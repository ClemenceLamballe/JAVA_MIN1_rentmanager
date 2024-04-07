package com.epf.rentmanager.servlet.Reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/reservations/list")
public class ReservationListServlet extends HttpServlet {
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
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

        try {
            List<Reservation> reservations = reservationService.findAll();
            List<Client> clients = new ArrayList<>();
            List<Vehicle> vehicles = new ArrayList<>();

            for (Reservation reservation : reservations) {
                 clients.add(clientService.findById(reservation.getClient_id()));
                 vehicles.add(vehicleService.findById(reservation.getVehicle_id()));

            }

            request.setAttribute("reservations", reservations);
            request.setAttribute("clients", clients);
            request.setAttribute("vehicles", vehicles);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }
}
