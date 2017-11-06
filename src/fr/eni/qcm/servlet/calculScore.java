package fr.eni.qcm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.bo.Qcm;
import fr.eni.jee.bo.Question;
import fr.eni.jee.bo.Reponse;

/**
 * Servlet implementation class calculScore
 */
public class calculScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calculScore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnregistrementResultat(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnregistrementResultat(request, response);
	}
	
	protected void EnregistrementResultat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Reponse> reponses= (List<Reponse>)request.getSession().getAttribute("reponsesCandidat");
		List<Question> list = (List<Question>)request.getSession().getAttribute("listeQuestion");
		//Qcm qcm =(Qcm)request.getSession().getAttribute("qcm");
		
		
		/*for (Question question : list) {
			Boolean bonneReponse= true;
			for (Reponse reponse : question.getReponses()) {
				if (reponses.contains(reponse)&&(reponse.getBonneReponse()==false)) {
					bonneReponse=false;
				}
				if ((!reponses.contains(reponse))&&(reponse.getBonneReponse()==true)) {
					bonneReponse=false;
				}
			}
		}*/
		
	}

}
