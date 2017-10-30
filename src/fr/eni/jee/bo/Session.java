package fr.eni.jee.bo;

import java.sql.Date;

public class Session {

	int id;
	int score;
	Date inscription;
	Date datelimite;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getInscription() {
		return inscription;
	}
	public void setInscription(Date inscription) {
		this.inscription = inscription;
	}
	public Date getDatelimite() {
		return datelimite;
	}
	public void setDatelimite(Date datelimite) {
		this.datelimite = datelimite;
	}
	
	public Session(){
		
		
	}
	
	
	
	public Session(int id, int score, Date inscription, Date datelimite) {
		super();
		this.id = id;
		this.score = score;
		this.inscription = inscription;
		this.datelimite = datelimite;
	}
	
	
	
}
