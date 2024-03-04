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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;

@WebServlet("/reservations/list")
public class ReservationListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReservationService reservationService = ReservationService.getInstance();
        try {
            List<Reservation> reservations = reservationService.findAll();
            request.setAttribute("reservations", reservations);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException | DaoException e) {
            // Gérer l'exception (par exemple, rediriger vers une page d'erreur)
            e.printStackTrace(); // Pour l'instant, affichez simplement la trace de la pile.
        }
    }
}
