package com.epf.rentmanager.service;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class ReservationService {
    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }


    /**
     * @param reservation
     * @return
     * @throws ServiceException
     * @throws DaoException
     */
    public long create(Reservation reservation) throws ServiceException, DaoException {
        this.validate(reservation);
        /*List<Reservation> allReservations = reservationDao.findAll();

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

        if (!this.isReservationAvailable(startDate, endDate, vehicleId,allReservations)) {
            throw new ServiceException("Cette voiture est déjà réservée pour cette période.");
        }

        if (!this.isReservationDurationValid(startDate, endDate, vehicleReservations)) {
            throw new ServiceException("Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite.");
        }

        if (!this.isReservationDurationVehicleValid(startDate, endDate, allReservationsvehhicle)) {
            throw new ServiceException("Vous ne pouvez pas réserver cette voiture car elle atteindra 30 jours de suite.");
        }

        if (!this.isReservationDateValid(startDate, endDate)) {
            throw new ServiceException("La date de début doit être antiérieur à celle de fin.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedEndDate = endDate.format(formatter);
        String formattedStartDate = startDate.format(formatter);

        if (!this.isReservationEndDateFormatValid(formattedStartDate)) {
            throw new ServiceException("Format de date invalide pour la date de début.");
        }

        if (!this.isReservationEndDateFormatValid(formattedEndDate)) {
            throw new ServiceException("Format de date invalide pour la date de fin.");
        }*/

            return reservationDao.create(reservation);
    }

    /**
     * @param reservationId
     * @throws ServiceException
     * @throws DaoException
     */
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


    /**
     * @return
     * @throws ServiceException
     * @throws DaoException
     */
    public List<Reservation> findAll() throws ServiceException, DaoException {
        return reservationDao.findAll();
    }

    /**
     * @param clientId
     * @return
     * @throws ServiceException
     * @throws DaoException
     */
    public List<Reservation> findReservationsByClientId(long clientId) throws ServiceException, DaoException {
        return reservationDao.findResaByClientId(clientId);
    }

    /**
     * @param vehicleId
     * @return
     * @throws ServiceException
     * @throws DaoException
     */
    public List<Reservation> findReservationsByVehicleId(long vehicleId) throws ServiceException, DaoException {
        return reservationDao.findResaByVehicleId(vehicleId);
    }

    /**
     * @param id
     * @return
     * @throws ServiceException
     */
    public Reservation findById(long id) throws ServiceException {
        try {
            return reservationDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la récupération de la réservation");
        }
    }


    /**
     * @return
     * @throws ServiceException
     * @throws DaoException
     */
    public int count() throws ServiceException, DaoException {
        return reservationDao.countReservation();
    }

    /**
     * @param reservation
     * @throws ServiceException
     */
    public void update(Reservation reservation) throws ServiceException {
        try {
            this.validate(reservation);
            /*
            List<Reservation> allReservations = reservationDao.findAll();
            allReservations.removeIf(r -> r.getId() == reservation.getId());

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


            if (!this.isReservationAvailable(startDate, endDate, vehicleId,allReservations)) {
                throw new ServiceException("Cette voiture est déjà réservée pour cette période. vous ne pouvez pas la modifier ainsi");
            }

            if (!this.isReservationDurationValid(startDate, endDate, vehicleReservations)) {
                throw new ServiceException("Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite.");
            }

            if (!this.isReservationDurationVehicleValid(startDate, endDate, allReservationsvehhicle)) {
                throw new ServiceException("Vous ne pouvez pas réserver cette voiture car elle atteindra 30 jours de suite.");
            }

            if (!this.isReservationDateValid(startDate, endDate)) {
                throw new ServiceException("La date de début doit être antiérieur à celle de fin.");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedEndDate = endDate.format(formatter);
            String formattedStartDate = startDate.format(formatter);


            if (!this.isReservationEndDateFormatValid(formattedEndDate)) {
                throw new ServiceException("Format de date de fin invalide.");
            }

            if (!this.isReservationEndDateFormatValid(formattedStartDate)) {
                throw new ServiceException("Format de date de début invalide.");
            }*/

            reservationDao.update(reservation);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la mise à jour de la réservation dans le Service");
        }
    }

    /**
     * @param startDate
     * @param endDate
     * @param vehicleId
     * @param allReservations
     * @return
     * @throws DaoException
     * @throws ServiceException
     */
    public boolean isReservationAvailable(LocalDate startDate, LocalDate endDate, Long vehicleId, List<Reservation> allReservations) throws DaoException, ServiceException {
        try {

            for (Reservation existingReservation : allReservations) {
                if (existingReservation.getVehicle_id() == vehicleId
                        && (!existingReservation.getDebut().isAfter(endDate) && !existingReservation.getFin().isBefore(startDate))) {
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            throw new ServiceException("Erreur pour vérifier la validité de la réservation",e);
        }
    }

    /**
     * @param startDate
     * @param endDate
     * @param vehicleReservations
     * @return
     * @throws DaoException
     * @throws ServiceException
     */
    public boolean isReservationDurationValid(LocalDate startDate, LocalDate endDate, List<Reservation> vehicleReservations) throws DaoException, ServiceException {
        try{
            long totalDays = 0;
            for (Reservation reservation : vehicleReservations) {
                if (endDate.equals(reservation.getDebut().minusDays(1)) || startDate.equals(reservation.getFin().plusDays(1))) {
                    totalDays += ChronoUnit.DAYS.between(reservation.getDebut(), reservation.getFin()) +1 ;
                }
            }
            long newReservationDays = ChronoUnit.DAYS.between(startDate, endDate);
            return totalDays + newReservationDays <= 7;
        }catch (Exception e) {
            throw new ServiceException("Erreur pour trouver valider la durée de la réservation dans le Service");
        }
    }

    /**
     * @param startDate
     * @param endDate
     * @param Reservations
     * @return
     * @throws DaoException
     * @throws ServiceException
     */
    public boolean isReservationDurationVehicleValid(LocalDate startDate, LocalDate endDate, List<Reservation> Reservations) throws DaoException, ServiceException {
        try{
            Collections.sort(Reservations, Comparator.comparing(Reservation::getDebut));
            long totalDays = 0;
            long newReservationDays = ChronoUnit.DAYS.between(startDate, endDate);
            for (int i = 0; i < Reservations.size(); i++) {
                Reservation currentReservation = Reservations.get(i);
                if (i > 0) {
                    LocalDate previousReservationEndDate = Reservations.get(i - 1).getFin();
                    if (!startDate.equals(previousReservationEndDate.plusDays(1))) {
                        if (totalDays > 30) {
                            return false;
                        }
                    }
                }
                totalDays += ChronoUnit.DAYS.between(currentReservation.getDebut(), currentReservation.getFin()) + 1;
            }
            totalDays += newReservationDays;
            return totalDays <= 30;
        } catch (Exception e) {
            throw new ServiceException("Erreur pour trouver valider la durée de la réservation pour le vehicule dans le Service");
        }
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     * @throws ServiceException
     */
    public boolean isReservationDateValid (LocalDate startDate, LocalDate endDate) throws ServiceException {
        try {
            if(endDate.isAfter(startDate)){
                return true;
            }
            return false;
        }catch (Exception e) {
            throw new ServiceException("Erreur pour trouver valider les dates de la réservation dans le Service");
        }

    }

    /**
     * @param StartDate
     * @return
     * @throws ServiceException
     */
    public boolean isReservationStartDateFormatValid (String StartDate) throws ServiceException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(StartDate, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }catch (Exception e) {
            throw new ServiceException("Erreur lors de la validation du format de la date de début de réservation.", e);
        }
    }

    /**
     * @param EndDate
     * @return
     * @throws ServiceException
     */
    public boolean isReservationEndDateFormatValid (String EndDate) throws ServiceException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate.parse(EndDate, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }catch (Exception e) {
            throw new ServiceException("Erreur lors de la validation du format de la date de fin de réservation.", e);
        }
    }

    public void validate (Reservation reservation) throws ServiceException, DaoException {
        List<Reservation> allReservations = reservationDao.findAll();
        allReservations.removeIf(r -> r.getId() == reservation.getId());

        LocalDate startDate = reservation.getDebut();
        LocalDate endDate = reservation.getFin();
        Long vehicleId = reservation.getVehicle_id();
        Long clientId = reservation.getClient_id();
        List<Reservation> clientReservations = reservationDao.findResaByClientId(clientId);
        clientReservations.removeIf(r -> r.getId() == reservation.getId());

        List<Reservation> vehicleReservations = new ArrayList<>();
        for (Reservation reservationc : clientReservations) {
            if (reservationc.getVehicle_id() == vehicleId) {
                vehicleReservations.add(reservationc);
            }
        }
        List<Reservation> allReservationsvehicle = reservationDao.findResaByVehicleId(vehicleId);
        allReservationsvehicle.removeIf(r -> r.getId() == reservation.getId());


        if (!this.isReservationAvailable(startDate, endDate, vehicleId,allReservations)) {
            throw new ServiceException("Cette voiture est déjà réservée pour cette période.");
        }

        if (!this.isReservationDurationValid(startDate, endDate, vehicleReservations)) {
            throw new ServiceException("Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite.");
        }

        if (!this.isReservationDurationVehicleValid(startDate, endDate, allReservationsvehicle)) {
            throw new ServiceException("Vous ne pouvez pas réserver cette voiture car elle atteindra 30 jours de suite.");
        }

        if (!this.isReservationDateValid(startDate, endDate)) {
            throw new ServiceException("La date de début doit être antiérieur à celle de fin.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedEndDate = endDate.format(formatter);
        String formattedStartDate = startDate.format(formatter);


        if (!this.isReservationEndDateFormatValid(formattedEndDate)) {
            throw new ServiceException("Format de date de fin invalide.");
        }

        if (!this.isReservationEndDateFormatValid(formattedStartDate)) {
            throw new ServiceException("Format de date de début invalide.");
        }
    }

    }
