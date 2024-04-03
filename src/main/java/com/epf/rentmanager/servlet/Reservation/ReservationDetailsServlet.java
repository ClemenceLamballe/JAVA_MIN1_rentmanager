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

@WebServlet("/reservations/details")
public class ReservationDetailsServlet extends HttpServlet {
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long reservationId = Long.parseLong(request.getParameter("id"));

        try {
            List<Reservation> reservations = new ArrayList<>();
            List<Client> clients = new ArrayList<>();
            List<Vehicle> vehicles = new ArrayList<>();

            Reservation reservation = reservationService.findById(reservationId);

            if (reservation != null) {
                reservations.add(reservation);

                Client client = clientService.findById(reservation.getClient_id());
                clients.add(client);

                Vehicle vehicle = vehicleService.findById(reservation.getVehicle_id());
                vehicles.add(vehicle);

                request.setAttribute("reservation", reservation);
                request.setAttribute("client", client);
                request.setAttribute("vehicle", vehicle);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/details.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServiceException | DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
