package fr.eni.qcm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.bo.Question;
import fr.eni.jee.bo.Reponse;
import fr.eni.jee.bo.Theme;
import fr.eni.jee.dal.QuestionDAO;
import fr.eni.jee.dal.ThemeDAO;

/**
 * Servlet implementation class administrerQcm
 */
public class administrerQcm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administrerQcm() {
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
		ArrayList<Theme> lesThemes = new ArrayList<Theme>();
		try {
			if(request.getParameter("libelle") != null){
				System.out.println("ajout d'un theme en BDD");
				Theme nouveauTheme = new Theme(request.getParameter("libelle"));
				ThemeDAO.ajouter(nouveauTheme);
			}else if(request.getParameter("enonce") != null 
					&& request.getParameter("themes") != null 
					&& request.getParameter("reponse")!= null){
						String libelleQuestion = request.getParameter("enonce");
						String idTheme = request.getParameter("themes");
						String[] lesReponses = request.getParameterValues("reponse");
						String[] lesBonnesReponses = request.getParameterValues("vraiFaux");
						ArrayList<Integer> indexesBonnesReponses = new ArrayList<Integer>();
						for(String S : lesBonnesReponses){
							indexesBonnesReponses.add(Integer.parseInt(S));
						}
						Question nouvelleQuestion = new Question(libelleQuestion);
						Integer compteur = 1;
						for(String S : lesReponses){
							Boolean ok = false;
							if(indexesBonnesReponses.size() > 0){
								if(indexesBonnesReponses.contains(compteur))
									ok = true;
							}
							Reponse R = new Reponse(S, ok);
							nouvelleQuestion.getReponses().add(R);
							compteur++;
						}
						QuestionDAO.ajouter(nouvelleQuestion, idTheme);
			}
			//On charge une liste de Themes
			
			lesThemes = ThemeDAO.rechercher();
		}catch (SQLException e) {e.printStackTrace();}
		
		//redirection
		request.setAttribute("listeThemes", lesThemes);
		dispatcher = getServletContext().getRequestDispatcher("/formateur/creationTheme.jsp");
		dispatcher.forward(request, response);
	}


}
