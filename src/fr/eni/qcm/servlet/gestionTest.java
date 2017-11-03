package fr.eni.qcm.servlet;

import java.io.IOException;
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


/**
 * Servlet implementation class gestionTest
 */
public class gestionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gestionnaireTest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gestionnaireTest(request, response);
	}

	protected void gestionnaireTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		
		//recuperation de l'état du QCM
		Qcm qcm =(Qcm)request.getSession().getAttribute("qcm");
		Question derniereQuestion = (Question)request.getSession().getAttribute("question");
		List<Reponse> reponses= (List<Reponse>)request.getSession().getAttribute("reponsesCandidat");
		
		String libelleReponse = null;
		//enregistrement des reponses
		if( !(request.getParameter("reponse") == null))
			libelleReponse = request.getParameter("reponse");
		else
			libelleReponse = "NO ANSWER";
		
		for (Reponse reponse : derniereQuestion.getReponses()) {
			if (reponse.getLibelle().equals(libelleReponse)) {
				reponses.add(reponse);
			}
		}	
		request.getSession().setAttribute("reponses", reponses);
		
		//envoie vers le servlet de synthese si derniere question du test
		if (derniereQuestion.equals(qcm.getSections().get(qcm.getSections().size()-1).getLesQuestions().get(qcm.getSections().get(qcm.getSections().size()-1).getLesQuestions().size()-1))) 
		{		
			dispatcher = getServletContext().getRequestDispatcher("/test/gestionSynthese");
			dispatcher.forward(request, response);
			return;
		}
		
		//recuperation de la question suivant
		Question question= new Question();
		for (int indexSection = 0; indexSection < (qcm.getSections().size()-1); indexSection++) {
			for (int indexQues = 0; indexQues < (qcm.getSections().get(indexSection).getLesQuestions().size()-1); indexQues++) {
				if (derniereQuestion.equals(qcm.getSections().get(indexSection).getLesQuestions().get(indexQues))) {
					if (indexQues==(qcm.getSections().get(indexSection).getNbQuestions()-1)) {
						question=qcm.getSections().get(indexSection+1).getLesQuestions().get(indexQues);
					}else {
						question=qcm.getSections().get(indexSection).getLesQuestions().get(indexQues+1);
					}					
				}
			}
		}
		
		//envoie vers la servlet
		request.setAttribute("question", question);
		dispatcher = getServletContext().getRequestDispatcher("/candidat/passageTest.jsp");
		dispatcher.forward(request, response);

		
		
		
		
		
		
		
	}
	
}
