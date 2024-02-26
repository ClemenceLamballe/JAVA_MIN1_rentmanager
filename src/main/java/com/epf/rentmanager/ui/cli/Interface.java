package com.epf.rentmanager.ui.cli;

import java.util.Scanner;

public class Interface {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Créer un client");
            System.out.println("2. Lister tous les clients");
            System.out.println("3. Créer un véhicule");
            System.out.println("4. Lister tous les véhicules");
            System.out.println("5. Supprimer un client");
            System.out.println("6. Supprimer un véhicule");
            System.out.println("7. Créer une réservation");
            System.out.println("8. Supprimer une réservation");
            System.out.println("9. Lister toutes les réservations");
            System.out.println("10. Lister les réservations associées à un client");
            System.out.println("11. Lister les réservations associées à un véhicule");
            System.out.println("0. Quitter");

            System.out.print("Choix : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    CreateClientCommand.createClient();
                    break;
                case 2:
                    ListClientsCommand.listerClients();
                    break;
                case 3:
                    CreateVehicleCommand.createVehicle();
                    break;
                case 4:
                    ListVehicleCommand.listerVehicles();
                    break;
                case 5:
                    DeleteClientCommand.supprimerClient();
                    break;
                case 6:
                    DeleteVehicleCommand.supprimerVehicle();
                    break;
                case 7:
                    CreateReservationCommand.createReservation();
                    break;
                case 8:
                    DeleteReservationCommand.deleteReservation();
                    break;
                case 9:
                    ListReservationsCommand.listerReservations();
                    break;
                case 10:
                    ListReservationsByClientCommand.listerReservationsParClient();
                    break;
                case 11:
                    ListReservationsByVehicleCommand.listerReservationsParVehicule();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
