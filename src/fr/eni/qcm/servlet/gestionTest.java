package fr.eni.qcm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import fr.eni.jee.dal.SessionDAO;


/**
 * Servlet implementation class gestionTest
 */
public class gestionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gestionnaireTest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gestionnaireTest(request, response);
	}

	protected void gestionnaireTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		
		//recuperation de l'état du QCM
		Qcm qcm =(Qcm)request.getSession().getAttribute("qcm");
		
		ArrayList<Question> lesQuestionsPosees = new ArrayList<Question>();
		
		Question derniereQuestion = (Question)request.getSession().getAttribute("question");
		List<Reponse> reponses= (List<Reponse>)request.getSession().getAttribute("reponsesCandidat");
		
		List<String> libelleReponse = null;
		//enregistrement des reponses du candidat
		if(request.getParameter("reponse") != null)   
			libelleReponse = new ArrayList<String>(Arrays.asList(request.getParameterValues("reponse")));
		
		for (Reponse reponse : derniereQuestion.getReponses()) {
			//System.out.println("____\nreponse saisie : "+libelleReponse+"\nreponse attendue : "+reponse);
			if (reponse.getLibelle().equals(libelleReponse)) {
				reponses.add(reponse);
			}
		}
		request.getSession().setAttribute("reponsesCandidat", reponses);
		
		
		List<Reponse> bonnesReponses = derniereQuestion.getReponses();
		Boolean reponseCorrecte = false;
		
		//Si chaque élément de la liste des bonnes réponses est retrouvé dans les réponses utilisateur
		int nbBonnesReponses = 0;
		for(Reponse R : bonnesReponses){
			if(R.getBonneReponse() == true){
				nbBonnesReponses++;
				if(libelleReponse.contains(R.getLibelle())){
						reponseCorrecte = true;
				}
			}
		}
		//Et si le nombre de réponses soumises correspond au nombre de réponses attendues
		if(nbBonnesReponses != libelleReponse.size())
			reponseCorrecte = false;
		
		//Alors la réponse est juste
		Session sessionTest = (Session) request.getSession().getAttribute("session");
		int score = sessionTest.getScoreUtilisateur();
		try{
		if(reponseCorrecte){
			score++;
			sessionTest.setScoreUtilisateur(score);
			SessionDAO.ajouterReponse(sessionTest.getId(), libelleReponse, derniereQuestion.getId(), true, score);
		}
		else{
			SessionDAO.ajouterReponse(sessionTest.getId(), libelleReponse, derniereQuestion.getId(), false, score);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			request.getSession().setAttribute("session", sessionTest);
		}
		
		
		
		
		//envoie vers le servlet de synthese si derniere question du test	
		if (derniereQuestion.equals(qcm.getSections().get(qcm.getSections().size()-1).getLesQuestions().get(qcm.getSections().get(qcm.getSections().size()-1).getLesQuestions().size()-1))) {
			dispatcher = getServletContext().getRequestDispatcher("/test/gestionSynthese");
			dispatcher.forward(request, response);
			return;
		}
		
		//recuperation de la question suivant
		Question question= new Question();
		
		/*
		for (int indexSection = 0; indexSection < (qcm.getSections().size()-1); indexSection++) {
			for (int indexQues = 0; indexQues < (qcm.getSections().get(indexSection).getNbQuestions()-1); indexQues++) {
				if (derniereQuestion.equals(qcm.getSections().get(indexSection).getLesQuestions().get(indexQues))) {
					if (indexQues==(qcm.getSections().get(indexSection).getNbQuestions()-1)) {
						question=qcm.getSections().get(indexSection+1).getLesQuestions().get(indexQues);
						break;
					}else {
						question=qcm.getSections().get(indexSection).getLesQuestions().get(indexQues+1);
						break;
					}	
				}
			}
		}*/
		//On parcour la liste des sections 
		Boolean questionChoisi = false;
		Boolean newSection = false;
		for (Section S : qcm.getSections()) {
			if(questionChoisi)
				break;
			if(newSection){
				question = S.getLesQuestions().get(1);
				break;
			}
			if(S.getLesQuestions().contains(derniereQuestion)){
				int index = 1;
				for(Question Q : S.getLesQuestions()){
					if(Q.equals(derniereQuestion) && index == S.getLesQuestions().size()){
						newSection = true;
						break;	
					}
					else if(Q.equals(derniereQuestion)){
						question = S.getLesQuestions().get(index);
						questionChoisi = true;
						break;
					}
					index++;
				}
			}
		}
		
		//envoie vers la servlet
		request.getSession().setAttribute("question", question);
		dispatcher = getServletContext().getRequestDispatcher("/candidat/passageTest.jsp");
		dispatcher.forward(request, response);	
	}
	
}
