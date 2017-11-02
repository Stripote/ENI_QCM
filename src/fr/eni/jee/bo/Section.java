package fr.eni.jee.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Section {
	int id;
	Theme theme;
	int nbQuestions;
	List<Question> lesQuestions;
	
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
	public void ajusterNombre(int nbQuestionMax){
		Random rand = new Random();
		List<Integer> lesIndexDesQuestionsAGarder = new ArrayList<Integer>();
		//Création d'une liste d'index de la taille de nbQuestionMax
		for(int i=0; i<nbQuestions; i++){
			int aleat = rand.nextInt(nbQuestionMax);
			if(!lesIndexDesQuestionsAGarder.contains(aleat)) //Si le nombre aleatoire n'est pas dans la liste
				lesIndexDesQuestionsAGarder.add(aleat); //On l'y ajoute
			else if(i>0)
				i--;//Sinon on descend l'index d'1, car ce tour de FOR n'a servit a rien, il a ramené un aleat déjà dans la liste
		}
		//On parcours chacune des questions de la sections
		for(int j = 0; j<lesQuestions.size();j++){
			if(!lesIndexDesQuestionsAGarder.contains(j))//Si l'index de la question n'est pas dans la liste des index a garder
				lesQuestions.remove(j);//On retire la question
		}
	}
	
}
