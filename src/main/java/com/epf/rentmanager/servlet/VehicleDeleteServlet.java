package com.epf.rentmanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/vehicles/delete")
public class VehicleDeleteServlet extends HttpServlet {

    @Autowired
    private VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        try {
            System.out.println("hello");
            long vehicleId = Long.parseLong(request.getParameter("id"));
            System.out.println("id v "+vehicleId);
            vehicleService.delete(vehicleId);
            System.out.println("sup ");
            response.sendRedirect(request.getContextPath() + "/vehicles/list");
            System.out.println("redig ");
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }
}
