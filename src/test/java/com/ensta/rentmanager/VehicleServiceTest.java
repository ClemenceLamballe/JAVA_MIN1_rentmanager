package com.ensta.rentmanager;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleDao vehicleDao;

    @Test
    public void create_should_fail_when_constructeur_empty() {
        Vehicle vehicle = new Vehicle(1, "", "modele", 4);
        try {
            vehicleService.create(vehicle);
            fail("Aucune exception n'a été levée pour un constructeur vide");
        } catch (ServiceException | DaoException e) {
            assertEquals("Le véhicule doit avoir un constructeur spécifié.", e.getMessage());
        }
    }

    @Test
    public void create_should_fail_when_modele_empty() {
        Vehicle vehicle = new Vehicle(1, "constructeur", "", 4);
        try {
            vehicleService.create(vehicle);
            fail("Aucune exception n'a été levée pour un modèle vide");
        } catch (ServiceException | DaoException e) {
            assertEquals("Le véhicule doit avoir un modèle spécifié.", e.getMessage());
        }
    }

    @Test
    public void create_should_fail_when_nb_places_out_of_range() {
        Vehicle vehicle = new Vehicle(1, "constructeur", "modele", 1);
        try {
            vehicleService.create(vehicle);
            fail("Aucune exception n'a été levée pour un nombre de places hors plage");
        } catch (ServiceException | DaoException e) {
            assertEquals("Le nombre de places dans le véhicule doit être compris entre 2 et 9.", e.getMessage());
        }
    }

    @Test
    public void create_should_succeed_when_vehicle_valid() throws ServiceException, DaoException {
        Vehicle vehicle = new Vehicle(1, "constructeur", "modele", 4);
        when(vehicleDao.create(vehicle)).thenReturn(1L);
        long generatedId = vehicleService.create(vehicle);
        assertEquals(1L, generatedId);
    }
}
