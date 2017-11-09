package fr.eni.qcm.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.dal.UtilisateurDAO;

/**
 * Servlet implementation class validerInscription
 */
public class validerInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validerInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gererInscription(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gererInscription(request, response);
	}
	
	protected void gererInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
				
		try {
			//Inscrire un candidat à un test
			String[] idU =(String[])request.getParameterValues("candidats");
			String[] idQ =(String[])request.getParameterValues("qcms");
			int idUtilisateur=-1;
			int idQcm=-1;
			
			for (String string : idU) {
				idUtilisateur= Integer.parseInt(string);
			}
			for (String string : idQ) {
				idQcm= Integer.parseInt(string);
			}
			
			if (idUtilisateur!=-1&&idQcm!=-1) {
				UtilisateurDAO.inscription(idUtilisateur, idQcm);
			}
						
			//renvoie vers le menu
			dispatcher = getServletContext().getRequestDispatcher("/administratif/menuAdministratif.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
