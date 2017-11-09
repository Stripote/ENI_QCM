package fr.eni.qcm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.bo.Qcm;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.QcmDAO;
import fr.eni.jee.dal.UtilisateurDAO;

/**
 * Servlet implementation class creationInscription
 */
public class accesCreationInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accesCreationInscription() {
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
		List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
		List<Qcm> qcms = new ArrayList<Qcm>();
		
		try {
			utilisateurs = UtilisateurDAO.rechercher();
			qcms= QcmDAO.rechercher();
			
			request.getSession().setAttribute("listeUtilisateurs", utilisateurs);
			request.getSession().setAttribute("listeQcms", qcms);
			
			dispatcher = getServletContext().getRequestDispatcher("/administratif/inscription.jsp");
			dispatcher.forward(request, response);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
