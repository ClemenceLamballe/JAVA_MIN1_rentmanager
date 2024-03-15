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

		//Vérifications :
		//si son constructeur (manufacturer) est vide.
		//le nombres de places est supérieure à 1.

		if (vehicle.getConstructeur().isEmpty()|| vehicle.getModele().isEmpty()  || vehicle.getNb_places()<=1) {
			System.out.println("Veuillez remplir tout les champs");
			throw new ServiceException();

        }


		// TODO: créer un véhicule
        return vehicleDao.create(vehicle);
    }
	public void delete(long vehicleId) throws ServiceException, DaoException {
		Vehicle vehicleToDelete = this.findById(vehicleId);
		if (vehicleToDelete  == null) {
			throw new ServiceException();
		}

		try {
			 vehicleDao.delete(vehicleToDelete );
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}
	public Vehicle findById(long id) throws ServiceException, DaoException {
		// TODO: récupérer un véhicule par son id

        return vehicleDao.findById(id);
    }

	public List<Vehicle> findAll() throws ServiceException, DaoException {
		// TODO: récupérer tous les clients

        return vehicleDao.findAll();
    }

	public int count() throws ServiceException, DaoException {
		return vehicleDao.count();
	}
	
}
