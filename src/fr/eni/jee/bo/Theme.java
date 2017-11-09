package fr.eni.jee.bo;

import java.util.ArrayList;
import java.util.List;

public class Theme {
	int id;
	String nom;
	List<Question> questions;
	
	public Theme(String unNom){
		this.nom = unNom;
		this.questions = new ArrayList<Question>();
	}
	
	public Theme() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
}
