package com.epf.rentmanager.servlet.Vehicle;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vehicles/edit")
public class VehicleEditServlet extends HttpServlet {

    @Autowired
    private VehicleService vehicleService;

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
        try {
            long vehicleId = Long.parseLong(request.getParameter("id"));
            Vehicle vehicle = vehicleService.findById(vehicleId);
            request.setAttribute("vehicle", vehicle);
            request.getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
        } catch (NumberFormatException | DaoException | ServiceException e) {
            e.printStackTrace();
        }
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
            long vehicleId = Long.parseLong(request.getParameter("id"));
            String constructeur = request.getParameter("constructeur");
            String modele = request.getParameter("modele");
            String nbPlacesString = request.getParameter("nb_places");
            Vehicle vehicle = vehicleService.findById(vehicleId);
            if (constructeur.isEmpty()) {
                request.setAttribute("VehicleConstructeurErrorMessage", "Le constructeur du véhicule est requis.");
            }
            if (modele.isEmpty()) {
                request.setAttribute("VehicleModeleErrorMessage", "Le modèle du véhicule est requis.");
            }

            if (!nbPlacesString.matches("\\d+")) {
                request.setAttribute("VehicleNbPlacesErrorMessage", "Le nombre de places du véhicule doit être un chiffre.");
                request.setAttribute("vehicle", vehicle);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp");
                dispatcher.forward(request, response);
                return;
            }

            int nb_places = Integer.parseInt(request.getParameter("nb_places"));


            if (nb_places < 2 || nb_places > 9) {
                request.setAttribute("VehicleNbPlacesErrorMessage", "Le nombre de places du véhicule doit être compris entre 2 et 9.");
            }

            if (constructeur.isEmpty() || modele.isEmpty() || nb_places < 2 || nb_places > 9) {
                request.setAttribute("vehicle", vehicle);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Vehicle vehicletoupdate = new Vehicle(vehicleId, constructeur, modele, nb_places);
            vehicleService.update(vehicletoupdate);

            response.sendRedirect(request.getContextPath() + "/vehicles/list");
        } catch (NumberFormatException | ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }
}
