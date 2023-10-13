package projects.dao;

//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projects.exception.DbException;

public class JDBCconnection {

	// Variables
	private static final String PASSWORD = "projects";
	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	private static final String SCHEMA = "projects";
	private static final String USER = "projects";

	// Connection Method Call
	public static Connection getConnection() {

		// Setting the URL
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", HOST, PORT, SCHEMA, USER,
				PASSWORD);

		// Try Catch block for the Driver Manager Call
		try {
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Successfull connection with " + SCHEMA);
			return conn;
		} catch (SQLException e) {
			System.out.println("Error getting connection with " + SCHEMA);
			throw new DbException(e);

		}

	}

}
