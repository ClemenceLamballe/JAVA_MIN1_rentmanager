package com.epf.rentmanager.ui.cli;
import com.epf.rentmanager.configurations.AppConfiguration;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.utils.IOUtils;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.model.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class CreateClientCommand {

    private static ClientService clientService;
    private CreateClientCommand() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.clientService = context.getBean(ClientService.class);
    }

    public static long createClient(){

        String nom = IOUtils.readString("Nom : ", true);
        String prenom = IOUtils.readString("Prénom : ", true);
        String email = IOUtils.readString("Email : ",true);
        LocalDate naissance = IOUtils.readDate("Date de naissance (format dd/mm/yyyy) : ",true);


        Client nouveauClient = new Client();
        nouveauClient.setNom(nom);
        nouveauClient.setPrenom(prenom);
        nouveauClient.setEmail(email);
        nouveauClient.setNaissance(naissance);

        try {
            long clientId = clientService.create(nouveauClient);

            System.out.println("Client créé avec l'ID : " + clientId+"\n");
        } catch (ServiceException | DaoException e) {
            System.out.println("Erreur lors de la création du client : " + e.getMessage());
        }
        return 0;
    }

}
