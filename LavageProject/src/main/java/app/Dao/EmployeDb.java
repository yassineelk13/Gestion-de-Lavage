package app.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.models.Employe;

public class EmployeDb {
	private String jdbcURL = "jdbc:mysql://localhost/jaava_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee" +
			  "  (fname, username, password, salary) VALUES " +
			  " (?, ?, ?, ?);";

	private static final String SELECT_EMPLOYEE_BY_ID = "select id, fname, username, password, salary from employee where id =?";
	private static final String SELECT_ALL_EMPLOYEES = "select * from employee";
	private static final String DELETE_EMPLOYEE_SQL = "delete from employee where id = ?;";
	private static final String UPDATE_EMPLOYEE_SQL = "update employee set fname = ?, username = ?, password = ?, salary = ? where id = ?;";


	public EmployeDb() {
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

	public void insertEmploye(Employe employe) throws SQLException {
		System.out.println(INSERT_EMPLOYEE_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
			preparedStatement.setString(1, employe.getFullName());
			preparedStatement.setString(2, employe.getUsername());
			preparedStatement.setString(3, employe.getPassword());
			preparedStatement.setDouble(4, employe.getSalary());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Employe selectEmploye(int id) {
		Employe employe = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String fname = rs.getString("fname");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Double salary = rs.getDouble("salary");
				employe = new Employe(id, fname, username, password, salary);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return employe;
	}

	public List<Employe> selectAllEmployes() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Employe> employes = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String fname = rs.getString("fname");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Double salary = rs.getDouble("salary");
				employes.add(new Employe(id, fname, username, password, salary));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return employes;
	}

	public boolean deleteEmploye(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateEmploye(Employe employe) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
			statement.setString(1, employe.getFullName());
			statement.setString(2, employe.getUsername());
			statement.setString(3, employe.getPassword());
			statement.setDouble(4, employe.getSalary());
			statement.setInt(5, employe.getId());

			rowUpdated = statement.executeUpdate() > 0;
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
