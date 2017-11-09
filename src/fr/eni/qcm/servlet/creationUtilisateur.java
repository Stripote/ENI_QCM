package fr.eni.qcm.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.bo.Role;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.UtilisateurDAO;



/**
 * Servlet implementation class creationUtilisateur
 */
public class creationUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creationUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		
		try {
			String nom = (request.getParameter("nom"));
			String prenom = (request.getParameter("prenom"));
			String login = (request.getParameter("login"));
			String password = (request.getParameter("password"));
			String role = (request.getParameter("role")); 
			
			Utilisateur utilisateur = new Utilisateur (nom, prenom, login, password, role);
			System.out.println("Nom:"+nom +"Prenom:"+ prenom+"login:"+ login+"password:"+ password +"role:"+role);
				
			utilisateur = UtilisateurDAO.ajouterRole(utilisateur);
			
			dispatcher = getServletContext().getRequestDispatcher("/administratif/menuAdministratif.jsp");
			dispatcher.forward(request,response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		
		
		
		
		
	}
		
	
}
