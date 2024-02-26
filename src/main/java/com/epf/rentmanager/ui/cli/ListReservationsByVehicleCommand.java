// Nouvelle classe de commande pour lister les réservations associées à un véhicule
package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.utils.IOUtils;

public class ListReservationsByVehicleCommand {

    public static void listerReservationsParVehicule() {
        try {
            long vehicleId = IOUtils.readLong("ID du véhicule : ");
            System.out.println("Liste de toutes les réservations pour le véhicule " + vehicleId + " : ");
            for (Reservation reservation : ReservationService.getInstance().findReservationsByVehicleId(vehicleId)) {
                System.out.println(reservation);
            }
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
    }
}
