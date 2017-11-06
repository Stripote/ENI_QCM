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
public class creationTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creationTest() {
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
		ArrayList<Section> lesSections = null;
		List<Reponse> reponses=new ArrayList<Reponse>();
		try {
			//Création du QCM
			int test = Integer.parseInt(request.getParameter("idQcm").toString());
			System.out.println("ID DU QCM :" + test);
			Qcm qcm = QcmDAO.rechercher(test);
			Question premiereQuestion = qcm.getSections().get(0).getLesQuestions().get(0);
			request.getSession().setAttribute("qcm", qcm);
			request.getSession().setAttribute("question", premiereQuestion);
			request.getSession().setAttribute("reponsesCandidat", reponses);
			
			Session sessionTest = new Session(qcm, (Utilisateur)request.getSession().getAttribute("utilisateurConnecte"));;
			sessionTest = SessionDAO.ajouter(sessionTest);
			
			request.getSession().setAttribute("session", sessionTest);
			dispatcher = getServletContext().getRequestDispatcher("/candidat/passageTest.jsp");
			dispatcher.forward(request, response);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
