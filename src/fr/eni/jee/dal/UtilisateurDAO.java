package fr.eni.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.bo.Utilisateur;
import fr.eni.qcm.util.AccesBase;

public class UtilisateurDAO {
	
	public static Utilisateur rechercher(int id) throws SQLException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select id, nom, prenom, login, password from utilisateur where id=?");
			rqt.setInt(1, id);
			rs=rqt.executeQuery();
			
			if (rs.next()){
				utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("id"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setLogin(rs.getString("login"));
				utilisateur.setPassword(rs.getString("password"));
			}
		
			else {
				utilisateur = null;
			}
			
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return utilisateur;
	}
	
	public static Utilisateur ajouter(Utilisateur utilisateur) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection(); 
			
			String insert = "insert into utilisateur (nom, prenom, login, password) values (?,?,?,?)";
			rqt = cnx.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, utilisateur.getNom());
			rqt.setString(2, utilisateur.getPrenom());
			rqt.setString(3, utilisateur.getLogin());
			rqt.setString(4, utilisateur.getPassword());
			rqt.executeUpdate();
			ResultSet key = rqt.getGeneratedKeys();
			key.next();
			utilisateur.setId(key.getInt(1));
			
			
			cnx.commit();
			
			key.close();
			
		} catch (SQLException sqle){
					
			if (cnx != null) {
				cnx.rollback();
			}
			throw sqle;
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return utilisateur;

	}

	public static void modifier(Utilisateur utilisateur) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("update stagiaires set nom = ?, prenom = ?, login = ?, password=? where id = ?");
			rqt.setString(1, utilisateur.getNom());
			rqt.setString(2, utilisateur.getPrenom());
			rqt.setString(3, utilisateur.getLogin());
			rqt.setString(4, utilisateur.getPassword());
			rqt.setInt(5, utilisateur.getId());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	public static void supprimer(Utilisateur utilisateur) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("delete from utilisateur where id = ?");
			rqt.setInt(1, utilisateur.getId());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	public static List<Utilisateur> rechercher() throws SQLException {
		
		List<Utilisateur> listeutilisateur = new ArrayList<Utilisateur>();
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select id, nom, prenom, login, password from utilisateur");
			rs=rqt.executeQuery();
			
			while (rs.next()){
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("id"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setLogin(rs.getString("login"));
				utilisateur.setPassword(rs.getString("password"));
				
				listeutilisateur.add(utilisateur);
			}

			
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeutilisateur;
		
	}
	
	
	
	
	
	
	
	
	
	
}
