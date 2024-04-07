package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.configurations.AppConfiguration;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.utils.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ListReservationsByVehicleCommand {
    private static ReservationService reservationService;
    private ListReservationsByVehicleCommand() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.reservationService = context.getBean(ReservationService.class);
    }

    public static void listerReservationsParVehicule() {
        try {
            long vehicleId = IOUtils.readLong("ID du véhicule : ");
            System.out.println("Liste de toutes les réservations pour le véhicule " + vehicleId + " : ");
            for (Reservation reservation : reservationService.findReservationsByVehicleId(vehicleId)) {
                System.out.println(reservation);
            }
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
    }
}
