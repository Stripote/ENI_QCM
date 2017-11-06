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
import fr.eni.jee.bo.Section;

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
		for (Section section : qcm.getSections()) {
			for (Question question : section.getLesQuestions()) {
				liste.add(question);
			}
		}

		//renvoie dans la jsp passageTest
		if (request.getParameter("retour")!=null) {
			Question question = liste.get(Integer.parseInt(request.getParameter("retour")));
			
			int indexGlobal = Integer.parseInt(request.getParameter("retour"));
			request.getSession().setAttribute("indexQuestion", indexGlobal);
			
			request.removeAttribute("retour");
			request.getSession().setAttribute("question", question);
						
			dispatcher = getServletContext().getRequestDispatcher("/candidat/passageTest.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		//renvoie vers la jsp syntheseTest autrement
		request.getSession().setAttribute("listeQuestion", liste);
		dispatcher = getServletContext().getRequestDispatcher("/candidat/syntheseTest.jsp");
		dispatcher.forward(request, response);
		
	}

}
