package com.epf.rentmanager.ui.cli;
import com.epf.rentmanager.configurations.AppConfiguration;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
public class ListClientsCommand {
    private static ClientService clientService;
    private ListClientsCommand() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.clientService = context.getBean(ClientService.class);
    }
    public static void listerClients() {

        try {
            List<Client> clients = clientService.findAll();

            if (clients.isEmpty()) {
                System.out.println("Aucun client trouvé.");
            } else {
                System.out.println("Liste des clients :");
                for (Client client : clients) {
                    System.out.println(client.getId() + ": " + client.getNom() + " " + client.getPrenom());
                }
                System.out.println();
            }
        } catch (DaoException | ServiceException e) {
            System.err.println("Erreur lors de la récupération de la liste des clients : " + e.getMessage());
        }
    }

}
