package com.epf.rentmanager.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/users/details")
public class ClientDetailsServlet extends HttpServlet {
    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long clientId = Long.parseLong(request.getParameter("id"));


        try{
        Client client = clientService.findById(clientId);

        if (client != null) {
            // Ajouter l'objet Client à la requête
            request.setAttribute("client", client);

            // Dispatcher vers la page des détails du client
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
            dispatcher.forward(request, response);
        }



        } catch (NumberFormatException | DaoException e) {
            e.printStackTrace();  // Gérer l'exception
        }

    }
}
