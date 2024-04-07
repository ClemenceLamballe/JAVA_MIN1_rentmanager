package com.epf.rentmanager.servlet.Vehicle;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


@WebServlet("/vehicles/create")


public class VehicleCreateServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;

    /**
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String constructeur = request.getParameter("constructeur");
            String modele = request.getParameter("modele");
            String nbPlacesString = request.getParameter("nb_places");


            if (constructeur.isEmpty()) {
                request.setAttribute("VehicleConstructeurErrorMessage", "Le constructeur du véhicule est requis.");
            }
            if (modele.isEmpty()) {
                request.setAttribute("VehicleModeleErrorMessage", "Le modèle du véhicule est requis.");
            }


            if (!nbPlacesString.matches("\\d+")) {
                request.setAttribute("VehicleNbPlacesErrorMessage", "Le nombre de places du véhicule doit être un chiffre.");
                request.setAttribute("constructeur", constructeur);
                request.setAttribute("modele", modele);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            int nbPlaces = Integer.parseInt(request.getParameter("nb_places"));


            if (nbPlaces < 2 || nbPlaces > 9) {
                request.setAttribute("VehicleNbPlacesErrorMessage", "Le nombre de places du véhicule doit être compris entre 2 et 9.");
            }

            if (constructeur.isEmpty() || modele.isEmpty() || nbPlaces < 2 || nbPlaces > 9) {
                request.setAttribute("constructeur", constructeur);
                request.setAttribute("modele", modele);
                request.setAttribute("nbPlaces", nbPlaces);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Vehicle newVehicle = new Vehicle(-1, constructeur, modele, nbPlaces);
            long generatedId = vehicleService.create(newVehicle);

            newVehicle.setId(generatedId);

            int numberOfVehicles = vehicleService.count();
            request.getSession().setAttribute("numberOfVehicles", numberOfVehicles);

            response.sendRedirect(request.getContextPath() + "/vehicles/list");
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }
}
