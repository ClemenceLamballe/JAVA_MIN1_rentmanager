package com.epf.rentmanager.servlet.client;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;



@WebServlet("/users/edit")
public class ClientEditServlet extends HttpServlet {

    @Autowired
    private ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("debut");
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long clientId = Long.parseLong(request.getParameter("id"));
            Client client = clientService.findById(clientId);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp").forward(request, response);
        } catch (NumberFormatException | DaoException e) {
            e.printStackTrace();

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long clientId = Long.parseLong(request.getParameter("id"));
            String nom = request.getParameter("last_name");
            String prenom = request.getParameter("first_name");
            String email = request.getParameter("email");
            LocalDate naissance = LocalDate.parse(request.getParameter("birthdate"));
            Client client = new Client(clientId, nom, prenom, email, naissance);
            clientService.update(client);
            response.sendRedirect(request.getContextPath() + "/clients/list");
        } catch (NumberFormatException | ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }

}
