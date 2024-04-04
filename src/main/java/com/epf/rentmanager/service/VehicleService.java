package com.epf.rentmanager.service;

import java.util.List;

//import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.dao.DaoException;
//import com.epf.rentmanager.exception.ServiceException;
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


	public long create(Vehicle vehicle) throws ServiceException, DaoException {
		try {

			if (vehicle.getConstructeur().isEmpty() || vehicle.getModele().isEmpty() || vehicle.getNb_places() < 2 || vehicle.getNb_places() > 9) {
				throw new ServiceException("Le véhicule doit avoir un modèle et un constructeur, et le nombre de places doit être compris entre 2 et 9.");
			}

			return vehicleDao.create(vehicle);
		} catch (DaoException e) {
			throw new DaoException("Erreur lors de la création du véhicule", e);
		}
	}

	public void delete(long vehicleId) throws ServiceException, DaoException {
		Vehicle vehicleToDelete = this.findById(vehicleId);

		try {
			 vehicleDao.delete(vehicleToDelete );
		} catch (DaoException e) {
			throw new ServiceException("Erreur dans la suppression de vehicule ",e);
		}
	}
	public Vehicle findById(long id) throws ServiceException, DaoException {

        return vehicleDao.findById(id);
    }

	public List<Vehicle> findAll() throws ServiceException, DaoException {
		// TODO: récupérer tous les clients

        return vehicleDao.findAll();
    }

	public int count() throws ServiceException, DaoException {
		return vehicleDao.count();
	}


	public void update(Vehicle vehicle) throws ServiceException {
		try {
			vehicleDao.update(vehicle);
		} catch (DaoException e) {
			throw new ServiceException("Error updating vehicle", e);
		}
	}
	
}
