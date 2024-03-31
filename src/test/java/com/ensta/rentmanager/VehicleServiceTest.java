package com.ensta.rentmanager;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VehicleServiceTest {

    @Mock
    private VehicleDao vehicleDaoMock;

    private VehicleService vehicleService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vehicleService = new VehicleService(vehicleDaoMock);
    }

    @Test
    public void testCreateVehicle() throws DaoException, ServiceException {
        Vehicle vehicle = new Vehicle("Renault", "Clio", 5);
        when(vehicleDaoMock.create(vehicle)).thenReturn(1L);

        long result = vehicleService.create(vehicle);

        assertEquals(1L, result);
        verify(vehicleDaoMock, times(1)).create(vehicle);
    }

    @Test(expected = ServiceException.class)
    public void testCreateVehicle_WithEmptyFields() throws DaoException, ServiceException {
        Vehicle vehicle = new Vehicle("", "", 0);

        vehicleService.create(vehicle);
    }

    @Test(expected = ServiceException.class)
    public void testCreateVehicle_WithInvalidNbPlaces() throws DaoException, ServiceException {
        Vehicle vehicle = new Vehicle("Renault", "Clio", 1);

        vehicleService.create(vehicle);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteVehicle_WhenVehicleNotFound() throws DaoException, ServiceException {
        long vehicleId = 1L;
        when(vehicleDaoMock.findById(vehicleId)).thenReturn(null);

        vehicleService.delete(vehicleId);
    }

    @Test
    public void testDeleteVehicle() throws DaoException, ServiceException {
        long vehicleId = 1L;
        Vehicle vehicleToDelete = new Vehicle("Renault", "Clio", 5);
        when(vehicleDaoMock.findById(vehicleId)).thenReturn(vehicleToDelete);

        vehicleService.delete(vehicleId);

        verify(vehicleDaoMock, times(1)).delete(vehicleToDelete);
    }

    @Test
    public void testFindAllVehicles() throws DaoException, ServiceException {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("Renault", "Clio", 5));
        vehicles.add(new Vehicle("Peugeot", "208", 5));
        when(vehicleDaoMock.findAll())
