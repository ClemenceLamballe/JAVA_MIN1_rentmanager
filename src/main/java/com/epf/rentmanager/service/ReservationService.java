package com.epf.rentmanager.service;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import java.util.List;
public class ReservationService {
    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }

    public long create(Reservation reservation) throws ServiceException, DaoException {

        return reservationDao.create(reservation);
    }

    public void delete(long reservationId) throws ServiceException, DaoException {
        Reservation reservationToDelete = reservationDao.findById(reservationId);
        if (reservationToDelete == null) {
            throw new ServiceException();
        }

        try {
            reservationDao.delete(reservationToDelete);
        } catch (DaoException e) {
            throw new ServiceException();
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
}
