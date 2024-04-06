package com.epf.rentmanager.servlet.Reservation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

@WebServlet("/reservations/edit")
public class ReservationEditServlet extends HttpServlet {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private VehicleService vehicleService;



    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {
        try {
            long reservationId = Long.parseLong(request.getParameter("id"));
            Reservation reservation = null;
            reservation = reservationService.findById(reservationId);
            Client client = clientService.findById(reservation.getClient_id());
            Vehicle vehicle = vehicleService.findById(reservation.getVehicle_id());
            List<Client> clients = clientService.findAll();
            List<Vehicle> vehicles = vehicleService.findAll();

            request.setAttribute("clients", clients);
            request.setAttribute("vehicles", vehicles);
            request.setAttribute("clientresa", client);
            request.setAttribute("vehicleresa", vehicle);
            request.setAttribute("reservation", reservation);
            request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);

        } catch (ServiceException | DaoException e) {
            throw new RuntimeException(e);
        }
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long reservationId = Long.parseLong(request.getParameter("id"));
            long clientId = Long.parseLong(request.getParameter("client_id"));
            long vehicleId = Long.parseLong(request.getParameter("vehicle_id"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String startDateString = request.getParameter("start_date");
            String endDateString = request.getParameter("end_date");

            List<Reservation> allreservations = reservationService.findAll();
            List<Reservation> allReservationsvehhicle = reservationService.findReservationsByVehicleId(vehicleId);
            List<Client> clients = clientService.findAll();
            List<Vehicle> vehicles = vehicleService.findAll();

            List<Reservation> clientReservations = reservationService.findReservationsByClientId(clientId);
            List<Reservation> vehicleReservations = new ArrayList<>();
            for (Reservation reservation : clientReservations) {
                if (reservation.getVehicle_id() == vehicleId) {
                    vehicleReservations.add(reservation);
                }
            }

            if (!reservationService.isReservationStartDateFormatValid(startDateString)) {
                request.setAttribute("errorMessageStartDateFormat", "Le format de la date est invalide. Veuillez utiliser le format jj/mm/aaaa.");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (!reservationService.isReservationEndDateFormatValid(endDateString)) {
                request.setAttribute("errorMessageEndDateFormat", "Le format de la date est invalide. Veuillez utiliser le format jj/mm/aaaa.");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp");
                dispatcher.forward(request, response);
                return;
            }
            LocalDate startDate = LocalDate.parse(request.getParameter("start_date"),formatter);
            LocalDate endDate = LocalDate.parse(request.getParameter("end_date"),formatter);

            if (!reservationService.isReservationDateValid(startDate, endDate)) {
                request.setAttribute("errorMessageDateValid", "Le format de la date est invalide. La date de début doit être antérieure à celle de fin.");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (!reservationService.isReservationAvailable(startDate, endDate, vehicleId,allreservations)) {
                request.setAttribute("StartDateErrorMessage", "Cette voiture est déjà réservée pour cette période.");

                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (!reservationService.isReservationDurationValid(startDate, endDate, vehicleReservations)) {
                request.setAttribute("ConsecutiveDaysErrorMessage", "Vous ne pouvez pas réserver cette voiture plus de 7 jours de suite (y compris sur une reservation différente).");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (!reservationService.isReservationDurationVehicleValid(startDate, endDate, allReservationsvehhicle)) {
                request.setAttribute("ConsecutiveDaysVehicleErrorMessage", "Vous ne pouvez pas réserver cette voiture car elle atteindra 30 jours de suite (y compris sur une reservation différente).");
                request.setAttribute("clients", clients);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("clientSelected", clientService.findById(clientId));
                request.setAttribute("vehicleSelected", vehicleService.findById(vehicleId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/edit.jsp");
                dispatcher.forward(request, response);
                return;
            }
            Reservation reservation = new Reservation(reservationId, clientId, vehicleId,  startDate, endDate);
            reservationService.update(reservation);
            response.sendRedirect(request.getContextPath() + "/reservations/list");
        } catch (NumberFormatException | ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }
}
