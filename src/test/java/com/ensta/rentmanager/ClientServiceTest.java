package com.ensta.rentmanager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import org.junit.Before;
import org.junit.Test;

public class ClientServiceTest {

    private ClientService clientService;
    private ClientDao mockClientDao;

    @Before
    public void setUp() {
        mockClientDao = mock(ClientDao.class);
        clientService = new ClientService(mockClientDao);
    }

    @Test
    public void testCreateClient() throws ServiceException, DaoException {
        Client client = new Client(1, "John", "Doe", "john.doe@email.com");
        when(mockClientDao.create(client)).thenReturn(1L);

        long clientId = clientService.create(client);

        assertEquals(1L, clientId);
    }

    @Test(expected = ServiceException.class)
    public void testCreateClientWithEmptyName() throws ServiceException, DaoException {
        Client client = new Client(1, "", "Doe", "john.doe@email.com");
        clientService.create(client);
    }

    @Test
    public void testDeleteClient() throws ServiceException, DaoException {
        long clientId = 1;
        Client client = new Client(clientId, "John", "Doe", "john.doe@email.com");
        when(mockClientDao.findById(clientId)).thenReturn(client);

        clientService.delete(clientId);

        verify(mockClientDao, times(1)).delete(client);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteNonExistingClient() throws ServiceException, DaoException {
        long clientId = 1;
        when(mockClientDao.findById(clientId)).thenReturn(null);

        clientService.delete(clientId);
    }

    @Test
    public void testFindClientById() throws DaoException {
        long clientId = 1;
        Client expectedClient = new Client(clientId, "John", "Doe", "john.doe@email.com");
        when(mockClientDao.findById(clientId)).thenReturn(expectedClient);

        Client actualClient = clientService.findById(clientId);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    public void testFindAllClients() throws ServiceException, DaoException {
        List<Client> expectedClients = new ArrayList<>();
        expectedClients.add(new Client(1, "John", "Doe", "john.doe@email.com"));
        expectedClients.add(new Client(2, "Jane", "Smith", "jane.smith@email.com"));
        when(mockClientDao.findAll()).thenReturn(expectedClients);

        List<Client> actualClients = clientService.findAll();

        assertEquals(expectedClients.size(), actualClients.size());
        assertEquals(expectedClients, actualClients);
    }

    @Test
    public void testCountClients() throws ServiceException, DaoException {
        int expectedCount = 5;
        when(mockClientDao.countClients()).thenReturn(expectedCount);

        int actualCount = clientService.count();

        assertEquals(expectedCount, actualCount);
    }
}
