package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DictionaryDAO {
	
	private List<String> dictionary;
	
	public DictionaryDAO() {}
	
	public void loadDictionary() {
		this.dictionary = new ArrayList<>();
		Connection conn = ConnectDB.getConnection();
		
		try {
			Statement st = conn.createStatement();
			
			String sql = "select nome from parola";
			
			ResultSet res = st.executeQuery(sql);
			
			while(res.next() ) {
				// Aggiungo parole alla struttura dati
				dictionary.add(res.getString("nome").toLowerCase());
			}
			
			res.close();
			conn.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public boolean isValid(String input) {
	
		try {
			Connection conn = ConnectDB.getConnection();
			
			Statement st = conn.createStatement();
				
			String sql = "SELECT nome FROM parola WHERE nome like \""+input+"%\"";
				
			ResultSet res = st.executeQuery(sql);
				
			if (res.next()) {
				res.close();
				conn.close();
				return true;
			} else {
				res.close();
				conn.close();
				return false;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException("Errore esecuzione",e1);
		}
		
	
	}
	
	public boolean isAWord(String input) {
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			Statement st = conn.createStatement();
				
			String sql = "SELECT nome FROM parola WHERE nome = \""+input+"\"";
				
			ResultSet res = st.executeQuery(sql);
				
			if (res.next()) {
				res.close();
				conn.close();
				return true;
			} else {
				res.close();
				conn.close();
				return false;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException("Errore esecuzione",e1);
		}
		
	}

}
