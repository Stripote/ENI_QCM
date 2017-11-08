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
import fr.eni.jee.bo.Session;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.QcmDAO;
import fr.eni.jee.dal.QuestionDAO;
import fr.eni.jee.dal.SessionDAO;

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
<<<<<<< Upstream, based on origin/master
			
			
			
			
			
			
			ArrayList<Qcm> qcms = QcmDAO.rechercher();
		
=======
			//recuperation de la liste de qcm
			ArrayList<Qcm> qcms = QcmDAO.rechercher();		
>>>>>>> 794a358 mise à jour du systeme de retour au test après une deconnexion
			request.getSession().setAttribute("listeQcms", qcms);
						
			//verification des sessions de test en cours
			Utilisateur user = null;
			user = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
			
			Session testEnCours =SessionDAO.rechercherTestEnCours(user.getId());
			if (testEnCours!=null) {
				Qcm qcm =testEnCours.getQcm();
				
				List<Reponse> reponses=new ArrayList<Reponse>();
				reponses=SessionDAO.rechercherReponsesCandidat(testEnCours.getId());
				Question derniereQuestion= QuestionDAO.rechercher(SessionDAO.rechercherDerniereQuestion(testEnCours.getId()));
				
				request.getSession().setAttribute("session", testEnCours);
				request.getSession().setAttribute("qcm", qcm);
				request.getSession().setAttribute("question", derniereQuestion);
				request.getSession().setAttribute("reponsesCandidat", reponses);
				
				dispatcher = getServletContext().getRequestDispatcher("/candidat/passageTest.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			
			
			
			//renvoie vers les menus en fonction des r�les
			dispatcher = getServletContext().getRequestDispatcher("/candidat/menuCandidat.jsp");
								
			if(user != null){
				if("Formateur".equals(user.getRole()))
					dispatcher = getServletContext().getRequestDispatcher("/formateur/menuFormateur.jsp");
				else if("Administrateur".equals(user.getRole()))
					dispatcher = getServletContext().getRequestDispatcher("/administratif/menuAdministratif.jsp");
			}
			dispatcher.forward(request,response);
							
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
