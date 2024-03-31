package com.epf.rentmanager.servlet;

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

@WebServlet("/users/details")
public class ClientDetailsServlet extends HttpServlet {
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long clientId = Long.parseLong(request.getParameter("id"));
        int reservationsCount = 0;
        int vehiclesCount = 0;

        try{
        Client client = clientService.findById(clientId);

        if (client != null) {
            // Récupérer les réservations du client
            List<Reservation> reservations = reservationService.findReservationsByClientId(clientId);
            System.out.println("cpt"+ reservations.size());
            List<Vehicle> vehicles = new ArrayList<>();
            List<String> vehicleManufacturers = new ArrayList<>();

            for (Reservation reservation : reservations) {
                long vehicleId = reservation.getVehicle_id();
                Vehicle vehicle = vehicleService.findById(vehicleId);
                vehicles.add(vehicle);
                vehicleManufacturers.add(vehicle.getConstructeur());
            }

            reservationsCount = reservations.size();
            vehiclesCount = vehicles.size();


            request.setAttribute("reservationsCount", reservationsCount);
            request.setAttribute("vehiclesCount", vehiclesCount);
            request.setAttribute("reservations", reservations);
            request.setAttribute("vehicles", vehicles);

            request.setAttribute("clientPrenom", client.getPrenom());
            request.setAttribute("clientNom", client.getNom());
            request.setAttribute("clientEmail", client.getEmail());

            request.setAttribute("vehicleManufacturers", vehicleManufacturers);


            // Dispatcher vers la page des détails du client
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
            dispatcher.forward(request, response);
        }



        } catch (NumberFormatException | DaoException | ServiceException e) {
            e.printStackTrace();  // Gérer l'exception
        }

    }
}
