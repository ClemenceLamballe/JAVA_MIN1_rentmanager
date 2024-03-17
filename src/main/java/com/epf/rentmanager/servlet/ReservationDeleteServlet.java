package com.epf.rentmanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/reservations/delete")
public class ReservationDeleteServlet extends HttpServlet {

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
            long reservationId = Long.parseLong(request.getParameter("id"));
            reservationService.delete(reservationId);
            response.sendRedirect(request.getContextPath() + "/reservations/list");
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();

        }
    }
}
