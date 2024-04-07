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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Autowired
	VehicleService vehicleService;
	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;

	/**
	 * @throws ServletException
	 */
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
		try {

			int numberOfVehicles = vehicleService.count();
			request.setAttribute("numberOfVehicles", numberOfVehicles);


			int numberOfClients = clientService.count();
			request.setAttribute("numberOfClients", numberOfClients);


			int numberOfReservations = reservationService.count();
			request.setAttribute("numberOfReservations", numberOfReservations);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException | DaoException e) {
			e.printStackTrace();
		}
	}

}
