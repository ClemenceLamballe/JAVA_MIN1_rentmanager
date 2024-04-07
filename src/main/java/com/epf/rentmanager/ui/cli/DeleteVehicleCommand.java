package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.configurations.AppConfiguration;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class DeleteVehicleCommand {

    private static VehicleService vehicleService;
    private DeleteVehicleCommand() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.vehicleService = context.getBean(VehicleService.class);
    }


    public static void supprimerVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'ID du vehicule que vous souhaitez supprimer : ");
        long vehiculeId = scanner.nextLong();

        try {

            vehicleService.delete(vehiculeId);
            System.out.println("Véhicule supprimé avec succès.\n");
        } catch (ServiceException | DaoException e) {
            System.err.println("Erreur lors de la suppression du Véhicule: " + e.getMessage());
        }
    }

}
