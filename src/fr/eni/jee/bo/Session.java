package fr.eni.jee.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Session {

	private int id;
	private Date datePassage;
	private Qcm qcm;
	private Utilisateur utilisateur;
	private int scoreMax;
	private int scoreUtilisateur;
	private List<Reponse> reponses;

	public Session(Qcm unQcm, Utilisateur unUtilisateur){
		this.reponses = new ArrayList<Reponse>();
		this.datePassage = new Date(System.currentTimeMillis());
		this.qcm = unQcm;
		this.utilisateur = unUtilisateur;
		this.scoreMax = qcm.getScoreMax();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDatePassage() {
		return datePassage;
	}
	public void setDatePassage(Date datePassage) {
		this.datePassage = datePassage;
	}
	public Qcm getQcm() {
		return qcm;
	}
	public void setQcm(Qcm qcm) {
		this.qcm = qcm;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public int getScoreMax() {
		return scoreMax;
	}
	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}
	public int getScoreUtilisateur() {
		return scoreUtilisateur;
	}
	public void setScoreUtilisateur(int scoreUtilisateur) {
		this.scoreUtilisateur = scoreUtilisateur;
	}
	public List<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}
	
	

	
	
}
