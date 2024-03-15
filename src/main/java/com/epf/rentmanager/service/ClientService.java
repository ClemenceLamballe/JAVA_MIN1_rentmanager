package com.epf.rentmanager.service;

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


		// On empêchera la création ou la mise à jour d’un Client si son
		//nom/prenom est vide.
		if (client.getNom().isEmpty() || client.getPrenom().isEmpty()) {
			throw new ServiceException();
		}

		// nom de famille en MAJUSCULES
		client.setNom(client.getNom().toUpperCase());

		// TODO: créer un client dans la base de données


		return clientDao.create(client);
	}
	public void delete(long clientId) throws ServiceException, DaoException {
		Client clientToDelete = this.findById(clientId);
		if (clientToDelete == null) {
			throw new ServiceException();
		}

		try {
			clientDao.delete(clientToDelete);
		} catch (DaoException e) {
			throw new ServiceException();
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


}
