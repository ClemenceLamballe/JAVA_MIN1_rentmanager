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


}