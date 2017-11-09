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
import fr.eni.jee.dal.QcmDAO;
import fr.eni.jee.dal.QuestionDAO;
import fr.eni.jee.dal.SessionDAO;
import fr.eni.jee.dal.UtilisateurDAO;

/**
 * Servlet implementation class listerQcm
 */
public class listerQcm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listerQcm() {
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
		try{
			//recuperation de l 'utilisateur
			Utilisateur user = null;
			user = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
			
			//recuperation de la liste de qcm
			if(user != null){
				if("Candidat".equals(user.getRole())){
					ArrayList<Qcm> listQcms = null;
					ArrayList<Integer> inscriptions= UtilisateurDAO.rechercherInscription(user.getId());
					ArrayList<Qcm> qcms= QcmDAO.rechercher();
					for (Qcm qcm : qcms) {
						if (inscriptions.contains(qcm.getId())) {
							if (listQcms==null) {
								listQcms = new ArrayList<Qcm>();
							}							
							listQcms.add(qcm);
						}
					}
					request.getSession().setAttribute("listeQcms", listQcms);
				}
				if("Formateur".equals(user.getRole())){
					ArrayList<Qcm> listQcms= QcmDAO.rechercher();
					request.getSession().setAttribute("listeQcms", listQcms);
				}
			}
			
			
						
			//verification des sessions de test en cours			
			Session testEnCours =SessionDAO.rechercherTestEnCours(user.getId());
			if (testEnCours!=null && user.getRole().equals("Candidat")) {
				Qcm qcmEnCours =testEnCours.getQcm();
				
				List<Reponse> reponses=new ArrayList<Reponse>();
				reponses=SessionDAO.rechercherReponsesCandidat(testEnCours.getId());
				Question derniereQuestion= QuestionDAO.rechercher(SessionDAO.rechercherDerniereQuestion(testEnCours.getId()));
				
				
				int indexQuestion =1;
				int index = 1;
				for (Section section : qcmEnCours.getSections()) {
					for (Question question : section.getLesQuestions()) {
						if (question.getId()==derniereQuestion.getId()) {
							indexQuestion = index;
							break;
						}
						index++;
					}
				}
				request.getSession().setAttribute("indexQuestion", indexQuestion);
				
				request.getSession().setAttribute("session", testEnCours);
				request.getSession().setAttribute("qcm", qcmEnCours);
				request.getSession().setAttribute("question", derniereQuestion);
				request.getSession().setAttribute("reponsesCandidat", reponses);
				
				dispatcher = getServletContext().getRequestDispatcher("/candidat/passageTest.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			
			
			
			//renvoie vers les menus en fonction des rôles							
			if(user != null){
				if("Candidat".equals(user.getRole())){
					
					dispatcher = getServletContext().getRequestDispatcher("/candidat/menuCandidat.jsp");
					dispatcher.forward(request,response);
					return;
				}
				if("Formateur".equals(user.getRole())){
					dispatcher = getServletContext().getRequestDispatcher("/formateur/menuFormateur.jsp");
					dispatcher.forward(request,response);
					return;
				}
				if("Administrateur".equals(user.getRole())){
					dispatcher = getServletContext().getRequestDispatcher("/administratif/menuAdministratif.jsp");
					dispatcher.forward(request,response);
					return;
				}
			}
			
			//renvoie vers le menu principal si aucun rôle
			dispatcher = getServletContext().getRequestDispatcher("/index.html");
			dispatcher.forward(request,response);
							
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
