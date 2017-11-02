package fr.eni.jee.bo;

import java.sql.Date;
import java.util.List;

public class Qcm {
//getset
	int id;
	String Nom;
	boolean valide;
	Date dateCreation;
	Date Debut;
	Date Fin;
	List<Section> sections;
	
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDebut() {
		return Debut;
	}
	public void setDebut(Date debut) {
		Debut = debut;
	}
	public Date getFin() {
		return Fin;
	}
	public void setFin(Date fin) {
		Fin = fin;
	}
	
	
	
	public Qcm(){
		
		
		
	}
	public Qcm(int id, String nom, boolean valide, Date dateCreation, Date debut, Date fin) {
		super();
		this.id = id;
		Nom = nom;
		this.valide = valide;
		this.dateCreation = dateCreation;
		Debut = debut;
		Fin = fin;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
