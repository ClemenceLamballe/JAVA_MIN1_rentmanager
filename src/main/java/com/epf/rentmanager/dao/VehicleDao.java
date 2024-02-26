package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;


public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur,modele, nb_places) VALUES(?,?,?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {

			Connection connection = ConnectionManager.getConnection();

			Statement statement = connection.createStatement();

			PreparedStatement ps =
					connection.prepareStatement(CREATE_VEHICLE_QUERY,statement.RETURN_GENERATED_KEYS);

			ps.setString(1, vehicle.getConstructeur());
			ps.setString(2, vehicle.getModele());
			ps.setInt(3, vehicle.getNb_places());


			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();

			if (resultSet.next()){
				//int id = resultSet.getInt(1);
				long id = resultSet.getLong(1);

				vehicle.setId(id);
				return id;
			}

			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la création du PreparedStatement"+e);
			throw new DaoException();
		}
		return -1;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			PreparedStatement ps =
					connection.prepareStatement(DELETE_VEHICLE_QUERY);

			ps.setLong(1, vehicle.getId());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException();
		}



		return 0;
	}

	public Vehicle findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_VEHICLE_QUERY);
			ps.setLong(1, id);

			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				String constructeur = resultSet.getString("constructeur");
				String modele = resultSet.getString("modele");
				int nbPlaces = resultSet.getInt("nb_places");

				return new Vehicle(id, constructeur,modele, nbPlaces);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException();
		}
		return null;

	}

	public List<Vehicle> findAll() throws DaoException {

		List<Vehicle> vehicles = new ArrayList<>();

		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_VEHICLES_QUERY);

			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String constructeur = resultSet.getString("constructeur");
				String modele = resultSet.getString("modele");
				int nbPlaces = resultSet.getInt("nb_places");

				Vehicle vehicle = new Vehicle(id, constructeur,modele, nbPlaces);
				vehicles.add(vehicle);
			}

			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException();
		}

		return vehicles;
		
	}
	

}
