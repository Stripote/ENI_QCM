package fr.eni.qcm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class AccesBasePoolConnection {
	public static Connection getConnection() throws SQLException{
		
		Connection connexion = null;
		try {
			InitialContext ic = new InitialContext();
			DataSource ds= (DataSource)ic.lookup("java:comp/env/jdbc/bdd");
			connexion =  ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connexion;		
	}
}
