package application.Models;

public class FormationModel {

	public int ID;
	public String Intitule;
	public String Domaine;
	public int Nombre_jours;
	public int Annee;
	public int mois;
	public String Formateur;
	public int Nombre_participants;
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getIntitule() {
		return Intitule;
	}
	public void setIntitule(String intitule) {
		Intitule = intitule;
	}
	public String getDomaine() {
		return Domaine;
	}
	public void setDomaine(String domaine) {
		Domaine = domaine;
	}
	public int getNombre_jours() {
		return Nombre_jours;
	}
	public void setNombre_jours(int nombre_jours) {
		Nombre_jours = nombre_jours;
	}
	public int getAnnee() {
		return Annee;
	}
	public void setAnnee(int annee) {
		Annee = annee;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public String getFormateur() {
		return Formateur;
	}
	public void setFormateur(String formateur) {
		Formateur = formateur;
	}
	public int getNombre_participants() {
		return Nombre_participants;
	}
	public void setNombre_participants(int nombre_participants) {
		Nombre_participants = nombre_participants;
	}
	public FormationModel(int iD, String intitule, String domaine, int nombre_jours, int annee, int mois,
			String formateur, int nombre_participants) {
		super();
		ID = iD;
		Intitule = intitule;
		Domaine = domaine;
		Nombre_jours = nombre_jours;
		Annee = annee;
		this.mois = mois;
		Formateur = formateur;
		Nombre_participants = nombre_participants;
	}

	
	
	
	
	
}
