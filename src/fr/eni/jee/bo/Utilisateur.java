package fr.eni.jee.bo;

import java.util.List;

public class Utilisateur {

	int id;
	String nom;
	String prenom;
	String login;
	String password;
	List<Role> lesRoles;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getLesRoles() {
		return lesRoles;
	}
	public void setLesRoles(List<Role> lesRoles) {
		this.lesRoles = lesRoles;
	}
	
	
	public Utilisateur(){
		
		
		
	}
	
	
	public Utilisateur(int id, String nom, String prenom, String login, String password, List<Role> lesRoles) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.lesRoles = lesRoles;
	}
	
	
	
	
}
