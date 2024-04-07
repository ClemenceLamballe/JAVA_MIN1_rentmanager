package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.configurations.AppConfiguration;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.utils.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DeleteReservationCommand {
    private static ReservationService reservationService;
    private DeleteReservationCommand() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.reservationService = context.getBean(ReservationService.class);
    }

    public static void deleteReservation() {
        long reservationId = IOUtils.readLong("ID de la réservation à supprimer : ");

        try {

            reservationService.delete(reservationId);

            System.out.println("Réservation supprimée avec succès\n");
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la suppression de la réservation : " + e.getMessage());
        }
    }
}
