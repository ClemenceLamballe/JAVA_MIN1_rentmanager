package com.epf.rentmanager.servlet.Vehicle;

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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/vehicles/details")
public class VehicleDetailsServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long vehicleId = Long.parseLong(request.getParameter("id"));

        List<Reservation> reservations = new ArrayList<>();
        List<Client> clients = new ArrayList<>();
        List<Client> clientsreservation = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();

        try {
            Vehicle vehicle = vehicleService.findById(vehicleId);

            if (vehicle != null) {
                reservations = reservationService.findReservationsByVehicleId(vehicleId);

                if (!reservations.isEmpty()) {
                    for (Reservation reservation : reservations) {
                        long vehicleIdlist = reservation.getVehicle_id();
                        Vehicle vehiclelist = vehicleService.findById(vehicleIdlist);
                        vehicles.add(vehiclelist);

                        long clientId = reservation.getClient_id();
                        Client clientR = clientService.findById(clientId);
                        clientsreservation.add(clientR);
                        boolean clientExists = false;
                        for (Client existingClient : clients) {
                            if (existingClient.getId() == clientId) {
                                clientExists = true;
                                break;
                            }
                        }
                        if (!clientExists) {
                            Client client = clientService.findById(clientId);
                            clients.add(client);
                        }
                    }
                }

                request.setAttribute("reservations", reservations);
                request.setAttribute("vehicle", vehicle);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clients", clients);
                request.setAttribute("clientsreservation", clientsreservation);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServiceException | DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
