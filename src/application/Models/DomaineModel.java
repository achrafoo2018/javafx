package application.Models;

public class DomaineModel {

	public int ID;
	public String Libelle;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLibelle() {
		return Libelle;
	}
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
	public DomaineModel(int iD, String libelle) {
		super();
		ID = iD;
		Libelle = libelle;
	} 
	
	

}
