package com.ensta.rentmanager;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ServiceException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
import java.util.Collections;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientDao clientDao;


    @Test
    public void create_should_fail_when_empty_fields() {
        Client clientWithEmptyFields = new Client(1, "", "", "", LocalDate.of(2002, 11, 11));
        // When
        try {
            clientService.create(clientWithEmptyFields);
            fail("Aucune exception n'a été levée pour des champs vides");
        } catch (ServiceException | DaoException e) {
            // Then
            assertEquals("erreur, nom ou prenom nul", e.getMessage());
        }
    }

    @Test
    public void create_should_fail_when_short_name() {
        Client clientWithShortName = new Client(1, "Jo", "Doe", "john.doe@email.com", LocalDate.of(2002, 11, 11));
        // When
        try {
            clientService.create(clientWithShortName);
            fail("Aucune exception n'a été levée pour un nom ou prénom trop court");
        } catch (ServiceException | DaoException e) {
            // Then
            assertEquals("Erreur, nom ou prénom doit contenir au moins 3 caractères", e.getMessage());
        }
    }

    @Test
    public void create_should_fail_when_underage_client() {
        Client underageClient = new Client(1, "John", "Doe", "john.doe@email.com", LocalDate.now().minusYears(17));
        // When
        try {
            clientService.create(underageClient);
            fail("Aucune exception n'a été levée pour un client mineur");
        } catch (ServiceException | DaoException e) {
            // Then
            assertEquals("Vous devez avoir au moins 18 ans pour vous inscrire.", e.getMessage());
        }
    }

    @Test
    public void create_should_fail_when_email_already_exists() throws DaoException {
        // Given
        Client existingClient = new Client(1, "John", "Doe", "john.doe@email.com", LocalDate.of(2000, 1, 1));
        when(clientDao.findAll()).thenReturn(Collections.singletonList(existingClient));
        Client clientWithExistingEmail = new Client(-1, "Jane", "Smith", "john.doe@email.com", LocalDate.of(2002, 11, 11));

        // When
        try {
            clientService.create(clientWithExistingEmail);
            fail("Aucune exception n'a été levée pour une adresse e-mail déjà existante");
        } catch (ServiceException e) {
            // Then
            assertEquals("Cette adresse e-mail est déjà utilisée par un autre client.", e.getMessage());
        }
    }

    @Test
    public void create_should_fail_when_invalid_email_format() {
        Client clientWithInvalidEmail = new Client(1, "John", "Doe", "invalid-email", LocalDate.of(2002, 11, 11));
        // When
        try {
            clientService.create(clientWithInvalidEmail);
            fail("Aucune exception n'a été levée pour une adresse e-mail invalide");
        } catch (ServiceException | DaoException e) {
            // Then
            assertEquals("Cette adresse e-mail n'est pas valide.", e.getMessage());
        }
    }

    @Test
    public void create_should_succeed_when_valid_client() throws ServiceException, DaoException {
        // Given
        Client validClient = new Client(1, "John", "Doe", "john.doe@email.com", LocalDate.of(1990, 1, 1));
        when(clientDao.findAll()).thenReturn(Collections.emptyList());

        // When
        long clientId = clientService.create(validClient);

        // Then
        assertEquals(0, clientId);
    }

}
