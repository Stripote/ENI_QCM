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
			if(request.getParameter("nomTheme") != null){
				Theme nouveauTheme = new Theme(request.getParameter("nomTheme"));
				ThemeDAO.ajouter(nouveauTheme);
			}else if(request.getParameter("libelleQuestion") != null 
					&& request.getParameter("themeId") != null 
					&& request.getParameter("reponses")!= null){
						String libelleQuestion = request.getParameter("libelleQuestion");
						String idTheme = request.getParameter("themeId");
						String[] lesReponses = request.getParameterValues("reponses");
						String[] lesBonnesReponses = request.getParameterValues("bonneReponse");
						Question nouvelleQuestion = new Question(libelleQuestion);
						int compteur = 0;
						for(String S : lesReponses){
							Reponse R = new Reponse(S, Boolean.valueOf(lesBonnesReponses[compteur]));
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
		dispatcher = getServletContext().getRequestDispatcher("creationTheme.jsp");
		dispatcher.forward(request, response);
	}


}
