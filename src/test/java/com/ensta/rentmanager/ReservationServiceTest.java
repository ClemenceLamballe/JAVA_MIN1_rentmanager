package com.ensta.rentmanager;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.service.ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationDao reservationDao;

    @Before
    public void setUp() throws DaoException {
        when(reservationDao.findResaByVehicleId(1L)).thenReturn(new ArrayList<>());
    }
    @Test (expected = ServiceException.class)
    public void create_should_fail_when_reservation_already_exists() throws DaoException, ServiceException {
        Reservation existingReservation = new Reservation(-1, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(1));
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(existingReservation);
        when(reservationDao.findResaByVehicleId(1L)).thenReturn(reservations);

        reservationService.create(new Reservation(-2, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(1)));
    }

    @Test (expected = ServiceException.class)
    public void create_should_fail_when_reservation_exceeds_max_duration() throws DaoException, ServiceException {
        Reservation existingReservation = new Reservation(-1, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(7));
        reservationService.create(new Reservation(-1, 1L, 1L, LocalDate.now().plusDays(1), LocalDate.now().plusDays(9)));
    }

    @Test(expected = ServiceException.class)
    public void create_should_fail_when_vehicle_reserved_for_30_consecutive_days() throws DaoException, ServiceException {
        List<Reservation> vehicleReservations = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 5; i++) {
            vehicleReservations.add(new Reservation(-1, 1L, 1L, startDate.plusDays(i * 6), startDate.plusDays(i * 6 + 6)));
        }
        when(reservationDao.findResaByVehicleId(1L)).thenReturn(vehicleReservations);

        reservationService.create(new Reservation(-1, 1L, 1L, LocalDate.now().plusDays(37), LocalDate.now().plusDays(43)));

    }


    @Test
    public void create_should_succeed_when_vehicle_not_reserved_for_30_consecutive_days() throws DaoException, ServiceException {
        List<Reservation> vehicleReservations = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 5; i++) {
            vehicleReservations.add(new Reservation(-1, 1L, 1L, startDate.plusDays(i * 6), startDate.plusDays(i * 6 + 6)));
        }
        when(reservationDao.findResaByVehicleId(1L)).thenReturn(vehicleReservations);
        reservationService.create(new Reservation(-1, 1L, 1L, LocalDate.now().plusDays(31), LocalDate.now().plusDays(33)));
    }

    @Test
    public void create_should_succeed_when_vehicle_reserved_for_less_than_30_consecutive_days() throws DaoException, ServiceException {
        List<Reservation> vehicleReservations = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 4; i++) {
            vehicleReservations.add(new Reservation(-1, 1L, 1L, startDate.plusDays(i * 6), startDate.plusDays(i * 6 + 6)));
        }
        when(reservationDao.findResaByVehicleId(1L)).thenReturn(vehicleReservations);

        reservationService.create(new Reservation(-1, 1L, 1L, LocalDate.now().plusDays(29), LocalDate.now().plusDays(31)));
    }

    @Test
    public void create_should_succeed_when_no_existing_reservations() throws DaoException, ServiceException {
        Reservation newReservation = new Reservation(-1, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(2));
        when(reservationDao.create(newReservation)).thenReturn(1L);
        List<Reservation> reservations = reservationService.findAll();
        boolean isAvailable = reservationService.isReservationAvailable(newReservation.getDebut(), newReservation.getFin(), newReservation.getVehicle_id(), reservations);

        assertTrue(isAvailable);
    }



}
