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
import fr.eni.jee.bo.Section;
import fr.eni.jee.bo.Session;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.SessionDAO;

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
		Session session=(Session)request.getSession().getAttribute("session");
		List<Question> liste  =new ArrayList<Question>();
		List<Reponse> listeReponse  =new ArrayList<Reponse>();
		String reponseDonnee=null;
		
		try {
			
			//recuperation de la liste des questions du test
			for (Section section : qcm.getSections()) {
				for (Question question : section.getLesQuestions()) {
					liste.add(question);
				}
			}

			//renvoie dans la jsp passageTest
			if (request.getParameter("retour")!=null) {
				Question question = liste.get(Integer.parseInt(request.getParameter("retour")));
				
				int indexGlobal = Integer.parseInt(request.getParameter("retour"))+1;
				request.getSession().setAttribute("indexQuestion", indexGlobal);
				
				request.removeAttribute("retour");
				request.getSession().setAttribute("question", question);			
				
				if (request.getSession().getAttribute("reponsesCandidat")!=null) {
					listeReponse = (ArrayList<Reponse>)request.getSession().getAttribute("reponsesCandidat");
				} else {
					listeReponse=SessionDAO.rechercherReponsesCandidat(session.getId());
				}
				for (Reponse reponse : listeReponse) {
					if (question.getReponses().contains(reponse)) {
						reponseDonnee+=reponse.getLibelle()+"#";
					}
				}
				request.getSession().setAttribute("reponseDonnee", reponseDonnee);
				
				dispatcher = getServletContext().getRequestDispatcher("/candidat/passageTest.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			//renvoie vers la jsp syntheseTest autrement
			Boolean testTermine=true;
			request.getSession().setAttribute("testTermine", testTermine);
			SessionDAO.testTermine(session.getId());
			
			request.getSession().setAttribute("listeQuestion", liste);
			dispatcher = getServletContext().getRequestDispatcher("/candidat/syntheseTest.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
				
	}

}
