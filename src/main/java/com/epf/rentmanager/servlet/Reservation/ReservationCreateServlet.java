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
            System.out.println("je commence le doPost");
            long clientId = Long.parseLong(request.getParameter("client_id"));
            long vehicleId = Long.parseLong(request.getParameter("vehicle_id"));
            System.out.println("on vérifie le client "+clientService.findById(clientId).getNom());
            System.out.println("avec la voiture "+vehicleService.findById(vehicleId).getModele());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String startDateString = request.getParameter("start_date");
            String endDateString = request.getParameter("end_date");
            System.out.println("string acquis"+endDateString);


            List<Reservation> allreservations = reservationService.findAll();
            List<Client> clients = clientService.findAll();
            List<Vehicle> vehicles = vehicleService.findAll();

            List<Reservation> allReservationsvehhicle = reservationService.findReservationsByVehicleId(vehicleId);
            System.out.println("la voiture a déjà "+allReservationsvehhicle.size()+" reservations");


            List<Reservation> clientReservations = reservationService.findReservationsByClientId(clientId);
            List<Reservation> vehicleReservations = new ArrayList<>();
            for (Reservation reservation : clientReservations) {
                if (reservation.getVehicle_id() == vehicleId) {
                    vehicleReservations.add(reservation);
                }
            }
            System.out.println("dont"+vehicleReservations.size()+" avec ce client");

            System.out.println("le format");
            if (!reservationService.isReservationStartDateFormatValid(startDateString)) {
                request.setAttribute("errorMessageStartDateFormat", "Le format de la date est invalide. Veuillez utiliser le format jj/mm/aaaa.");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
                dispatcher.forward(request, response);
                return;
            }
            System.out.println("le format");

            if (!reservationService.isReservationEndDateFormatValid(endDateString)) {
                request.setAttribute("errorMessageEndDateFormat", "Le format de la date est invalide. Veuillez utiliser le format jj/mm/aaaa.");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
                dispatcher.forward(request, response);
                return;
            }
            LocalDate startDate = LocalDate.parse(request.getParameter("start_date"),formatter);
            LocalDate endDate = LocalDate.parse(request.getParameter("end_date"),formatter);

            System.out.println("la validité dates");

            if (!reservationService.isReservationDateValid(startDate, endDate)) {
                request.setAttribute("errorMessageDateValid", "Le format de la date est invalide. La date de début doit être antérieure à celle de fin.");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            System.out.println("la validité dates possible au niveau de la voiture");

            if (!reservationService.isReservationAvailable(startDate, endDate, vehicleId,allreservations)) {
                request.setAttribute("StartDateErrorMessage", "Cette voiture est déjà réservée pour cette période.");

                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            System.out.println("validité 7j");

            if (!reservationService.isReservationDurationValid(startDate, endDate, vehicleReservations)) {
                request.setAttribute("ConsecutiveDaysErrorMessage", "Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite (y compris sur une reservation différente).");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            System.out.println("validité 7j");

            if (!reservationService.isReservationDurationVehicleValid(startDate, endDate, allReservationsvehhicle)) {
                request.setAttribute("ConsecutiveDaysVehicleErrorMessage", "Vous ne pouvez pas réserver cette voiture car elle atteindra 30 jours de suite (y compris sur une reservation différente).");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Reservation newReservation = new Reservation(-1, clientId, vehicleId, startDate, endDate);
            long generatedId = reservationService.create(newReservation);
            newReservation.setId(generatedId);
            response.sendRedirect(request.getContextPath() + "/reservations/list");
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }

}
