package com.epf.rentmanager.servlet.Reservation;

import java.io.IOException;
import java.time.LocalDate;
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
            long vehicleId = Long.parseLong(request.getParameter("vehicle_id"));
            long clientId = Long.parseLong(request.getParameter("client_id"));
            LocalDate startDate = LocalDate.parse(request.getParameter("start_date"));
            LocalDate endDate = LocalDate.parse(request.getParameter("end_date"));



            Reservation reservation = new Reservation(reservationId, clientId, vehicleId,  startDate, endDate);
            reservationService.update(reservation);
            response.sendRedirect(request.getContextPath() + "/reservations/list");
        } catch (NumberFormatException | ServiceException e) {
            e.printStackTrace();
        }
    }
}
