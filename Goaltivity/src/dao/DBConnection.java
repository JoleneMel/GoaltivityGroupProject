package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private final static String URL = "jdbc:mysql://localhost:3306/goaltivity";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Death3the3kid3!";
	private static Connection connection;
	private static DBConnection instance;

	private DBConnection(Connection connection) {
		this.connection = connection;
	}

	public static Connection getConnection() {
		if (instance == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				instance = new DBConnection(connection);
				System.out.println("Connection is successful");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return DBConnection.connection;
	}
}
