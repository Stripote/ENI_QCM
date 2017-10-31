package fr.eni.qcm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;


public class AccesBase {
	public static Connection getConnection() throws SQLException{
		
		String uri = Parametre.lire("dbUrl");
		String user = Parametre.lire("dbUser");
		String password = Parametre.lire("dbPassword");
		Connection connexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//DriverManager.registerDriver((Driver)Class.forName("com.mysql.jdbc.Driver").newInstance());
			connexion =  DriverManager.getConnection(uri, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return connexion;		
	}
}
