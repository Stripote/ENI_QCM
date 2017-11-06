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
		Session session= null;
		/*PreparedStatement rqt = null;
		ResultSet rs = null;
		Connection cnx = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement(
					"SELECT questions.id idQUESTIONS, questions.enonce enonceQUESTIONS, questions.image imageQUESTIONS, reponses.libelle libelleREPONSES, reponses.reponses reponsesREPONSES "
					+ "FROM questions JOIN reponses ON reponses.question=questions.id "
					+ "JOIN questions_theme qt ON qt.idQuestion=questions.id "
					+ "JOIN theme ON qt.idTheme=theme.id "
					+"where questions.id=?");
			rqt.setInt(1, id);
			rs=rqt.executeQuery();
			
			if (rs.next()){
				List<Reponse> reponses = new ArrayList<Reponse>();
				
				String libelle= rs.getString("libelleREPONSES");
				String index=rs.getString("reponsesREPONSES");
				
				ArrayList<String> libelles = new ArrayList<String>(Arrays.asList(libelle.split("#")));
				List<String> indexs = new ArrayList<String>(Arrays.asList(index.split("#")));
				
				for (String unLibelle : libelles) {
					Reponse reponse=new Reponse();
					reponse.setLibelle(unLibelle);
					if (indexs.contains(libelles.indexOf(unLibelle))) {
						reponse.setBonneReponse(true);
					} else {
						reponse.setBonneReponse(false);
					}
					reponses.add(reponse);
				}
				
				question.setId(rs.getInt("idQUESTIONS"));
				question.setEnonce(rs.getString("enonceQUESTIONS"));
				if (rs.getString("imageQUESTIONS")!=null) {
					question.setImage(rs.getString("imageQUESTIONS"));
				}		
				question.setReponses(reponses);
			}		
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}*/
		return session;
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
					rqt.setInt(2, session.getId());
					rqt.setInt(3, question.getId());

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

	public static void ajouterReponse(int idSession, List<String> reponses, int idQuestion, boolean bonneReponse, int score) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		String reponseBdd =null;
		
		for (String S : reponses) {
			if (reponses.indexOf(S)==reponses.size()) {
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
			//cnx.commit();
			
			//mise à jour du score Utilisateur dans session
			if (bonneReponse==true) {
				rqt=cnx.prepareStatement("update session set scoreUtilisateur = ? where idSession = ?");
				rqt.setInt(1, score);
				rqt.setInt(2, idSession);
				rqt.executeUpdate();
				//cnx.commit();
			}			
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
}
