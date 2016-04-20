package it.polito.tdp.ruzzle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordDAO {

	public boolean searchSequence(String sequence) {

		Connection conn = DBConnect.getConnection();

		String sql = "SELECT nome FROM parola WHERE nome LIKE ?;";

		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, sequence + "%");
			ResultSet res = st.executeQuery();

			if (res.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database AA");
		}
	}

	public boolean searchWord(String word) {

		Connection conn = DBConnect.getConnection();

		String sql = "SELECT nome FROM parola WHERE nome=?;";

		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, word);
			ResultSet res = st.executeQuery();

			if (res.next()) {
				System.out.println("esiste: " + word);
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database AA");
		}
	}

}
