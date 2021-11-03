/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3.reto3.reto3.service;

import ciclo3.reto3.reto3.repositor.ReservationRepository;
import ciclo3.reto3.reto3.model.Reservation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diana Romero
 */
@Service
/*
 * Se crea el servicio de la clase Reservation
 */
public class ReservationService {

    /**
     * Se crea de la clase Reservation la variable reservationRepository, para agregarlos a los metodos 
     * @Autowired inicializa el objeto reservationRepository
     */
    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * @param lista de las reservaciones
     * @return Todas las reservaciones contenidas en la Lista Reservation.
     */
    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    /**
     * Se crea el metodo que contiene la clase Reservation, para obtener una Reservacion a traves del IdReservation.
     * @return la reservacion guardada en la clase reservaciones segun su Id.
     */
    public Optional<Reservation> getReservation(int reservationId) {
        return reservationRepository.getReservation(reservationId);
    }

    /**
     * Se crea el metodo save, con las validaciones correspondientes de si ya existe el id o no
     * @return El IdReservation que se ingresa luego de las validaciones guardandolo en la variable reservation
     */
    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reaux = reservationRepository.getReservation(reservation.getIdReservation());
            if (reaux.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

     /**
     * Se crea el metodo update, para actualizar una reservacion. 
     * @return La reservacion actualizada, luego de verificar si el IdReservation existe o no
     */
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reaux = reservationRepository.getReservation(reservation.getIdReservation());
            if (!reaux.isEmpty()) {

                if (reservation.getStartDate() != null) {
                    reaux.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    reaux.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    reaux.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(reaux.get());
                return reaux.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }
    /**
     *
     * Se crea el metodo delete, para eliminar un registro de reservacion.
     * @return Si es posible eliminar la reservacion.
     */
    public boolean deleteReservation(int reservationId) {
        Boolean reaux = getReservation(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return reaux;
    }

}
