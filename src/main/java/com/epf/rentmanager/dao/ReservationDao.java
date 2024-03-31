package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ServiceException;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}

	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATION_BY_ID_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET vehicle_id=?, client_id=?, debut=?, fin=? WHERE id=?;";

	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			PreparedStatement ps =
					 connection.prepareStatement(CREATE_RESERVATION_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);



			ps.setLong(1, reservation.getClient_id());
			ps.setLong(2, reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();


			if (resultSet.next()){
				int id = resultSet.getInt(1);
				reservation.setId(id);

				return id;
			}

			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la création d'une réservation");
		}
		return -1;
	}
	
	public long delete(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			PreparedStatement ps =
					connection.prepareStatement(DELETE_RESERVATION_QUERY);

			ps.setLong(1, reservation.getId());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la suppression d'une réservation");
		}

		return 0;
	}

	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			ps.setLong(1, clientId);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Long vehicleID= resultSet.getLong("vehicle_id");
				LocalDate Debut = resultSet.getDate("debut").toLocalDate();
				LocalDate Fin = resultSet.getDate("fin").toLocalDate();
				Long id = resultSet.getLong("id");

				Reservation reservation = new Reservation(id, clientId,  vehicleID,  Debut, Fin);
				reservations.add(reservation);

			}

			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur pour trouver la réservation");
		}
		return reservations;
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			ps.setLong(1, vehicleId);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Long clientId = resultSet.getLong("client_id");
				LocalDate debut = resultSet.getDate("debut").toLocalDate();
				LocalDate fin = resultSet.getDate("fin").toLocalDate();
				Long id = resultSet.getLong("id");

				Reservation reservation = new Reservation(id, clientId, vehicleId, debut, fin);
				reservations.add(reservation);
			}

			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur pour trouver la réservation");
		}
		return reservations;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_RESERVATIONS_QUERY);

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				Long clientId = resultSet.getLong("client_id");
				Long vehicleId = resultSet.getLong("vehicle_id");
				LocalDate debut = resultSet.getDate("debut").toLocalDate();
				LocalDate fin = resultSet.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(id, clientId, vehicleId, debut, fin);
				reservations.add(reservation);
			}

			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur pour trouver les réservations");
		}
		return reservations;
	}

	public Reservation findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_RESERVATION_BY_ID_QUERY);
			ps.setLong(1, id);

			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				long clientId = resultSet.getLong("client_id");
				long vehicleId = resultSet.getLong("vehicle_id");
				LocalDate debut = resultSet.getDate("debut").toLocalDate();
				LocalDate fin = resultSet.getDate("fin").toLocalDate();

				return new Reservation(id, clientId, vehicleId, debut, fin);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur pour trouver la réservation");
		}
		return null;
	}

	public int countReservation() throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM reservation");
			 ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				return resultSet.getInt(1);
			}

		} catch (SQLException e) {
			throw new DaoException("Erreur pour compter les réservations");
		}

		return -1;
	}
	public void update(Reservation reservation) throws DaoException {
		try {

			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_RESERVATION_QUERY);
			ps.setLong(1, reservation.getVehicle_id());

			ps.setLong(2, reservation.getClient_id());

			ps.setDate(3, Date.valueOf(reservation.getDebut()));

			ps.setDate(4, Date.valueOf(reservation.getFin()));

			ps.setLong(5, reservation.getId());

			ps.executeUpdate();

			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la mise à jour de la réservation dans le DAO");
		}
	}
}
