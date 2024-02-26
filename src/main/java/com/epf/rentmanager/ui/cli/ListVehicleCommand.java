package com.epf.rentmanager.ui.cli;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.model.Vehicle;

import java.util.List;
public class ListVehicleCommand {

    public static void listerVehicles() {
        VehicleDao vehicleDao = VehicleDao.getInstance();
        try {
            List<Vehicle> vehicles = vehicleDao.findAll();

            if (vehicles.isEmpty()) {
                System.out.println("Aucun véhicule trouvé.");
            } else {
                System.out.println("Liste des véhicules :");
                for (Vehicle vehicle : vehicles) {
                    //System.out.println(vehicle.toString());
                    //System.out.println("OU");
                    System.out.println(vehicle.getId() + ": " + vehicle.getConstructeur() + " " + vehicle.getNb_places()+ " " + vehicle.getModele());

                }
                System.out.println();
            }
        } catch (DaoException e) {
            System.err.println("Erreur lors de la récupération de la liste des véhicules : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
