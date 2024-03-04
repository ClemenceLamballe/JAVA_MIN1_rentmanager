package com.epf.rentmanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;


@WebServlet("/vehicles/create")


public class VehicleCreateServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Affichage du formulaire
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String constructeur = request.getParameter("constructeur");
            String modele = request.getParameter("modele");
            int nbPlaces = Integer.parseInt(request.getParameter("nb_places"));

            // Création d'un objet Vehicle avec un ID par défaut (-1)
            Vehicle newVehicle = new Vehicle(-1, constructeur, modele, nbPlaces);

            // Appel à la méthode create du service
            VehicleService vehicleService = VehicleService.getInstance();
            long generatedId = vehicleService.create(newVehicle);

            // Mise à jour de l'ID après la création
            newVehicle.setId(generatedId);

            // Mise à jour du nombre de véhicules dans la session
            int numberOfVehicles = vehicleService.count();
            request.getSession().setAttribute("numberOfVehicles", numberOfVehicles);

            // Redirection vers la liste des véhicules
            response.sendRedirect(request.getContextPath() + "/vehicles/list");
        } catch (ServiceException | DaoException e) {
            // Gérer l'exception (par exemple, rediriger vers une page d'erreur)
            e.printStackTrace();
        }
    }
}
