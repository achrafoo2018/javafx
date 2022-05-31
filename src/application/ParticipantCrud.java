package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Models.FormateurModel;
import application.Models.FormationModel;
import application.Models.ParticipantModel;

public class ParticipantCrud {

	static Scanner sc = new Scanner(System.in);
	static PreparedStatement pr =null;
	
	
	public String createEtudiant(Connection cn,int Matricule_participant,String Nom,String Prenom,int Profil,Date Date_naissance) {
		// saisir les information du nouvelle Formation

		try {
			//contréle sur les données saisi
			if((Integer)Matricule_participant ==null || Nom.length()==0 || Prenom.length()==0 || (Integer)Profil ==null) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else if(!ProfilCrud.getProfilById(cn, Profil)) {
				return "Ce profil n'existe Pas!";
			}else if(ParticipantCrud.getParticipantById(cn, Matricule_participant)) {
				return "Cette matricule est deja existe!";
			}else {
				pr = cn.prepareStatement("insert into Participant (Matricule_participant,Nom,Prenom,Profil,Date_naissance) values (?,?,?,?,?)");
				pr.setInt(1, Matricule_participant);
				pr.setString(2,Nom.trim());
				pr.setString(3,Prenom.trim());
				pr.setInt(4, Profil);
				pr.setDate(5, Date_naissance);
				pr.executeUpdate();
				return "Le nouvel participant a été ajouté avec succés.";
			}
		}catch(SQLException e) {
			return "Opération échouée !";
		}
	}
	
	
	
public String modifyEtudiant(Connection cn,int id,String Nom,String Prenom,int Profil,Date Date_naissance) {
		
		try {
			//contréle sur les données saisi
			if((Integer)id ==null || Nom.length()==0 || Prenom.length()==0 || (Integer)Profil ==null) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else if(!ProfilCrud.getProfilById(cn, Profil)) {
				return "Ce profil n'existe Pas!";
			}else if(!ParticipantCrud.getParticipantById(cn, id)) {
				return "Cette matricule n'existe pas!";
			}else {
				pr = cn.prepareStatement("update Participant set Nom=?,Prenom=?,Profil=?,Date_naissance=? where Matricule_participant = ?");
				pr.setString(1,Nom.trim());
				pr.setString(2,Prenom.trim());
				pr.setInt(3, Profil);
				pr.setDate(4, Date_naissance);
				pr.setInt(5, id);
				pr.executeUpdate();
				return "L'participant a été modifié avec succés.";
			}
			
		}catch(SQLException e) {
			return "Opération échouée !";
		}
		
	}




public static ParticipantModel getSignleParticipantById(Connection cn,int id) {
	// verifier si le Formateur est deja existe
	try {
		pr = cn.prepareStatement("select Nom,Prenom,Profil,Date_naissance from participant where Matricule_participant=? ");
		pr.setInt(1, id);
		ResultSet rs = pr.executeQuery();
		
		boolean existe = rs.next();
		if(existe) {
			String profil =ProfilCrud.getProfilLibelleById(cn, rs.getInt(3));
			
			return new ParticipantModel(id+"", rs.getString(1), rs.getString(2), profil, rs.getDate(4));
		}
		
		return null;
		
		}catch(SQLException e) {
			// System.out.println(e.getMessage());
			return null;
		}
	}


	
	
public String deleteEtudiant(Connection cn,int id) {
	// supprimer Etudiant 
	if(getParticipantById(cn, id)) {
	try {
		pr = cn.prepareStatement("delete from Participant where Matricule_participant = ?");
		pr.setInt(1, id);
		pr.executeUpdate();
		return "L'participant a été supprimé.";
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		return "Opération échouée !";
	}
	}else {
		return "Il ny a pas d'participant avec cette matricule!";
	}
	
}
	
	
	




public  ArrayList<ParticipantModel> getAllEtudiant(Connection cn) {
	ArrayList<ParticipantModel> resultArray = new ArrayList<ParticipantModel>();
	try {
		pr = cn.prepareStatement("select Matricule_participant,Nom,Prenom,Profil,Date_naissance from Participant order by Nom asc");
		ResultSet rs = pr.executeQuery();
		while(rs.next()) {
			String profil = ProfilCrud.getProfilLibelleById(cn, rs.getInt(4));
			resultArray.add(new ParticipantModel(String.format("%08d", rs.getInt(1)),rs.getString(2),rs.getString(3),profil,rs.getDate(5)) );
		}
		
		return resultArray;
		
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Opération échouée !");
			return resultArray;
			
		}
	}










	
	
	public static boolean getParticipantById(Connection cn,int id) {
		// verifier si le participant est deja existe
		try {
			pr = cn.prepareStatement("select Matricule_participant from participant where Matricule_participant =? ");
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			
			boolean existe = rs.next();
			if(existe) {
				return true;
			}
			
			return false;
			
			}catch(SQLException e) {
				// System.out.println(e.getMessage());
				return false;
			}
		}
	

}
