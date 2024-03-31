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
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/vehicles/delete")
public class VehicleDeleteServlet extends HttpServlet {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long vehicleId = Long.parseLong(request.getParameter("id"));
            List<Reservation> reservations = reservationService.findReservationsByVehicleId(vehicleId);
            for (Reservation reservation : reservations) {
                reservationService.delete(reservation.getId());
            }
            vehicleService.delete(vehicleId);
            response.sendRedirect(request.getContextPath() + "/vehicles/list");
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }
}
