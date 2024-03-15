package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.configurations.AppConfiguration;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.utils.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ListReservationsByClientCommand {
    private static ReservationService reservationService;
    private ListReservationsByClientCommand() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.reservationService = context.getBean(ReservationService.class);
    }
    public static void listerReservationsParClient() {
        try {
            long clientId = IOUtils.readLong("ID du client : ");
            System.out.println("Liste de toutes les réservations pour le client " + clientId + " : ");
            for (Reservation reservation : reservationService.findReservationsByClientId(clientId)) {
                System.out.println(reservation);
            }
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
    }
}
