package com.epf.rentmanager.service;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

        LocalDate startDate = reservation.getDebut();
        LocalDate endDate = reservation.getFin();
        Long vehicleId = reservation.getVehicle_id();
        Long clientId = reservation.getClient_id();

        List<Reservation> clientReservations = reservationDao.findResaByClientId(clientId);
        List<Reservation> vehicleReservations = new ArrayList<>();
        for (Reservation reservationc : clientReservations) {
            if (reservationc.getVehicle_id() == vehicleId) {
                vehicleReservations.add(reservationc);
            }
        }
        List<Reservation> allReservationsvehhicle = reservationDao.findResaByVehicleId(vehicleId);


        if (!reservationDao.isReservationAvailable(startDate, endDate, vehicleId,allReservations)) {
            throw new ServiceException("Cette voiture est déjà réservée pour cette période.");
        }

        if (!reservationDao.isReservationDurationValid(startDate, endDate, vehicleReservations)) {
            throw new ServiceException("Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite.");
        }

        if (!reservationDao.isReservationDurationVehicleValid(startDate, endDate, allReservationsvehhicle)) {
            throw new ServiceException("Vous ne pouvez pas réserver cette voiture car elle atteindra 30 jours de suite.");
        }

        if (!reservationDao.isReservationDateValid(startDate, endDate)) {
            throw new ServiceException("La date de début doit être antiérieur à celle de fin.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedEndDate = endDate.format(formatter);
        String formattedStartDate = startDate.format(formatter);

        if (!reservationDao.isReservationEndDateFormatValid(formattedStartDate)) {
            throw new ServiceException("Format de date invalide pour la date de début.");
        }

        if (!reservationDao.isReservationEndDateFormatValid(formattedEndDate)) {
            throw new ServiceException("Format de date invalide pour la date de fin.");
        }

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
            List<Reservation> allReservations = reservationDao.findAll();

            LocalDate startDate = reservation.getDebut();
            LocalDate endDate = reservation.getFin();
            Long vehicleId = reservation.getVehicle_id();
            Long clientId = reservation.getClient_id();
            List<Reservation> clientReservations = reservationDao.findResaByClientId(clientId);
            List<Reservation> vehicleReservations = new ArrayList<>();
            for (Reservation reservationc : clientReservations) {
                if (reservationc.getVehicle_id() == vehicleId) {
                    vehicleReservations.add(reservationc);
                }
            }
            List<Reservation> allReservationsvehhicle = reservationDao.findResaByVehicleId(vehicleId);


            if (!reservationDao.isReservationAvailable(startDate, endDate, vehicleId,allReservations)) {
                throw new ServiceException("Cette voiture est déjà réservée pour cette période.");
            }

            if (!reservationDao.isReservationDurationValid(startDate, endDate, vehicleReservations)) {
                throw new ServiceException("Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite.");
            }

            if (!reservationDao.isReservationDurationVehicleValid(startDate, endDate, allReservationsvehhicle)) {
                throw new ServiceException("Vous ne pouvez pas réserver cette voiture car elle atteindra 30 jours de suite.");
            }

            if (!reservationDao.isReservationDateValid(startDate, endDate)) {
                throw new ServiceException("La date de début doit être antiérieur à celle de fin.");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedEndDate = endDate.format(formatter);
            String formattedStartDate = startDate.format(formatter);


            if (!reservationDao.isReservationEndDateFormatValid(formattedEndDate)) {
                throw new ServiceException("Format de date de fin invalide.");
            }

            if (!reservationDao.isReservationEndDateFormatValid(formattedStartDate)) {
                throw new ServiceException("Format de date de début invalide.");
            }

            reservationDao.update(reservation);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la mise à jour de la réservation dans le Service");
        }
    }

    public boolean isReservationAvailable(LocalDate startDate, LocalDate endDate, Long vehicleId, List<Reservation> allReservations) throws DaoException {
        return reservationDao.isReservationAvailable(startDate, endDate, vehicleId,allReservations);
    }

    public boolean isReservationDurationValid(LocalDate startDate, LocalDate endDate, List<Reservation> vehicleReservations) throws DaoException {
        return reservationDao.isReservationDurationValid(startDate, endDate, vehicleReservations);
    }

    public boolean isReservationDurationVehicleValid(LocalDate startDate, LocalDate endDate, List<Reservation> Reservations) throws DaoException {
        return reservationDao.isReservationDurationVehicleValid(startDate, endDate, Reservations);
    }

    public boolean isReservationDateValid (LocalDate startDate, LocalDate endDate){
        return reservationDao.isReservationDateValid(startDate, endDate);
    }

    public boolean isReservationStartDateFormatValid (String StartDate){
        return reservationDao.isReservationStartDateFormatValid(StartDate);
    }

    public boolean isReservationEndDateFormatValid (String EndDate){
        return reservationDao.isReservationEndDateFormatValid(EndDate);
    }
}
