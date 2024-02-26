package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.utils.IOUtils;

public class ListReservationsByClientCommand {

    public static void listerReservationsParClient() {
        try {
            long clientId = IOUtils.readLong("ID du client : ");
            System.out.println("Liste de toutes les réservations pour le client " + clientId + " : ");
            for (Reservation reservation : ReservationService.getInstance().findReservationsByClientId(clientId)) {
                System.out.println(reservation);
            }
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
    }
}
