package com.epf.rentmanager.dao;

import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
@Repository
public class  ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";

	/**
	 * @param client
	 * @return
	 * @throws DaoException
	 */
	public long create(Client client) throws DaoException {
		try {

			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			PreparedStatement ps =
					connection.prepareStatement(CREATE_CLIENT_QUERY,statement.RETURN_GENERATED_KEYS);

			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getNaissance()));


			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();

			if (resultSet.next()){
				int id = resultSet.getInt(1);
				return id;
			}

			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la création d'un client",e);
		}
		return -1;
	}

	/**
	 * @param client
	 * @return
	 * @throws DaoException
	 */
	public long delete(Client client) throws DaoException {

		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			PreparedStatement ps =
					connection.prepareStatement(DELETE_CLIENT_QUERY);

			ps.setLong(1, client.getId());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la suppression d'un client",e);
		}



		return 0;
	}

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Client findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_CLIENT_QUERY);
			ps.setLong(1, id);

			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String email = resultSet.getString("email");
				LocalDate naissance = resultSet.getDate("naissance").toLocalDate();

				return new Client(id, nom, prenom, email, naissance);
			}

			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur pour trouver un client",e);
		}
		return null;
	}

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<>();

		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_CLIENTS_QUERY);

			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String email = resultSet.getString("email");
				LocalDate naissance = resultSet.getDate("naissance").toLocalDate();

				Client client = new Client(id, nom, prenom, email, naissance);
				clients.add(client);
			}

			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur pour trouver les clients",e);
		}

		return clients;
	}

	/**
	 * @return
	 * @throws DaoException
	 */
	public int countClients() throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM client");
			 ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				return resultSet.getInt(1);
			}

		} catch (SQLException e) {
			throw new DaoException("Erreur pour compter les clients",e);
		}

		return -1;
	}

	/**
	 * @param client
	 * @throws DaoException
	 */
	public void update(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_CLIENT_QUERY);

			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getNaissance()));
			ps.setLong(5, client.getId());

			ps.executeUpdate();

			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la mise à jour d'un client",e);
		}
	}


}
