package fr.eni.qcm.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.bo.Session;
import fr.eni.jee.dal.SessionDAO;

/**
 * Servlet implementation class gestionResultat
 */
public class gestionResultat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionResultat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		afficherResultat(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		afficherResultat(request, response);
	}
	
	protected void afficherResultat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		Session session=(Session)request.getSession().getAttribute("session");
		
		
		try {
			//recuperation des resultats
			session = SessionDAO.rechercherScore(session.getId());
			Integer pourcentage=(int)(((float)session.getScoreUtilisateur()/(float)session.getScoreMax())*100);
			String resultat=null;
			if (pourcentage<33) {
				resultat="NON-ACQUIS";
			}else if (pourcentage<66) {
				resultat="EN COURS D'ACQUISITION";
			}else {
				resultat="ACQUIS";
			}
			
			//renvoie vers la jsp pageResultat
			request.getSession().setAttribute("pourcentage", pourcentage);
			request.getSession().setAttribute("resultat", resultat);
			dispatcher = getServletContext().getRequestDispatcher("/candidat/pageResultat.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
