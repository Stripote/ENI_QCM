package fr.eni.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.bo.Qcm;
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
}
