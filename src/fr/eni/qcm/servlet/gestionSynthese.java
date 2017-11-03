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

/**
 * Servlet implementation class gestionSynthese
 */
public class gestionSynthese extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionSynthese() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gestionnaireSynthese(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gestionnaireSynthese(request, response);
	}
	
	protected void gestionnaireSynthese(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		Qcm qcm =(Qcm)request.getSession().getAttribute("qcm");
		List<Question> liste  =new ArrayList<Question>();
		
		//recuperation de la liste des questions du test
		for (int indexSection = 0; indexSection < (qcm.getSections().size()-1); indexSection++) {
			for (int indexQues = 0; indexQues < (qcm.getSections().get(indexSection).getNbQuestions()-1); indexQues++) {
				Question question= qcm.getSections().get(indexSection).getLesQuestions().get(indexQues);
				liste.add(question);
			}
		}
		
		
		//renvoie vers la jsp syntheseTest
		request.setAttribute("listeQuestion", liste);
		dispatcher = getServletContext().getRequestDispatcher("/candidat/syntheseTest.jsp");
		dispatcher.forward(request, response);
		
	}

}
