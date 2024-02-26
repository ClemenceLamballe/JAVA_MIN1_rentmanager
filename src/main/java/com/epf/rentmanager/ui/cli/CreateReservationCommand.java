package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.utils.IOUtils;

import java.time.LocalDate;

public class CreateReservationCommand {

    public static long createReservation() {
        long clientId = IOUtils.readLong("ID du client : ");
        long vehicleId = IOUtils.readLong("ID du véhicule : ");
        LocalDate debut = IOUtils.readDate("Date de début (dd/mm/yyyy) : ",true);
        LocalDate fin = IOUtils.readDate("Date de fin (dd/mm/yyyy) : ",true);

        // Créer la réservation
        Reservation nouvelleReservation = new Reservation();
        nouvelleReservation.setClient_id(clientId);
        nouvelleReservation.setVehicle_id(vehicleId);
        nouvelleReservation.setDebut(debut);
        nouvelleReservation.setFin(fin);



        try {


            long reservationId = ReservationService.getInstance().create(nouvelleReservation);

            System.out.println("Réservation créée avec l'ID : " + reservationId+"\n");
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la création de la réservation : " + e.getMessage());
            // e.printStackTrace();
        }
        return 0;
    }
}
