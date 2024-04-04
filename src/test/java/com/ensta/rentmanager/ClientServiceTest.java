package com.ensta.rentmanager;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ServiceException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;


import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientDao clientDao;

    @Test
    public void create_should_return_generated_id_when_client_valid() throws ServiceException, DaoException {

        Client client = new Client(-1,"John", "Doe", "john.doe@email.com",LocalDate.of(2002, 11, 11));
        when(clientDao.create(client)).thenReturn(1L);
        long generatedId = clientService.create(client);
        assertEquals(1L, generatedId);
    }

    @Test(expected = ServiceException.class)
    public void create_should_throw_service_exception_when_empty_fields() throws ServiceException, DaoException {

        Client client = new Client(-1,"", "", "",LocalDate.of(2002, 11, 11));
        clientService.create(client);
    }

    @Test(expected = ServiceException.class)
    public void create_should_throw_service_exception_when_underage_client() throws ServiceException, DaoException {
        Client client = new Client(-1, "John", "Doe", "john.doe@email.com", LocalDate.of(2022, 11, 11));
        clientService.create(client);
    }
}
