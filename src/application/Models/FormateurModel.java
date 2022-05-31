package application.Models;

public class FormateurModel {

	public int ID;
	public String Nom;
	public String Prenom;
	public String Domaine;
	public String Email;
	public int Num_telephone;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getDomaine() {
		return Domaine;
	}
	public void setDomaine(String domaine) {
		Domaine = domaine;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getNum_telephone() {
		return Num_telephone;
	}
	public void setNum_telephone(int num_telephone) {
		Num_telephone = num_telephone;
	}
	public FormateurModel(int iD, String nom, String prenom, String domaine, String email, int num_telephone) {
		super();
		ID = iD;
		Nom = nom;
		Prenom = prenom;
		Domaine = domaine;
		Email = email;
		Num_telephone = num_telephone;
	}
	
	
	
	

}
