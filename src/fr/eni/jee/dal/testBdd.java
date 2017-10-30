package fr.eni.jee.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.qcm.util.AccesBase;



public class testBdd {
	
	public static void test() {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		
		try{
			try {
				cnx = AccesBase.getConnection();
				rqt = cnx.prepareStatement("select * from utilisateurs");
				rs=rqt.executeQuery();		
			}finally{
				if (rs!=null) rs.close();
				if (rqt!=null) rqt.close();
				if (cnx!=null) cnx.close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
	
	
}
