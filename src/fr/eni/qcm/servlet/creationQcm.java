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
import fr.eni.jee.bo.Question;
import fr.eni.jee.bo.Reponse;
import fr.eni.jee.bo.Section;
import fr.eni.jee.bo.Session;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.QcmDAO;
import fr.eni.jee.dal.SessionDAO;

/**
 * Servlet implementation class creationTest
 */
public class creationQcm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creationQcm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}
	
	
	
	
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		
		
		
		
		try {
			//Création du QCM
			String nom = (request.getParameter("nom"));
			Qcm qcm = new Qcm (nom);
			qcm = QcmDAO.ajouter(qcm);
			
			int idTheme = Integer.parseInt(request.getParameter("idTheme").toString());
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
