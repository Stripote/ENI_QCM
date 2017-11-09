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
import fr.eni.qcm.util.AccesBasePoolConnection;

public class UtilisateurDAO {
	
	public static Utilisateur rechercher(String login, String password) throws SQLException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select A.id, A.nom, A.prenom, A.login, A.password, C.libelle "
					+ "from utilisateurs A "
					+ "join utilisateurs_role B on A.id=B.idUtilisateur "
					+ "join rôle C on C.id=B.idRole "
					+ "where A.login=? and A.password=?");
			rqt.setString(1, login);
			rqt.setString(2, password);
			rs=rqt.executeQuery();
			
			if (rs.next()){
				utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("id"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setLogin(rs.getString("login"));
				utilisateur.setPassword(rs.getString("password"));
				utilisateur.setRole(rs.getString("libelle"));
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
	
	public static Utilisateur rechercher(int id) throws SQLException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select id, nom, prenom, login, password from utilisateurs where id=?");
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
	
	public static Utilisateur rechercher(String role) throws SQLException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select A.id, A.nom, A.prenom, A.login, A.password, C.libelle "
					+ "from utilisateurs A "
					+ "join utilisateurs_role B on A.id=B.idUtilisateur "
					+ "join rôle C on C.id=B.idRole "
					+ "where C.libelle=?");
			rqt.setString(1, role);
			rs=rqt.executeQuery();
			
			if (rs.next()){
				utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("id"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setLogin(rs.getString("login"));
				utilisateur.setPassword(rs.getString("password"));
				utilisateur.setRole(rs.getString("libelle"));
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
			
			String insert = "insert into utilisateurs (nom, prenom, login, password) values (?,?,?,?)";
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

	@SuppressWarnings("resource")
	public static Utilisateur ajouterRole(Utilisateur utilisateur) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection(); 
			
			String insertUtilisateur = "insert into utilisateurs (nom, prenom, login, password) values (?,?,?,?)";
	                         
			
			
			rqt = cnx.prepareStatement(insertUtilisateur, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, utilisateur.getNom());
			rqt.setString(2, utilisateur.getPrenom());
			rqt.setString(3, utilisateur.getLogin());
			rqt.setString(4, utilisateur.getPassword());
			rqt.executeUpdate();
			ResultSet key = rqt.getGeneratedKeys();
			key.next();
			utilisateur.setId(key.getInt(1));
			
		
		    String insertRole = "insert into utilisateurs_role (idUtilisateur, idRole) values (?,?)";
		    
			rqt = cnx.prepareStatement(insertRole);
			rqt.setInt(1, utilisateur.getId());           
			rqt.setInt(2, Integer.parseInt(utilisateur.getRole()));
			rqt.executeUpdate();
			
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
			rqt=cnx.prepareStatement("update utilisateurs set nom = ?, prenom = ?, login = ?, password= ? where id = ?");
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
			rqt=cnx.prepareStatement("delete from utilisateurs where id = ?");
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
			rqt = cnx.prepareStatement("select id, nom, prenom, login, password from utilisateurs");
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
