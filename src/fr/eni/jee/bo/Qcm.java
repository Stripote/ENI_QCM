package fr.eni.jee.bo;

import java.sql.Date;
import java.util.List;

public class Qcm {

	int id;
	String Nom;
	boolean valide;
	Date Creation;
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
	public Date getCreation() {
		return Creation;
	}
	public void setCreation(Date creation) {
		Creation = creation;
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
	
	
	public Qcm(int id, String nom, boolean valide, Date creation, Date debut, Date fin) {
		super();
		this.id = id;
		Nom = nom;
		this.valide = valide;
		Creation = creation;
		Debut = debut;
		Fin = fin;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
