package com.epf.rentmanager.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/reservations/details")
public class ReservationPlayServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long vehicleId = Long.parseLong(request.getParameter("id"));
            System.out.println("je veux afficher le vehicule"+vehicleId);
            Vehicle vehicles = vehicleService.findById(vehicleId);

            System.out.println("vehicule"+vehicles);
            if (vehicles != null) {

                request.setAttribute("vehicles", vehicles);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException | ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }
}
