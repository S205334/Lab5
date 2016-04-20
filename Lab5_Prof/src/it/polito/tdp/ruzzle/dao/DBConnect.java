package it.polito.tdp.ruzzle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static final String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";
	private static Connection connection;

	public static Connection getConnection() {

		try {
			if (connection == null) {
				connection = DriverManager.getConnection(jdbcURL);
			}
			return connection;

		} catch (SQLException e) {
//			e.printStackTrace();
			throw new RuntimeException("Errore nella connessione", null);
		}
	}

}