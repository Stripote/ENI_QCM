package fr.eni.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.eni.jee.bo.Qcm;
import fr.eni.jee.bo.Question;
import fr.eni.jee.bo.Reponse;
import fr.eni.jee.bo.Theme;
import fr.eni.qcm.util.AccesBase;

public class ThemeDAO {
	
	/***
	 * Permet de rechercher un thème en base de données, par son ID
	 * @param id
	 * @param cnx
	 * @return Theme
	 * @throws SQLException
	 */
	public static Theme rechercher(int id, Connection cnx) throws SQLException{
		Theme theme= null;
		PreparedStatement rqt = null;
		ResultSet rs = null;

		try{
			if(cnx==null)
				cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement(
					"SELECT theme.libelle nomTHEME, questions.id idQUESTIONS " 
							+"FROM questions " 
							+"JOIN questions_theme qt ON qt.idQuestion=questions.id "
							+"JOIN theme ON qt.idTheme=theme.id "
							+"WHERE theme.id=?");
			rqt.setInt(1, id);
			rs=rqt.executeQuery();
			List<Question> questions = new ArrayList<Question>();
			theme = new Theme();
			int i=0;
			
			while(rs.next()) {
				if (i==0) {
					theme.setId(id);
					theme.setNom(rs.getString("nomTHEME"));
				}
				Question question= new Question();
				int idQuestion = Integer.parseInt(rs.getString("idQUESTIONS"));
				question=QuestionDAO.rechercher(idQuestion, cnx);
				questions.add(question);
				i++;
			}
			theme.setQuestions(questions);
		}finally{
			/*if (!rs.isClosed()) 
				rs.close();
			if (!rqt.isClosed()) 
				rqt.close();
			if (!cnx.isClosed()) 
				cnx.close();*/
		}
		return theme;
	}
	
	/***
	 * Permet de rechercher un thème en base de données, par son ID
	 * @param id
	 * @return Theme
	 * @throws SQLException
	 */
	public static Theme rechercher(int id) throws SQLException{
		Theme theme= null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Connection cnx = null;
		try{
			
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement(
					"SELECT theme.libelle nomTHEME, questions.id idQUESTIONS " 
							+"FROM questions " 
							+"JOIN questions_theme qt ON qt.idQuestion=questions.id "
							+"JOIN theme ON qt.idTheme=theme.id "
							+"WHERE theme.id=?");
			rqt.setInt(1, id);
			rs=rqt.executeQuery();
			List<Question> questions = new ArrayList<Question>();
			theme = new Theme();
			int i=0;
			
			if (rs.next()) {
				if (i==0) {
					theme.setId(id);
					theme.setNom(rs.getString("nomTHEME"));
				}
				Question question= new Question();
				question=QuestionDAO.rechercher(Integer.parseInt(rs.getString("idQUESTIONS")), cnx);
				questions.add(question);
				i++;
			}
			theme.setQuestions(questions);
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return theme;
	}
	
	public static void ajouter(Theme theme) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection(); 
			
			String insert = "insert into theme (libelle) values (?)";
			rqt = cnx.prepareStatement(insert);
			rqt.setString(1, theme.getNom());

			rqt.executeUpdate();			
			
			cnx.commit();

			
		} catch (SQLException sqle){
					
			throw sqle;
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}

	public static void modifier(Theme theme) throws SQLException{
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
	
	public static void supprimer(Theme theme) throws SQLException{
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
	
public static ArrayList<Theme> rechercher() throws SQLException {
		
		ArrayList<Theme> listeThemes = new ArrayList<Theme>();
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select id, libelle from theme");
			rs=rqt.executeQuery();
			
			while (rs.next()){
				Theme theme = new Theme();
				theme.setId(rs.getInt("id"));
				theme.setNom(rs.getString("libelle"));
				
				
				
				listeThemes.add(theme);
			}

			
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeThemes;
		
	
		
	}
}
