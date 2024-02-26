package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;

public class ListReservationsCommand {

    public static void listerReservations() {
        try {
            System.out.println("Liste de toutes les réservations : ");
            for (Reservation reservation : ReservationService.getInstance().findAll()) {
                System.out.println(reservation);
            }
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
    }
}
