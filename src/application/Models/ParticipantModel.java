package application.Models;

import java.sql.Date;

public class ParticipantModel {

	public String Matricule_participant;
	public String Nom;
	public String Prenom;
	public String Profil;
	public Date Date_naissance;
	
	
	public String getMatricule_participant() {
		return Matricule_participant;
	}
	public void setMatricule_participant(String matricule_participant) {
		Matricule_participant = matricule_participant;
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
	public String getProfil() {
		return Profil;
	}
	public void setProfil(String profil) {
		Profil = profil;
	}
	public Date getDate_naissance() {
		return Date_naissance;
	}
	public void setDate_naissance(Date date_naissance) {
		Date_naissance = date_naissance;
	}
	public ParticipantModel(String matricule_participant, String nom, String prenom, String profil, Date date_naissance) {
		super();
		Matricule_participant = matricule_participant;
		Nom = nom;
		Prenom = prenom;
		Profil = profil;
		Date_naissance = date_naissance;
	}
	

}
