package fr.eni.jee.bo;

import java.util.ArrayList;
import java.util.List;

public class Question {
	int id;
	String enonce;
	String image;
	List<Reponse> reponses;
	
	public Question(String unEnonce){
		this.enonce = unEnonce;
		this.reponses  = new ArrayList<Reponse>();
	}
	
	public Question() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}
	
	@Override
	public String toString(){
		return this.enonce;
	}
	
	
	
}
