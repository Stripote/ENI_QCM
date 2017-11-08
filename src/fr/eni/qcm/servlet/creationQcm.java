package fr.eni.qcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.bo.Qcm;
import fr.eni.jee.bo.Section;
import fr.eni.jee.bo.Theme;
import fr.eni.jee.dal.QcmDAO;
import fr.eni.jee.dal.ThemeDAO;

/**
 * Servlet implementation class creationTest
 */
public class creationQcm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creationQcm() {
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
		try {
			//Création du QCM
			String nom = (request.getParameter("nom"));
			Qcm qcm = new Qcm (nom);
			
			
			String[] idTheme =  request.getParameterValues("themes");
			String[] nbQuestions = request.getParameterValues("nbQuestions");
			int compteur = 0;
			for(String S : idTheme){
				String unTheme = S;
				String unNombreDeQuestion = nbQuestions[compteur];
				System.out.println("Theme : "+unTheme+" de "+unNombreDeQuestion+" questions");
				Theme leTheme = (Theme)ThemeDAO.rechercher(Integer.parseInt(unTheme));
				Section laSection = new Section(leTheme, Integer.parseInt(unNombreDeQuestion));
				qcm.getSections().add(laSection);
				compteur++;
			}
			QcmDAO.ajouter(qcm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
