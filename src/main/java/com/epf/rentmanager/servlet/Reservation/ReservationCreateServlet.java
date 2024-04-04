package com.epf.rentmanager.servlet.Reservation;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;

import java.time.format.DateTimeFormatter;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.ArrayList;
import java.util.List;
@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            List<Client> clients = clientService.findAll();
            List<Vehicle> vehicles = vehicleService.findAll();
            request.setAttribute("clients", clients);
            request.setAttribute("vehicles", vehicles);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
        dispatcher.forward(request, response);
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long clientId = Long.parseLong(request.getParameter("client_id"));
            long vehicleId = Long.parseLong(request.getParameter("vehicle_id"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(request.getParameter("start_date"), formatter);
            LocalDate endDate = LocalDate.parse(request.getParameter("end_date"), formatter);

            List<Reservation> allreservations = reservationService.findAll();
            List<Reservation> allReservationsvehhicle = reservationService.findReservationsByVehicleId(vehicleId);

            System.out.println("verif availble");
            if (!reservationService.isReservationAvailable(startDate, endDate, vehicleId)) {
                request.setAttribute("StartDateErrorMessage", "Cette voiture est déjà réservée pour cette période.");
                List<Client> clients = clientService.findAll();
                List<Vehicle> vehicles = vehicleService.findAll();
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Reservation> clientReservations = reservationService.findReservationsByClientId(clientId);
            List<Reservation> vehicleReservations = new ArrayList<>();
            for (Reservation reservation : clientReservations) {
                if (reservation.getVehicle_id() == vehicleId) {
                    vehicleReservations.add(reservation);
                }
            }

            System.out.println("verif 7j");

            if (!reservationService.isReservationDurationValid(startDate, endDate, vehicleReservations)) {
                System.out.println("pas valide / 7j");
                request.setAttribute("ConsecutiveDaysErrorMessage", "Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite (y compris sur une reservation différente).");
                List<Client> clients = clientService.findAll();
                List<Vehicle> vehicles = vehicleService.findAll();
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
                dispatcher.forward(request, response);
                return;
            }


            System.out.println("verif 30j");

            if (!reservationService.isReservationDurationVehicleValid(startDate, endDate, allReservationsvehhicle)) {
                System.out.println("pas valide / 30j");
                request.setAttribute("ConsecutiveDaysVehicleErrorMessage", "Vous ne pouvez pas réserver cette voiture car elle atteindra 30 jours de suite (y compris sur une reservation différente).");
                List<Client> clients = clientService.findAll();
                List<Vehicle> vehicles = vehicleService.findAll();
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
                dispatcher.forward(request, response);
                return;
            }


                System.out.println("fin verif");

            Reservation newReservation = new Reservation(-1, clientId, vehicleId, startDate, endDate);
            long generatedId = reservationService.create(newReservation);
            newReservation.setId(generatedId);
            response.sendRedirect(request.getContextPath() + "/reservations/list");
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }

}
