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
        System.out.println("all:"+allReservations.size());

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

        boolean test =  reservationDao.isReservationAvailable(startDate, endDate, vehicleId,allReservations);
        System.out.println("test"+test+"avec"+startDate+" "+endDate+" "+vehicleId+" "+allReservations.size());

        if (!this.isReservationAvailable(startDate, endDate, vehicleId,allReservations)) {
            System.out.println("pas available avec"+allReservations.size());
            throw new ServiceException("Cette voiture est déjà réservée pour cette périodeeeeeeeee.");
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
                throw new ServiceException("Cette voiture est déjà réservée pour cette période. vous ne pouvez pas la modifier ainsi");
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

    public boolean isReservationAvailable(LocalDate startDate, LocalDate endDate, Long vehicleId, List<Reservation> allReservations) throws DaoException, ServiceException {

        try{
            System.out.println("hello?");
            return reservationDao.isReservationAvailable(startDate,endDate,vehicleId,allReservations);
        }catch (DaoException e) {
            throw new ServiceException("Erreur pour trouver valider la disponibilité de la réservation dans le Service");
        }
    }

    public boolean isReservationDurationValid(LocalDate startDate, LocalDate endDate, List<Reservation> vehicleReservations) throws DaoException, ServiceException {
        try{
            return reservationDao.isReservationDurationValid(startDate, endDate, vehicleReservations);

        }catch (DaoException e) {
            throw new ServiceException("Erreur pour trouver valider la durée de la réservation dans le Service");
        }
    }

    public boolean isReservationDurationVehicleValid(LocalDate startDate, LocalDate endDate, List<Reservation> Reservations) throws DaoException, ServiceException {
        try{
            return reservationDao.isReservationDurationVehicleValid(startDate, endDate, Reservations);
        }catch (DaoException e) {
            throw new ServiceException("Erreur pour trouver valider la durée de la réservation pour le vehicule dans le Service");
        }
    }

    public boolean isReservationDateValid (LocalDate startDate, LocalDate endDate) throws ServiceException {
        try{
            System.out.println("dans service");
            boolean isValid = reservationDao.isReservationDateValid(startDate, endDate);
            System.out.println("dans service"+isValid);

            return isValid;
        }catch (DaoException e) {
            throw new ServiceException("Erreur pour trouver valider les dates de la réservation dans le Service");
        }

    }

    public boolean isReservationStartDateFormatValid (String StartDate) throws ServiceException {
        try {
            return reservationDao.isReservationStartDateFormatValid(StartDate);
        }catch (DaoException e) {
            throw new ServiceException("Erreur pour trouver valider le format de date de début de la réservation dans le Service");
        }
    }

    public boolean isReservationEndDateFormatValid (String EndDate) throws ServiceException {
        try{
            return reservationDao.isReservationEndDateFormatValid(EndDate);
        }catch (DaoException e) {
            throw new ServiceException("Erreur pour trouver valider le format de date de fin de la réservation dans le Service");
        }
    }
}
