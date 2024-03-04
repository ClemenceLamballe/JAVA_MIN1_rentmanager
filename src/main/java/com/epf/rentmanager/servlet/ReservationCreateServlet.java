package com.epf.rentmanager.servlet;

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
import java.util.List;
@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            List<Client> clients = ClientService.getInstance().findAll();
            List<Vehicle> vehicles = VehicleService.getInstance().findAll();

            request.setAttribute("clients", clients);
            request.setAttribute("vehicles", vehicles);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
        dispatcher.forward(request, response);
        } catch (ServiceException | DaoException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve reservation details from the form
            long clientId = Long.parseLong(request.getParameter("client_id"));
            long vehicleId = Long.parseLong(request.getParameter("vehicle_id"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate startDate = LocalDate.parse(request.getParameter("start_date"), formatter);
            LocalDate endDate = LocalDate.parse(request.getParameter("end_date"), formatter);


            // Create a new Reservation object
            Reservation newReservation = new Reservation(-1, clientId, vehicleId, startDate, endDate);

            // Call the create method in the service layer
            ReservationService reservationService = ReservationService.getInstance();
            long generatedId = reservationService.create(newReservation);

            // Update the ID after creation
            newReservation.setId(generatedId);

            // Redirect to the reservation list page
            response.sendRedirect(request.getContextPath() + "/reservations/list");
        } catch (ServiceException | DaoException e) {
            // Handle exceptions (e.g., redirect to an error page)
            e.printStackTrace(); // For now, print the stack trace.
        }
    }
}
