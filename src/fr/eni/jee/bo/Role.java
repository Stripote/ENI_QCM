package fr.eni.jee.bo;

public class Role {
int id;
String libelle;




public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}

public Role(){
		
}



public Role(int id, String libelle) {
	super();
	this.id = id;
	this.libelle = libelle;
}



}
