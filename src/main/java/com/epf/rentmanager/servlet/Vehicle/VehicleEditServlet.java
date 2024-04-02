package com.epf.rentmanager.servlet.Vehicle;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long vehicleId = Long.parseLong(request.getParameter("id"));
            Vehicle vehicle = vehicleService.findById(vehicleId);
            request.setAttribute("vehicle", vehicle);
            request.getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
        } catch (NumberFormatException | DaoException | ServiceException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., redirect to an error page)
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long vehicleId = Long.parseLong(request.getParameter("id"));
            String constructeur = request.getParameter("constructeur");
            String modele = request.getParameter("modele");
            int nb_places = Integer.parseInt(request.getParameter("nb_places"));

            Vehicle vehicle = new Vehicle(vehicleId, constructeur, modele, nb_places);
            vehicleService.update(vehicle);

            response.sendRedirect(request.getContextPath() + "/vehicles/list");
        } catch (NumberFormatException | ServiceException e) {
            e.printStackTrace();
        }
    }
}
