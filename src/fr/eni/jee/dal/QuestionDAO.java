package fr.eni.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.eni.jee.bo.Question;
import fr.eni.jee.bo.Reponse;
import fr.eni.jee.bo.Theme;
import fr.eni.qcm.util.AccesBase;

public class QuestionDAO {

	
	/***
	 * renvoie la question demandée et les reponses liées
	 * @param idQuestion
	 * @param Connexion
	 * @return Question
	 * @throws SQLException
	 */
	public static Question rechercher(int id, Connection cnx) throws SQLException{
		Question question = new Question();
		PreparedStatement rqt = null;
		ResultSet rs = null;

		try{
			if(cnx==null)
				cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement(
					"SELECT questions.id idQUESTIONS, questions.enonce enonceQUESTIONS, questions.image imageQUESTIONS, reponses.libelle libelleREPONSES, reponses.reponses reponsesREPONSES "
					+ "FROM questions JOIN reponses ON reponses.question=questions.id "
					+ "JOIN questions_theme qt ON qt.idQuestion=questions.id "
					+ "JOIN theme ON qt.idTheme=theme.id "
					+"where questions.id=?");
			rqt.setInt(1, id);
			rs=rqt.executeQuery();
			
			if(rs.next()){
				List<Reponse> reponses = new ArrayList<Reponse>();
				
				String libelle= rs.getString("libelleREPONSES");
				String index=rs.getString("reponsesREPONSES");
				String enonce = rs.getString("enonceQUESTIONS");
				int idQuestion = rs.getInt("idQUESTIONS");
				
				question.setEnonce(enonce);
				question.setId(idQuestion);
				if(rs.getString("imageQUESTIONS")!=null) {
					question.setImage(rs.getString("imageQUESTIONS"));
				}	
				ArrayList<String> libelles = new ArrayList<String>(Arrays.asList(libelle.split("#")));
				List<String> indexs = new ArrayList<String>(Arrays.asList(index.split("#")));
				for (String unLibelle : libelles) {
					Reponse reponse=new Reponse();
					reponse.setLibelle(unLibelle);
					int i = libelles.indexOf(unLibelle);
					if (indexs.contains( String.valueOf(i+1) )) {
						reponse.setBonneReponse(true);
					} else {
						reponse.setBonneReponse(false);
					}
					reponses.add(reponse);
				}
				
				question.setReponses(reponses);
			}		
		}finally{
			/*if (!rs.isClosed()) 
				rs.close();
			if (!rqt.isClosed()) 
				rqt.close();
			if (!cnx.isClosed()) 
				cnx.close(); */
		}
		return question;
	}
	
	/***
	 * renvoie la question demandée et les reponses liées
	 * @param id
	 * @return Question
	 * @throws SQLException
	 */
	public static Question rechercher(int id) throws SQLException{
		Question question= null;
		PreparedStatement rqt = null;
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
		}
		return question;
	}
	
	public static Question ajouter(Question question) throws SQLException{
		/*Connection cnx=null;
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
		}*/
		
		return question;

	}

	public static void modifier(Question question) throws SQLException{
		/*Connection cnx=null;
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
		}*/
	}
	
	public static void supprimer(Question question) throws SQLException{
		/*Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("delete from utilisateurs where id = ?");
			rqt.setInt(1, utilisateur.getId());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}*/
	}
	
	/***
	 * renvoie l'ensemble des questions en bdd
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Question> rechercher() throws SQLException {
		
		ArrayList<Question> listQuestion = new ArrayList<Question>();
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement(
					"SELECT questions.id, questions.enonce, questions.image, reponses.libelle, reponses.reponses "
					+ "FROM questions JOIN reponses ON reponses.question=questions.id "
					+ "JOIN questions_theme qt ON qt.idQuestion=questions.id "
					+ "JOIN theme ON qt.idTheme=theme.id");
			rs=rqt.executeQuery();
			
			while (rs.next()){
				List<Reponse> reponses = new ArrayList<Reponse>();
							
				String libelle= rs.getString("reponses.libelle");
				String index=rs.getString("reponses.reponse");
				
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
				
				
				Question question = new Question();
				question.setId(rs.getInt("questions.id"));
				question.setEnonce(rs.getString("questions.enonce"));
				if (rs.getString("questions.image")!=null) {
					question.setImage(rs.getString("questions.image"));
				}		
				question.setReponses(reponses);
				
				listQuestion.add(question);
			}
			
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listQuestion;
		
	}

	
}
