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

	
	public static ArrayList<Qcm> rechercher() throws SQLException {
		
		ArrayList<Qcm> listeqcm = new ArrayList<Qcm>();
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select id, nom, creation from qcm");
			rs=rqt.executeQuery();
			
			while (rs.next()){

				Qcm qcm = new Qcm();
				qcm.setId(rs.getInt("id"));
				qcm.setNom(rs.getString("nom"));
				qcm.setCreation(rs.getDate("creation"));
				
				listeqcm.add(qcm);
			}

			
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeqcm;
		
	}
	
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
				
				section.setTheme(theme);
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
