package fr.eni.jee.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Section {
	int id;
	Theme theme;
	int nbQuestions;
	List<Question> lesQuestions;
	
	public Section(Theme unTheme, int nbQuestions){
		this.theme = unTheme;
		this.nbQuestions = nbQuestions;
		this.id = 0;
		this.lesQuestions = new ArrayList<Question>();
	}
	
	public Section() {
		// TODO Auto-generated constructor stub
	}

	public List<Question> getLesQuestions() {
		return lesQuestions;
	}
	public void setLesQuestions(List<Question> lesQuestions) {
		this.lesQuestions = lesQuestions;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public int getNbQuestions() {
		return nbQuestions;
	}
	public void setNbQuestions(int nbQuestions) {
		this.nbQuestions = nbQuestions;
	}
	
	/***
	 * Permet de ne garder, aléatoirement, que le nombre de questions assignées par 
	 * le formateur, en retirant les questions en trop
	 * @param nbQuestionMax
	 */
	public void ajusterNombre(){
		Random rand = new Random();
		int nbQuestionMax = lesQuestions.size();
		ArrayList<Integer> lesIndexDesQuestionsAGarder = new ArrayList<Integer>();
		ArrayList<Question> lesQuestionsOrdonnees = new ArrayList();
		HashMap temp = new HashMap();
		
		//Création d'une liste d'index de la taille de nbQuestionMax
		int i=0;
		while(i<nbQuestions){
			int aleat = rand.nextInt(nbQuestionMax);
			if(!lesIndexDesQuestionsAGarder.contains(aleat)){ //Si le nombre aleatoire n'est pas dans la liste
				lesIndexDesQuestionsAGarder.add(aleat); //On l'y ajoute
				i++;
			}
		}
		
		//Conversion de la liste des questions en HashMap pour conserver les indexs
		int tmpIndex = 0;
		for(Question Q : lesQuestions){
			temp.put(tmpIndex, Q);
			tmpIndex++;
		}
		
		//On parcours chacune des questions de la sections, pour les retirer via leurs indexs
		int tailleAvantRetrait = lesQuestions.size();
		for(int j = 0; j<tailleAvantRetrait;j++){
			if(!lesIndexDesQuestionsAGarder.contains(j)){//Si l'index de la question n'est pas dans la liste des index a garder
				temp.remove(j);
			}
		}
		
		//Conversion inverse d'un HashMap en liste de Questions
		Iterator it = temp.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry me = (Map.Entry)it.next();
			lesQuestionsOrdonnees.add( (Question) me.getValue());
		}
		//Attribution nouvelle liste de questions à l'objet Section courant
		lesQuestions = lesQuestionsOrdonnees;
	}
	
}
