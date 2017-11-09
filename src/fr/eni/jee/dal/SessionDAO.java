package fr.eni.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;

import fr.eni.jee.bo.Question;
import fr.eni.jee.bo.Reponse;
import fr.eni.jee.bo.Section;
import fr.eni.jee.bo.Session;
import fr.eni.qcm.util.AccesBase;

public class SessionDAO {

	public static Session rechercherScore(int idSession) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs = null;
		Session sessionEnCours=null;
		
		try{
			cnx=AccesBase.getConnection();
				
			//verification question déjà repondue
			rqt=cnx.prepareStatement("select scoreMax, scoreUtilisateur from session where idSession = ?");
			rqt.setInt(1, idSession);			
			rs=rqt.executeQuery();
			
			
			while (rs.next()) {	
				if (sessionEnCours==null) {
					sessionEnCours=new Session();
				}				
				sessionEnCours.setScoreMax(rs.getInt("scoreMax"));
				sessionEnCours.setScoreUtilisateur(rs.getInt("scoreUtilisateur"));
			}			
						
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return sessionEnCours;
	}
	
	public static Session ajouter(Session session) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		
		try{
			cnx=AccesBase.getConnection(); 
			
			//mise à jour table session
			String insertSession = "insert into session (utilisateur, qcm, datePassage, scoreMax) values (?,?,?,?)";
			rqt = cnx.prepareStatement(insertSession, Statement.RETURN_GENERATED_KEYS);
			rqt.setInt(1, session.getUtilisateur().getId());
			rqt.setInt(2, session.getQcm().getId());
			rqt.setDate(3, new java.sql.Date(new Date().getTime()));
			rqt.setInt(4, session.getScoreMax());
			rqt.executeUpdate();
			
			//recuperation de l'idSession
			ResultSet key = rqt.getGeneratedKeys();
			key.next();
			session.setId(key.getInt(1));
			
			//cnx.commit();
			
			
			
			//mise à jour table reponse(des candidats)
			String insertReponse = "insert into session_reponses (session, question) values (?,?)";
			
			for (Section section : session.getQcm().getSections()) {
				for (Question question : section.getLesQuestions()) {						
					rqt = cnx.prepareStatement(insertReponse);
					rqt.setInt(1, session.getId());
					rqt.setInt(2, question.getId());

					rqt.executeUpdate();
						
					//cnx.commit();					
				}
			}
			
		} catch (SQLException sqle){
					
			/*if (cnx != null) {
				cnx.rollback();
			}*/
			throw sqle;
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return session;

	}

	public static void ajouterReponse(int idSession, List<String> reponses, int idQuestion, boolean bonneReponse) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		String reponseBdd = "";
		ResultSet rs = null;
		List<Boolean> listReponse=new ArrayList<Boolean>();
		int score=0;
		
		for (String S : reponses) {
			if (reponses.indexOf(S)==reponses.size()-1) {
				reponseBdd+=S;
			}else {
				reponseBdd+=S+"#";
			}			
		}
		
		try{
			cnx=AccesBase.getConnection();
						
			//mise à jour de session_reponses
			rqt=cnx.prepareStatement("update session_reponses set reponse = ?, vraiFaux = ? where question = ?");
			rqt.setString(1, reponseBdd);
			rqt.setBoolean(2, bonneReponse);
			rqt.setInt(3, idQuestion);
			rqt.executeUpdate();
				
			//verification question déjà repondue
			rqt=cnx.prepareStatement("select vraiFaux from session_reponses where session = ?");
			rqt.setInt(1, idSession);			
			rs=rqt.executeQuery();
			
			while (rs.next()) {
				Boolean verifReponse= rs.getBoolean("vraiFaux");
				if (verifReponse) {
					listReponse.add(verifReponse);
				}				
			}			
			score=listReponse.size();
			
			//mise à jour du score Utilisateur dans session
			rqt=cnx.prepareStatement("update session set scoreUtilisateur = ? where idSession = ?");
			rqt.setInt(1, score);
			rqt.setInt(2, idSession);
			rqt.executeUpdate();
			
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	public static Session rechercherTestEnCours(int idUtilisateur) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		String reponseBdd = "";
		ResultSet rs = null;
		int testEnCours = 0;
		Session sessionEnCours=null;
		
		try{
			cnx=AccesBase.getConnection();
				
			//verification question déjà repondue
			rqt=cnx.prepareStatement("select idSession, qcm from session where utilisateur = ? and testTermine=0");
			rqt.setInt(1, idUtilisateur);			
			rs=rqt.executeQuery();
			
			
			while (rs.next()) {	
				if (sessionEnCours==null) {
					sessionEnCours=new Session();
				}				
				sessionEnCours.setId(rs.getInt("idSession"));
				sessionEnCours.setQcm(QcmDAO.rechercher(rs.getInt("qcm"),sessionEnCours.getId(), cnx));	
			}			
						
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return sessionEnCours;
	}
	
	public static List<Reponse> rechercherReponsesCandidat(int idSession) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		String reponseBdd = "";
		ResultSet rs = null;
		List<Reponse> reponses = null;
		
		try{
			cnx=AccesBase.getConnection();
				
			//verification question déjà repondue
			rqt=cnx.prepareStatement("select reponse from session_reponses where session = ?");
			rqt.setInt(1, idSession);			
			rs=rqt.executeQuery();
			
			reponses = new ArrayList<Reponse>();
			
			while (rs.next()) {
				
				if (rs.getString("reponse")!=null&&rs.getString("reponse")!="") {
					String libelle= rs.getString("reponse");
					
					ArrayList<String> libelles = new ArrayList<String>(Arrays.asList(libelle.split("#")));

					for (String unLibelle : libelles) {
						Reponse reponse=new Reponse();
						reponse.setLibelle(unLibelle);
						reponses.add(reponse);
					}
				}
				
			
			}			
						
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return reponses;
	}
	
	public static int rechercherDerniereQuestion(int idSession) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs = null;
		int index = -1;
		
		try{
			cnx=AccesBase.getConnection();
				
			//verification question déjà repondue
			rqt=cnx.prepareStatement("select question, reponse from session_reponses where session = ?");
			rqt.setInt(1, idSession);			
			rs=rqt.executeQuery();
			
			
			while (rs.next()) {				
				if (rs.getString("reponse")==null&&index==-1) {
					index=rs.getInt("question");					
				}			
			}
						
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return index;
	}
	
	public static void testTermine(int idSession) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		
		try{
			cnx=AccesBase.getConnection(); 
			
			//mise à jour table session
			rqt = cnx.prepareStatement("update session set testTermine = ? where idSession = ?");
			rqt.setBoolean(1, true);
			rqt.setInt(2, idSession);
			rqt.executeUpdate();
						
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}

	}
	
}
