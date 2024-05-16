package app.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.models.Client;



public class ClientDb {
	private String jdbcURL = "jdbc:mysql://localhost/jaava_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_Client_SQL = "INSERT INTO client"
			+ "  (prenom, nom, email, telephone,description) VALUES " + " (?, ?, ?, ?,?);";

	private static final String SELECT_CLIENT_BY_ID = "select * from client where id =?";
	private static final String SELECT_ALL_CLIENT = "select * from client";
	private static final String DELETE_CLIENT_SQL = "delete from client where id = ?;";
	private static final String UPDATE_CLIENT_SQL = "update client set prenom = ?, nom = ?, email = ?, telephone = ?,description=? where id = ?;";

	public ClientDb() {

	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertClient(Client client) throws SQLException {
		System.out.println(INSERT_Client_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Client_SQL)) {
			preparedStatement.setString(1, client.getPrenom());
			preparedStatement.setString(2, client.getNom());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setString(4, client.getTelephone());
			preparedStatement.setString(5, client.getDescription());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Client selectClient(int id) {
		Client client = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String prenom = rs.getString("prenom");
				String nom = rs.getString("nom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String description = rs.getString("description");
				client = new Client(id, prenom, nom, email, telephone,description);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return client;
	}
	
	
	

	public List<Client> selectAllclients() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Client> clients = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENT);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String prenom = rs.getString("prenom");
				String nom = rs.getString("nom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String description = rs.getString("description");
				clients.add(new Client(id, prenom, nom, email, telephone,description));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return clients;
	}

	public boolean deleteclient(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateclient(Client client) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement  preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL);) {
			preparedStatement.setString(1, client.getPrenom());
			preparedStatement.setString(2, client.getNom());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setString(4, client.getTelephone());
			preparedStatement.setString(5, client.getDescription());
			preparedStatement.setInt(6, client.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
