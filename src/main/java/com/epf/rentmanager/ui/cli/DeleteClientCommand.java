package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.*;
import java.util.Scanner;
public class DeleteClientCommand {

    public static void supprimerClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'ID du client que vous souhaitez supprimer : ");
        long clientId = scanner.nextLong();

        try {
            ClientService clientService = ClientService.getInstance();
            clientService.delete(clientId);
            System.out.println("Client supprimé avec succès.\n");
        } catch (ServiceException | DaoException e) {
            System.err.println("Erreur lors de la suppression du client : " + e.getMessage());
        }
    }


}
