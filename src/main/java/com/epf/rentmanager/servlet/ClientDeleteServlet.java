package com.epf.rentmanager.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/users/delete")
public class ClientDeleteServlet extends HttpServlet {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long clientId = Long.parseLong(request.getParameter("id"));
        try {
            List<Reservation> reservations = reservationService.findReservationsByClientId(clientId);
            for (Reservation reservation : reservations) {
                reservationService.delete(reservation.getId());
            }
            clientService.delete(clientId);
            // Rediriger vers une page de confirmation ou une autre page
            response.sendRedirect(request.getContextPath() + "/clients/list"); // Redirige vers la liste des utilisateurs par exemple
        } catch (NumberFormatException | ServiceException | DaoException e) {
            e.printStackTrace();  // Gérer l'exception
            // Rediriger vers une page d'erreur

        }
    }
}
