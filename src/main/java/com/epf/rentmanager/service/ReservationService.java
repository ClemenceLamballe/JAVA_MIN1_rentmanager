package com.epf.rentmanager.service;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ReservationService {
    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }


    public long create(Reservation reservation) throws ServiceException, DaoException {
        List<Reservation> allReservations = reservationDao.findAll();
        LocalDate debut = reservation.getDebut();
        LocalDate fin = reservation.getFin();

        LocalDate startDate = reservation.getDebut();
        LocalDate endDate = reservation.getFin();
        Long vehicleId = reservation.getVehicle_id();

        if (!reservationDao.isReservationAvailable(startDate, endDate, vehicleId)) {
            throw new ServiceException("Cette voiture est déjà réservée pour cette période.");
        }

        /*// Vérification : une voiture ne peut pas être réservée 2 fois le même jour
        boolean alreadyReservedSameDay = false;
        for (Reservation existingReservation : allReservations) {
            LocalDate existingDebut = existingReservation.getDebut();
            LocalDate existingFin = existingReservation.getFin();
            if (existingReservation.getVehicle_id() == reservation.getVehicle_id()
                    && (existingDebut.isEqual(debut) || existingFin.isEqual(fin))) {
                alreadyReservedSameDay = true;
                break;
            }
        }
        if (alreadyReservedSameDay) {
            throw new ServiceException("Cette voiture est déjà réservée pour cette date.");
        }

        // Vérification : une voiture ne peut pas être réservée plus de 7 jours de suite par le même utilisateur
        int maxConsecutiveDays = 7;
        int consecutiveDays = 1;
        for (int i = 1; i < allReservations.size(); i++) {
            Reservation current = allReservations.get(i);
            Reservation previous = allReservations.get(i - 1);

            if (current.getVehicle_id() == reservation.getVehicle_id()
                    && current.getClient_id() == reservation.getClient_id()
                    && current.getDebut().minusDays(1).isEqual(previous.getFin())) {
                consecutiveDays++;
                if (consecutiveDays > maxConsecutiveDays) {
                    throw new ServiceException("Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite.");
                }
            } else {
                consecutiveDays = 1;
            }
        }

        // Vérification : une voiture ne peut pas être réservée 30 jours de suite sans pause
        int maxDaysWithoutPause = 30;
        int daysWithoutPause = 0;
        for (LocalDate date = debut; date.isBefore(fin.plusDays(1)); date = date.plusDays(1)) {
            boolean reserved = false;
            for (Reservation existingReservation : allReservations) {
                LocalDate existingDebut = existingReservation.getDebut();
                LocalDate existingFin = existingReservation.getFin();
                if (existingReservation.getVehicle_id() == reservation.getVehicle_id()
                        && existingDebut.isBefore(date) && existingFin.isAfter(date)) {
                    reserved = true;
                    break;
                }
            }
            if (reserved) {
                daysWithoutPause = 0;
            } else {
                daysWithoutPause++;
                if (daysWithoutPause > maxDaysWithoutPause) {
                    throw new ServiceException("Vous ne pouvez pas réserver cette voiture plus de 30 jours de suite sans pause.");
                }
            }
        }*/

        return reservationDao.create(reservation);
    }

    public void delete(long reservationId) throws ServiceException, DaoException {
        Reservation reservationToDelete = reservationDao.findById(reservationId);
        if (reservationToDelete == null) {
            throw new ServiceException("Erreur dans la suppression de reservation : reservation nulle");
        }

        try {
            reservationDao.delete(reservationToDelete);
        } catch (DaoException e) {
            throw new ServiceException("Erreur dans la spression de vehicule");
        }
    }



    public List<Reservation> findAll() throws ServiceException, DaoException {
        return reservationDao.findAll();
    }

    public List<Reservation> findReservationsByClientId(long clientId) throws ServiceException, DaoException {
        return reservationDao.findResaByClientId(clientId);
    }

    public List<Reservation> findReservationsByVehicleId(long vehicleId) throws ServiceException, DaoException {
        return reservationDao.findResaByVehicleId(vehicleId);
    }

    public Reservation findById(long id) throws ServiceException {
        try {
            return reservationDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la récupération de la réservation");
        }
    }


    public int count() throws ServiceException, DaoException {
        return reservationDao.countReservation();
    }

    public void update(Reservation reservation) throws ServiceException {
        try {
            //if (reservation.getDebut().isAfter(reservation.getFin())) {
              //  throw new ServiceException("La date de début ne peut pas être après la date de fin.");
            //}
            reservationDao.update(reservation);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la mise à jour de la réservation dans le Service");
        }
    }

    public boolean isReservationAvailable(LocalDate startDate, LocalDate endDate, Long vehicleId) throws DaoException {
        return reservationDao.isReservationAvailable(startDate, endDate, vehicleId);
    }

    public boolean isReservationDurationValid(LocalDate startDate, LocalDate endDate, List<Reservation> vehicleReservations) throws DaoException {
        return reservationDao.isReservationDurationValid(startDate, endDate, vehicleReservations);
    }

    public boolean isReservationDurationVehicleValid(LocalDate startDate, LocalDate endDate, List<Reservation> Reservations) throws DaoException {
        return reservationDao.isReservationDurationVehicleValid(startDate, endDate, Reservations);
    }

}
