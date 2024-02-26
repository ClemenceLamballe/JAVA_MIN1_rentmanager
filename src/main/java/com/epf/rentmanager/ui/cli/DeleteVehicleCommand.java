package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;

import java.util.Scanner;

public class DeleteVehicleCommand {

    public static void supprimerVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'ID du vehicule que vous souhaitez supprimer : ");
        long vehiculeId = scanner.nextLong();

        try {
            VehicleService vehicleService = VehicleService.getInstance();
            vehicleService.delete(vehiculeId);
            System.out.println("Véhicule supprimé avec succès.\n");
        } catch (ServiceException | DaoException e) {
            System.err.println("Erreur lors de la suppression du Véhicule: " + e.getMessage());
        }
    }

}
