package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.utils.IOUtils;

public class DeleteReservationCommand {

    public static void deleteReservation() {
        long reservationId = IOUtils.readLong("ID de la réservation à supprimer : ");

        try {

            ReservationService.getInstance().delete(reservationId);

            System.out.println("Réservation supprimée avec succès\n");
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la suppression de la réservation : " + e.getMessage());
        }
    }
}
