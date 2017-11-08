package fr.eni.jee.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.bo.Qcm;
import fr.eni.jee.bo.Question;
import fr.eni.jee.bo.Section;
import fr.eni.jee.bo.Theme;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.qcm.util.AccesBase;

public class QcmDAO {

	/***
	 * renvoie la liste de qcm avec les id, le nom et la date de creation 
	 * @return
	 * @throws SQLException
	 */
	
	public static ArrayList<Qcm> rechercher() throws SQLException {
		
		ArrayList<Qcm> listeQcms = new ArrayList<Qcm>();
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select id, nom, dateCreation from qcm");
			rs=rqt.executeQuery();
			
			while (rs.next()){
				Qcm qcm = new Qcm();
				qcm.setId(rs.getInt("id"));
				qcm.setNom(rs.getString("nom"));
				qcm.setDateCreation(rs.getDate("dateCreation"));
				
				
				listeQcms.add(qcm);
			}

			
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeQcms;
		
	}
	
	/***
	 * renvoie un qcm avec les sections correspondantes, les themes et les id de questions liées
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static Qcm rechercher(int id) throws SQLException {
		
		List<Section> sections = new ArrayList<Section>();
		Qcm qcm = null;
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("SELECT qcm.id idQCM, qcm.nom nomQCM, theme.id idTHEME, theme.libelle libelleTHEME, sections.id idSECTIONS, sections.nbQuestions nbQUESTIONS "
					+"FROM qcm "
					+"JOIN sections ON qcm.id=sections.qcm "
					+"JOIN theme ON theme.id=sections.theme "
					+"WHERE qcm.id = ?");
			rqt.setInt(1, id);
			rs=rqt.executeQuery();
			
			int i = 0;
			qcm = new Qcm();
			while (rs.next()){
				if (i==0) {
					qcm.setId(rs.getInt("idQCM"));
					qcm.setNom(rs.getString("nomQCM"));
				}
								
				Section section= new Section();
				section.setId(rs.getInt("idSECTIONS"));
				section.setNbQuestions(rs.getInt("nbQUESTIONS"));
				Theme theme=new Theme();
				theme.setId(rs.getInt("idTHEME"));
				theme.setNom(rs.getString("libelleTHEME"));
				section.setLesQuestions(ThemeDAO.rechercher(theme.getId(), cnx).getQuestions());
				section.setTheme(theme);
				section.ajusterNombre();
				sections.add(section);
				i++;
			} 
			qcm.setSections(sections);
			
		}finally{
			if(!rs.isClosed())
				rs.close();
			if(!rqt.isClosed())
				rqt.close();
			if(!cnx.isClosed())
				cnx.close();
		}
		return qcm;
		
	}
	
public static Qcm rechercher(int idQcm, int idSession, Connection cnx) throws SQLException {
		
		List<Section> sections = new ArrayList<Section>();
		List<Integer> listQues = new ArrayList<Integer>();
		Qcm qcm = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			rqt = cnx.prepareStatement("SELECT question "
					+"FROM session_reponses "
					+"WHERE session = ?");
			rqt.setInt(1, idSession);
			rs=rqt.executeQuery();
			while (rs.next()) {listQues.add(rs.getInt("question"));}
			
			rqt = cnx.prepareStatement("SELECT qcm.id idQCM, qcm.nom nomQCM, theme.id idTHEME, theme.libelle libelleTHEME, sections.id idSECTIONS, sections.nbQuestions nbQUESTIONS "
					+"FROM qcm "
					+"JOIN sections ON qcm.id=sections.qcm "
					+"JOIN theme ON theme.id=sections.theme "
					+"WHERE qcm.id = ?");
			rqt.setInt(1, idQcm);
			rs=rqt.executeQuery();
			
			int i = 0;
			qcm = new Qcm();
			while (rs.next()){
				if (i==0) {
					qcm.setId(rs.getInt("idQCM"));
					qcm.setNom(rs.getString("nomQCM"));
				}
								
				Section section= new Section();
				section.setId(rs.getInt("idSECTIONS"));
				section.setNbQuestions(rs.getInt("nbQUESTIONS"));
				Theme theme=new Theme();
				theme.setId(rs.getInt("idTHEME"));
				theme.setNom(rs.getString("libelleTHEME"));
				section.setLesQuestions(ThemeDAO.rechercher(theme.getId(), cnx).getQuestions());
				section.setTheme(theme);

				List<Question> listeAVerifier= new ArrayList<Question>(section.getLesQuestions());
				for (Question question : listeAVerifier) {
					if (!listQues.contains(question.getId())) {
						section.getLesQuestions().remove(question);
					}
				}
				
				sections.add(section);
				i++;
			} 
			qcm.setSections(sections);
			
		}finally{
			/*if(!rs.isClosed())
				rs.close();
			if(!rqt.isClosed())
				rqt.close();
			if(!cnx.isClosed())
				cnx.close();*/
		}
		return qcm;
		
	}
	
	/***
	 * Permet d'ajouter un QCM en base de données.
	 * @param qcm
	 * @return
	 * @throws SQLException
	 */
	public static Qcm ajouter(Qcm qcm) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection(); 
			//Insertion du QCM
			String insertQcm = "insert into qcm (nom) values (?)";
			rqt = cnx.prepareStatement(insertQcm, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, qcm.getNom());
			rqt.execute();
			ResultSet key = rqt.getGeneratedKeys();
			key.next();
			qcm.setId(key.getInt(1));
			//insertion des sections
			for(Section S : qcm.getSections()){
				String insertSection = "insert into sections(theme, nbQuestions, qcm) values(?, ?, ?)";
				rqt = cnx.prepareStatement(insertSection);
				rqt.setInt(1, S.getTheme().getId());
				rqt.setInt(2, S.getNbQuestions());
				rqt.setInt(3, qcm.getId());
				rqt.execute();
			}
			cnx.commit();	
			key.close();
			
		} catch (SQLException sqle){		
			sqle.printStackTrace();
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return qcm;

	}
	
}
