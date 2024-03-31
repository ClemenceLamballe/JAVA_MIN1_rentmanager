package com.epf.rentmanager.service;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {
    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }


    public long create(Reservation reservation) throws ServiceException, DaoException {

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

}
