package fr.eni.jee.bo;

public class Reponse {
	String libelle;
	Boolean bonneReponse;
	
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Boolean getBonneReponse() {
		return bonneReponse;
	}
	public void setBonneReponse(Boolean bonneReponse) {
		this.bonneReponse = bonneReponse;
	}
	
	public String toString(){
		if(this.getBonneReponse())
			return this.libelle+"\nBONNE REPONSE";
		else
			return this.libelle+"\nMAUVAISE REPONSE";
	}
	
	
	
	
}
