package com.epf.rentmanager.ui.cli;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;
import java.util.ArrayList;
import java.util.List;
public class ListClientsCommand {

    public static void listerClients() {
        ClientDao clientDao = ClientDao.getInstance();
        try {
            List<Client> clients = clientDao.findAll();

            if (clients.isEmpty()) {
                System.out.println("Aucun client trouvé.");
            } else {
                System.out.println("Liste des clients :");
                for (Client client : clients) {
                    System.out.println(client.getId() + ": " + client.getNom() + " " + client.getPrenom());
                }
                System.out.println();
            }
        } catch (DaoException e) {
            System.err.println("Erreur lors de la récupération de la liste des clients : " + e.getMessage());
        }
    }

}
