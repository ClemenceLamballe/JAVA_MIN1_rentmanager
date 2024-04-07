package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;

	private VehicleService(VehicleDao vehicleDao){
		this.vehicleDao = vehicleDao;
	}


	/**
	 * @param vehicle
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public long create(Vehicle vehicle) throws ServiceException, DaoException {
		try {
			this.validate(vehicle);
			/*
			if (vehicle.getConstructeur().isEmpty()) {
				throw new ServiceException("Le véhicule doit avoir un constructeur spécifié.");
			}

			if (vehicle.getModele().isEmpty()) {
				throw new ServiceException("Le véhicule doit avoir un modèle spécifié.");
			}

			if (vehicle.getNb_places() < 2 || vehicle.getNb_places() > 9) {
				throw new ServiceException("Le nombre de places dans le véhicule doit être compris entre 2 et 9.");
			}*/

			return vehicleDao.create(vehicle);
		} catch (DaoException e) {
			throw new DaoException("Erreur lors de la création du véhicule", e);
		}
	}

	/**
	 * @param vehicleId
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public void delete(long vehicleId) throws ServiceException, DaoException {

		try {
			Vehicle vehicleToDelete = this.findById(vehicleId);
			 vehicleDao.delete(vehicleToDelete );
		} catch (DaoException e) {
			throw new ServiceException("Erreur dans la suppression de vehicule.",e);
		}
	}

	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public Vehicle findById(long id) throws ServiceException, DaoException {
		try{
			return vehicleDao.findById(id);
		}catch (DaoException e) {
			throw new ServiceException("Erreur pour trouver le vehicule.",e);
		}

    }

	/**
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public List<Vehicle> findAll() throws ServiceException, DaoException {
		try{
			return vehicleDao.findAll();
		}catch (DaoException e) {
			throw new ServiceException("Erreur pour trouver tous les vehicules.",e);
		}


    }

	/**
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public int count() throws ServiceException, DaoException {
		try {
			return vehicleDao.count();
		}catch (DaoException e) {
			throw new ServiceException("Erreur pour compter les vehicules.",e);
		}

	}


	/**
	 * @param vehicle
	 * @throws ServiceException
	 */
	public void update(Vehicle vehicle) throws ServiceException {
		try {
			this.validate(vehicle);
			/*
			if (vehicle.getConstructeur().isEmpty()) {
				throw new ServiceException("Le véhicule doit avoir un constructeur spécifié.");
			}

			if (vehicle.getModele().isEmpty()) {
				throw new ServiceException("Le véhicule doit avoir un modèle spécifié.");
			}

			if (vehicle.getNb_places() < 2 || vehicle.getNb_places() > 9) {
				throw new ServiceException("Le nombre de places dans le véhicule doit être compris entre 2 et 9.");
			}*/

			vehicleDao.update(vehicle);
		} catch (DaoException e) {
			throw new ServiceException("Erreur pour mettre à jour un vehicule", e);
		}
	}

	public void validate(Vehicle vehicle) throws ServiceException {

			if (vehicle.getConstructeur().isEmpty()) {
				throw new ServiceException("Le véhicule doit avoir un constructeur spécifié.");
			}

			if (vehicle.getModele().isEmpty()) {
				throw new ServiceException("Le véhicule doit avoir un modèle spécifié.");
			}

			if (vehicle.getNb_places() < 2 || vehicle.getNb_places() > 9) {
				throw new ServiceException("Le nombre de places dans le véhicule doit être compris entre 2 et 9.");
			}


	}


	}
