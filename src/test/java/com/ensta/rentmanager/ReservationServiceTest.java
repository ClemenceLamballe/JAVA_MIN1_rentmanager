package com.ensta.rentmanager;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.service.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {
    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationDao reservationDao;

    @Test
    public void delete_should_succeed_when_reservation_exists() throws DaoException, ServiceException {
        // Given
        long reservationId = 1;
        Reservation reservationToDelete = new Reservation(reservationId, 1, 1, LocalDate.now(), LocalDate.now().plusDays(1));
        when(reservationDao.findById(reservationId)).thenReturn(reservationToDelete);

        // When
        reservationService.delete(reservationId);

        // Then

    }

    @Test
    public void delete_should_fail_with_correct_error_message_when_reservation_is_null() throws DaoException {
        // Given
        long reservationId = 1;
        when(reservationDao.findById(reservationId)).thenReturn(null);

        // When
        try {
            reservationService.delete(reservationId);
            fail("Aucune exception n'a été levée pour une réservation nulle");
        } catch (ServiceException e) {
            // Then
            assertEquals("Erreur dans la suppression de reservation : reservation nulle", e.getMessage());
        } catch (DaoException e) {
            fail("DaoException a été levée à tort");
        }
    }

    @Test
    public void create_should_fail_when_vehicle_already_reserved_for_period() throws DaoException {
        // Given
        LocalDate startDate = LocalDate.of(2024, 4, 1);
        LocalDate endDate = LocalDate.of(2024, 4, 3);
        Long vehicleId = 100L;
        List<Reservation> allReservations = new ArrayList<>();
        allReservations.add(new Reservation(1, 1, vehicleId, LocalDate.of(2024, 4, 2), LocalDate.of(2024, 4, 4)));
        when(reservationDao.findAll()).thenReturn(allReservations);

        // When
        try {
            reservationService.create(new Reservation(100, 1, vehicleId, startDate, endDate));
            fail("Aucune exception n'a été levée pour une voiture déjà réservée pour cette période");
        } catch (ServiceException e) {
            // Then
            assertEquals("Cette voiture est déjà réservée pour cette période.", e.getMessage());
        }
    }

    @Test
    public void create_should_fail_when_reservation_duration_exceeds_limit() throws DaoException {
        // Given
        LocalDate startDate = LocalDate.of(2024, 4, 1);
        LocalDate endDate = LocalDate.of(2024, 4, 10);
        Long vehicleId = 100L;
        List<Reservation> vehicleReservations = new ArrayList<>();
        vehicleReservations.add(new Reservation(1, 1, vehicleId, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 6)));
        when(reservationDao.findResaByClientId(1L)).thenReturn(vehicleReservations);

        // When
        try {
            reservationService.create(new Reservation(100, 1, vehicleId, startDate, endDate));
            fail("Aucune exception n'a été levée pour une durée de réservation dépassant la limite");
        } catch (ServiceException e) {
            // Then
            assertEquals("Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite.", e.getMessage());
        }
    }

    @Test
    public void create_should_fail_when_vehicle_reservation_duration_exceeds_limit() throws DaoException {
        // Given
        LocalDate startDate = LocalDate.of(2024, 5, 1);
        LocalDate endDate = LocalDate.of(2024, 5, 5);
        Long vehicleId = 3L;
        List<Reservation> vehicleReservations = new ArrayList<>();
        vehicleReservations.add(new Reservation(1, 1, vehicleId, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 6)));
        vehicleReservations.add(new Reservation(2, 2, vehicleId, LocalDate.of(2024, 4, 7), LocalDate.of(2024, 4, 14)));
        vehicleReservations.add(new Reservation(3, 1, vehicleId, LocalDate.of(2024, 4, 15), LocalDate.of(2024, 4, 22)));
        vehicleReservations.add(new Reservation(4, 2, vehicleId, LocalDate.of(2024, 4, 23), LocalDate.of(2024, 4, 30)));

        when(reservationDao.findResaByVehicleId(vehicleId)).thenReturn(vehicleReservations);

        // When
        try {
            reservationService.create(new Reservation(5, 1, vehicleId, startDate, endDate));
            fail("Aucune exception n'a été levée pour une durée de réservation de véhicule dépassant la limite");
        } catch (ServiceException e) {
            // Then
            assertEquals("Vous ne pouvez pas réserver cette voiture car elle atteindra 30 jours de suite.", e.getMessage());
        }
    }

    @Test
    public void create_should_fail_when_start_date_after_end_date() throws DaoException {
        // Given
        LocalDate startDate = LocalDate.of(2024, 4, 10);
        LocalDate endDate = LocalDate.of(2024, 4, 1);
        Long vehicleId = 100L;

        // When
        try {
            reservationService.create(new Reservation(100, 1, vehicleId, startDate, endDate));
            fail("Aucune exception n'a été levée pour une date de début après la date de fin");
        } catch (ServiceException e) {
            // Then
            assertEquals("La date de début doit être antiérieur à celle de fin.", e.getMessage());
        }
    }

    @Test
    public void create_should_succeed_when_no_exceptions() throws DaoException, ServiceException {
        // Given
        LocalDate startDate = LocalDate.of(2024, 4, 1);
        LocalDate endDate = LocalDate.of(2024, 4, 3);
        Long vehicleId = 100L;

        when(reservationDao.findAll()).thenReturn(new ArrayList<>());
        when(reservationDao.create(any(Reservation.class))).thenReturn(1L);

        // When
        long reservationId = reservationService.create(new Reservation(1, 1, vehicleId, startDate, endDate));

        // Then
        assertEquals(1L, reservationId);
    }


}