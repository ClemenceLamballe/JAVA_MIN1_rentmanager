package com.ensta.rentmanager;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock
    private ReservationDao reservationDaoMock;

    private ReservationService reservationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationDaoMock);
    }

    @Test
    public void testCreateReservation() throws DaoException, ServiceException {
        Reservation reservation = new Reservation();
        when(reservationDaoMock.create(reservation)).thenReturn(1L);

        long result = reservationService.create(reservation);

        assertEquals(1L, result);
        verify(reservationDaoMock, times(1)).create(reservation);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteReservation_WhenReservationNotFound() throws DaoException, ServiceException {
        long reservationId = 1L;
        when(reservationDaoMock.findById(reservationId)).thenReturn(null);

        reservationService.delete(reservationId);
    }

    @Test
    public void testDeleteReservation() throws DaoException, ServiceException {
        long reservationId = 1L;
        Reservation reservationToDelete = new Reservation();
        when(reservationDaoMock.findById(reservationId)).thenReturn(reservationToDelete);

        reservationService.delete(reservationId);

        verify(reservationDaoMock, times(1)).delete(reservationToDelete);
    }

    @Test
    public void testFindAllReservations() throws DaoException, ServiceException {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        when(reservationDaoMock.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.findAll();

        assertEquals(2, result.size());
        verify(reservationDaoMock, times(1)).findAll();
    }

    @Test
    public void testFindReservationsByClientId() throws DaoException, ServiceException {
        long clientId = 1L;
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        when(reservationDaoMock.findResaByClientId(clientId)).thenReturn(reservations);

        List<Reservation> result = reservationService.findReservationsByClientId(clientId);

        assertEquals(1, result.size());
        verify(reservationDaoMock, times(1)).findResaByClientId(clientId);
    }

    @Test
    public void testFindReservationsByVehicleId() throws DaoException, ServiceException {
        long vehicleId = 1L;
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        when(reservationDaoMock.findResaByVehicleId(vehicleId)).thenReturn(reservations);

        List<Reservation> result = reservationService.findReservationsByVehicleId(vehicleId);

        assertEquals(1, result.size());
        verify(reservationDaoMock, times(1)).findResaByVehicleId(vehicleId);
    }

    @Test
    public void testCountReservations() throws DaoException, ServiceException {
        when(reservationDaoMock.countReservation()).thenReturn(5);

        int result = reservationService.count();

        assertEquals(5, result);
        verify(reservationDaoMock, times(1)).countReservation();
    }
}
