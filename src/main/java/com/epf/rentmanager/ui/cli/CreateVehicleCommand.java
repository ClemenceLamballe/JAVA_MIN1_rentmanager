package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;

import java.time.LocalDate;

public class CreateVehicleCommand {

    public static long createVehicle() {


        String constructeur = IOUtils.readString("Constructeur : ", true);
        String modele = IOUtils.readString("modele : ", true);
        int nbPlaces = IOUtils.readInt("Nombre de place : ");

        // Créer le vehicle
        Vehicle nouveauVehicle = new Vehicle();
        nouveauVehicle.setConstructeur(constructeur);
        nouveauVehicle.setNb_places(nbPlaces);
        nouveauVehicle.setModele(modele);



        try {
            // Appeler le service pour créer le vehicle
            long VehicleId = VehicleService.getInstance().create(nouveauVehicle);

            System.out.println("Vehicule créé avec l'ID : " + VehicleId+"\n");
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la création du Vehicule: " + e.getMessage());
            //e.printStackTrace();
        }
        return 0;
    }

}
