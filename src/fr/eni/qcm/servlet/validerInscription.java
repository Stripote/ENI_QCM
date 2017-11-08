package fr.eni.qcm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int idUtilisateur= Integer.parseInt(request.getParameter("idUtilisateur"));
		int idQcm= Integer.parseInt(request.getParameter("idQcm"));;
		
		//TODO: Faire table en bdd pour inscrire un candidat à un test puis faire DAO
		
		dispatcher = getServletContext().getRequestDispatcher("/administratif/menuAdministratif.jsp");
		dispatcher.forward(request, response);
		
	}

}
