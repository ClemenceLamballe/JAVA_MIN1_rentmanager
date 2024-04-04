package com.epf.rentmanager.servlet.client;

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
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String email = request.getParameter("email");
            LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));

            if (firstName.length() < 3 ) {
                request.setAttribute("NameErrorMessage", "Le nom et le prénom doivent contenir au moins 3 caractères.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if ( lastName.length() < 3) {
                request.setAttribute("LastNameErrorMessage", "Le nom et le prénom doivent contenir au moins 3 caractères.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            LocalDate now = LocalDate.now();
            if (birthdate.plusYears(18).isAfter(now)) {
                request.setAttribute("BirthdateErrorMessage", "Vous devez avoir au moins 18 ans pour vous inscrire.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Client> clients = clientService.findAll();
            for (Client existingClient : clients) {
                if (existingClient.getEmail().equals(email)) {
                    request.setAttribute("EmailErrorMessage", "Cette adresse e-mail est déjà utilisée par un autre client.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }

            Client newClient = new Client(-1, lastName, firstName, email, birthdate);
            long generatedId = clientService.create(newClient);
            newClient.setId(generatedId);
            response.sendRedirect(request.getContextPath() + "/clients/list");
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }
}
