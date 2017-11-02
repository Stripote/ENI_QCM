package fr.eni.jee.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.bo.Qcm;
import fr.eni.jee.bo.Section;
import fr.eni.jee.bo.Theme;
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
	 * renvoie un qcm avec les sections correspondantes, les themes et les id de questions li�es
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
			rqt = cnx.prepareStatement("SELECT qcm.id, qcm.nom, theme.id, theme.libelle, sections.id, sections.nbQuestions"
					+"FROM qcm"
					+"JOIN sections ON qcm.id=sections.qcm"
					+"JOIN theme ON theme.id=sections.theme"
					+"WHERE qcm.id = ?");
			rqt.setInt(1, id);
			rs=rqt.executeQuery();
			
			int i = 0;
			qcm = new Qcm();
			while (rs.next()){

				if (i==0) {
					qcm.setId(rs.getInt("qcm.id"));
					qcm.setNom(rs.getString("qcm.nom"));
				}
								
				Section section= new Section();
				section.setId(rs.getInt("sections.id"));
				Theme theme=new Theme();
				theme.setId(rs.getInt("theme.id"));
				theme.setNom(rs.getString("theme.libelle"));
				section.setLesQuestions(ThemeDAO.rechercher(theme.getId()).getQuestions());
				section.setTheme(theme);
				section.ajusterNombre(section.getLesQuestions().size());
				sections.add(section);
				i++;
			} 
			qcm.setSections(sections);
			
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return qcm;
		
	}
	
	
}