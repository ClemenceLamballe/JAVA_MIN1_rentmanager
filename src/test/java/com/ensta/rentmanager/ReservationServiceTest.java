package com.ensta.rentmanager;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.service.ServiceException;
import org.junit.Test;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationDao reservationDao;
    @Test (expected = ServiceException.class)
    public void create_should_fail_when_reservation_already_exists() throws DaoException, ServiceException {
        Reservation existingReservation = new Reservation(-1, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(1));
        reservationService.create(new Reservation(-2, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(1)));
    }

    @Test (expected = ServiceException.class)
    public void create_should_fail_when_reservation_exceeds_max_duration() throws DaoException, ServiceException {
        Reservation existingReservation = new Reservation(-1, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(7));
        reservationService.create(new Reservation(-1, 1L, 1L, LocalDate.now().plusDays(1), LocalDate.now().plusDays(9)));
    }
}
