package com.epf.rentmanager.servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epf.rentmanager.service.*;

import com.epf.rentmanager.dao.DaoException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			VehicleService vehicleService = VehicleService.getInstance();
			int numberOfVehicles = vehicleService.count();
			request.setAttribute("numberOfVehicles", numberOfVehicles);

			ClientService clientService = ClientService.getInstance();
			int numberOfClients = clientService.count();
			request.setAttribute("numberOfClients", numberOfClients);

			ReservationService reservationService = ReservationService.getInstance();
			int numberOfReservations = reservationService.count();
			request.setAttribute("numberOfReservations", numberOfReservations);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException | DaoException e) {
			// Gérer l'exception (par exemple, rediriger vers une page d'erreur)
			e.printStackTrace(); // Pour l'instant, affichez simplement la trace de la pile.
		}
	}

}
