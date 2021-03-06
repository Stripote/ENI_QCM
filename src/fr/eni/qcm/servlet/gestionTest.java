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
		
		//recuperation de l'�tat du QCM
		Qcm qcm =(Qcm)request.getSession().getAttribute("qcm");
		
		ArrayList<Question> lesQuestionsPosees = new ArrayList<Question>();
		
		Question derniereQuestion = (Question)request.getSession().getAttribute("question");
		List<Reponse> reponses= (List<Reponse>)request.getSession().getAttribute("reponsesCandidat");
		
		List<String> libelleReponse = new ArrayList<String>();
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
		
		//Si chaque �l�ment de la liste des bonnes r�ponses est retrouv� dans les r�ponses utilisateur
		int nbBonnesReponses = 0;
		for(Reponse R : bonnesReponses){
			if(R.getBonneReponse() == true){
				nbBonnesReponses++;
				if(libelleReponse.contains(R.getLibelle())){
						reponseCorrecte = true;
				}
			}
		}
		//Et si le nombre de r�ponses soumises correspond au nombre de r�ponses attendues
		if(nbBonnesReponses != libelleReponse.size())
			reponseCorrecte = false;
		
		//Alors la r�ponse est juste
		Session sessionTest = (Session) request.getSession().getAttribute("session");

		try{
		if(reponseCorrecte){
			SessionDAO.ajouterReponse(sessionTest.getId(), libelleReponse, derniereQuestion.getId(), true);
		}
		else{
			SessionDAO.ajouterReponse(sessionTest.getId(), libelleReponse, derniereQuestion.getId(), false);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			request.getSession().setAttribute("session", sessionTest);
		}
		
		
		
		
		//envoie vers le servlet de synthese si derniere question du test	
		int a = qcm.getSections().size()-1;
		int b = qcm.getSections().get(a).getLesQuestions().size()-1;
		Question c = qcm.getSections().get(a).getLesQuestions().get(b);
		if (derniereQuestion.equals( c)){
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
				question = S.getLesQuestions().get(0);
				break;
			}
			
			List<Integer> listId = new ArrayList<Integer>();
			for (Question uneQuestion : S.getLesQuestions()) {
				listId.add(uneQuestion.getId());
			}
			if(listId.contains(derniereQuestion.getId())){
				int index = 1;
				for(Question Q : S.getLesQuestions()){
					if(Q.getId()==derniereQuestion.getId() && index == S.getLesQuestions().size()){
						newSection = true;
						break;	
					}
					else if(Q.getId()==derniereQuestion.getId()){
						question = S.getLesQuestions().get(index);
						questionChoisi = true;
						break;
					}
					index++;
				}
			}
			
			/*if(S.getLesQuestions().contains(derniereQuestion)){
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
			}*/
		}
		
		//envoie vers la servlet
		request.getSession().setAttribute("question", question);
		try{
			int indexGlobal = (Integer) request.getSession().getAttribute("indexQuestion");
			indexGlobal++;
			request.getSession().setAttribute("indexQuestion", indexGlobal);
		}catch(Exception e){
			int indexGlobal = 2;
			request.getSession().setAttribute("indexQuestion", indexGlobal);
		}
		dispatcher = getServletContext().getRequestDispatcher("/candidat/passageTest.jsp");
		dispatcher.forward(request, response);	
	}
	
}
