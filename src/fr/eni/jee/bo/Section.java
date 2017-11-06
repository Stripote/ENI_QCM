package fr.eni.jee.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;

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
	 * Permet de ne garder, al�atoirement, que le nombre de questions assign�es par 
	 * le formateur, en retirant les questions en trop
	 * @param nbQuestionMax
	 */
	public void ajusterNombre(){
		Random rand = new Random();
		int nbQuestionMax = lesQuestions.size();
		ArrayList<Integer> lesIndexDesQuestionsAGarder = new ArrayList<Integer>();
		//Cr�ation d'une liste d'index de la taille de nbQuestionMax
		int i=0;
		while(i<nbQuestions){
			int aleat = rand.nextInt(nbQuestionMax);
			if(!lesIndexDesQuestionsAGarder.contains(aleat)){ //Si le nombre aleatoire n'est pas dans la liste
				lesIndexDesQuestionsAGarder.add(aleat); //On l'y ajoute
				System.out.println("a garder :"+lesQuestions.get(aleat));
				i++;
			}
		}
		//On parcours chacune des questions de la sections
		int tailleAvantRetrait = lesQuestions.size();
		for(int j = 0; j<tailleAvantRetrait;j++){
			if(!lesIndexDesQuestionsAGarder.contains(j)){//Si l'index de la question n'est pas dans la liste des index a garder
				Question toRemove = lesQuestions.get(j);
				System.out.println(toRemove+" retir�e");
				lesQuestions.remove(toRemove);//On retire la question
			}
		}
		ArrayList<Question> lesQuestionsOrdonnees = new ArrayList<Question>(lesQuestions);
		lesQuestions = lesQuestionsOrdonnees;
	}
	
}
