package com.epf.rentmanager.ui.cli;
import com.epf.rentmanager.configurations.AppConfiguration;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
public class ListVehicleCommand {

    private static VehicleService vehicleService;
    private ListVehicleCommand() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.vehicleService = context.getBean(VehicleService.class);
    }

    public static void listerVehicles() {

        try {
            List<Vehicle> vehicles = vehicleService.findAll();

            if (vehicles.isEmpty()) {
                System.out.println("Aucun véhicule trouvé.");
            } else {
                System.out.println("Liste des véhicules :");
                for (Vehicle vehicle : vehicles) {
                    System.out.println(vehicle.getId() + ": " + vehicle.getConstructeur() + " " + vehicle.getNb_places()+ " " + vehicle.getModele());

                }
                System.out.println();
            }
        } catch (DaoException | ServiceException e) {
            System.err.println("Erreur lors de la récupération de la liste des véhicules : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
