package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.configurations.AppConfiguration;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class CreateVehicleCommand {
    private static VehicleService vehicleService;
    private CreateVehicleCommand() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.vehicleService = context.getBean(VehicleService.class);
    }

    public static long createVehicle() {



        String constructeur = IOUtils.readString("Constructeur : ", true);
        String modele = IOUtils.readString("modele : ", true);
        int nbPlaces = IOUtils.readInt("Nombre de place : ");


        Vehicle nouveauVehicle = new Vehicle();
        nouveauVehicle.setConstructeur(constructeur);
        nouveauVehicle.setNb_places(nbPlaces);
        nouveauVehicle.setModele(modele);



        try {

            long VehicleId = vehicleService.create(nouveauVehicle);

            System.out.println("Vehicule créé avec l'ID : " + VehicleId+"\n");
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la création du Vehicule: " + e.getMessage());

        }
        return 0;
    }

}
