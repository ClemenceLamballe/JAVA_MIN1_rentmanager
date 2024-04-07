package com.epf.rentmanager.servlet.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String email = request.getParameter("email");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String birthdateString = request.getParameter("birthdate");


            try {
                LocalDate birthdate = LocalDate.parse(birthdateString, dateFormatter);
            } catch (DateTimeParseException e) {
                request.setAttribute("BirthdateErrorMessage", "Le format de date n'est pas valide.");
                request.setAttribute("lastName", lastName);
                request.setAttribute("firstName", firstName);
                request.setAttribute("email", email);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
                dispatcher.forward(request, response);
                return;

            }

            LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));

            List<Client> clients = clientService.findAll();
            for (Client existingClient : clients) {
                if (existingClient.getEmail().equals(email)) {
                    request.setAttribute("EmailErrorMessage", "Cette adresse e-mail est déjà utilisée par un autre client.");
                    request.setAttribute("lastName", lastName);
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("birthdate", birthdate);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }

            if (firstName.length() < 3 ) {
                request.setAttribute("NameErrorMessage", "Le nom et le prénom doivent contenir au moins 3 caractères.");
                request.setAttribute("lastName", lastName);
                request.setAttribute("email", email);
                request.setAttribute("birthdate", birthdate);

            }

            if ( lastName.length() < 3) {
                request.setAttribute("LastNameErrorMessage", "Le nom et le prénom doivent contenir au moins 3 caractères.");
                request.setAttribute("firstName", firstName);
                request.setAttribute("email", email);
                request.setAttribute("birthdate", birthdate);

            }


            LocalDate now = LocalDate.now();
            if (birthdate.plusYears(18).isAfter(now)) {
                request.setAttribute("BirthdateErrorMessage", "Vous devez avoir au moins 18 ans pour vous inscrire.");
                request.setAttribute("lastName", lastName);
                request.setAttribute("firstName", firstName);
                request.setAttribute("email", email);


            }


            if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")) {
                request.setAttribute("EmailErrorMessage", "L'adresse e-mail n'est pas valide.");
                request.setAttribute("lastName", lastName);
                request.setAttribute("firstName", firstName);
                request.setAttribute("birthdate", birthdate);

            }




            if ( firstName.length() < 3 || lastName.length() < 3|| birthdate.plusYears(18).isAfter(now)|| !email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
                dispatcher.forward(request, response);
                return;
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
