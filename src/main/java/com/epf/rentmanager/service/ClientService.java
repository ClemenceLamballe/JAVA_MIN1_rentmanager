package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;

	private ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
	}

	
	
	public long create(Client client) throws ServiceException, DaoException {
		if (client.getNom().isEmpty() || client.getPrenom().isEmpty()) {
			throw new ServiceException("erreur, nom ou prenom nul");
		}

		if (client.getNom().length() < 3 || client.getPrenom().length() < 3) {
			throw new ServiceException("Erreur, nom ou prénom doit contenir au moins 3 caractères");
		}

		LocalDate now = LocalDate.now();
		LocalDate birthdate = client.getNaissance();
		if (birthdate.plusYears(18).isAfter(now)) {
			throw new ServiceException("Vous devez avoir au moins 18 ans pour vous inscrire.");
		}

		List<Client> clients = clientDao.findAll();
		for (Client existingClient : clients) {
			if (existingClient.getEmail().equals(client.getEmail())) {
				throw new ServiceException("Cette adresse e-mail est déjà utilisée par un autre client.");
			}
		}

		client.setNom(client.getNom().toUpperCase());
		return clientDao.create(client);
	}
	public void delete(long clientId) throws ServiceException, DaoException {
		Client clientToDelete = this.findById(clientId);
		if (clientToDelete == null) {
			throw new ServiceException("erreur dans la suppression d'un client : client nul");
		}

		try {
			clientDao.delete(clientToDelete);
		} catch (DaoException e) {
			throw new ServiceException("erreur dans la suppression d'un client");
		}
	}
	public Client findById(long id) throws DaoException {
		// TODO: récupérer un client par son id
		return clientDao.findById(id);
	}

	public List<Client> findAll() throws ServiceException, DaoException {
		// TODO: récupérer tous les clients
		return clientDao.findAll();
	}

	public int count() throws ServiceException, DaoException {
		return clientDao.countClients();
	}

	public void update(Client client) throws ServiceException, DaoException {
		try {
			clientDao.update(client);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors de la mise à jour du client");
		}
	}


}
