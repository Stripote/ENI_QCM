package fr.eni.qcm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		Qcm qcm =(Qcm)request.getAttribute("qcm");
		List<Question> liste  =new ArrayList<Question>();
		
		
		for (int indexSection = 0; indexSection < (qcm.getSections().size()-1); indexSection++) {
			for (int indexQues = 0; indexQues < (qcm.getSections().get(indexSection).getLesQuestions().size()-1); indexQues++) {
				Question question= qcm.getSections().get(indexSection).getLesQuestions().get(indexQues);
				liste.add(question);
			}
		}
		
		
	}

}
