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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display the create client form
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve client details from the form
            String firstName = request.getParameter("first_name");

            String lastName = request.getParameter("last_name");

            String email = request.getParameter("email");
            LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));

            // Create a new Client object
            Client newClient = new Client(-1, lastName, firstName, email, birthdate);

            // Call the create method in the service layer
            //ClientService clientService = ClientService.getInstance();
            long generatedId = clientService.create(newClient);

            // Update the ID after creation
            newClient.setId(generatedId);

            // Redirect to the client list page
            response.sendRedirect(request.getContextPath() + "/clients/list");
        } catch (ServiceException | DaoException e) {
            // Handle exceptions (e.g., redirect to an error page)
            e.printStackTrace(); // For now, print the stack trace.
        }
    }
}
