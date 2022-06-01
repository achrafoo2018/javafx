package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Models.FormateurModel;
import application.Models.FormationModel;

public class FormationCrud {

	static Scanner sc = new Scanner(System.in);
	static PreparedStatement pr =null;
	
	
	public String createFormation(Connection cn,String Intitulé,int Domaine,int Nombre_jours,int Annee, int mois,int Formateur,int Nombre_participants) {
		// saisir les information du nouvelle Formation

		try {
			//contrôle sur les données saisi
			if(Intitulé.length()==0 || (Integer)Domaine ==null|| (Integer)Nombre_jours ==null|| (Integer)Annee ==null|| (Integer)mois ==null|| (Integer)Formateur ==null|| (Integer)Nombre_participants ==null) {
				return "Tous les champs sont obligatoires et doivent être remplis correctement !";
			}else if(!DomaineCrud.getDomaineById(cn, Domaine)) {
				return "Ce Domaine n'existe Pas!";
			}else if(!FormateurCrud.getFormateurById(cn, Formateur)) {
				return "Ce formateur n'existe Pas!";
			}else {
				pr = cn.prepareStatement("insert into formation (Intitulé,Domaine,Nombre_jours,Annee,mois,Formateur,Nombre_participants) values (?,?,?,?,?,?,?)");
				pr.setString(1,Intitulé.trim());
				pr.setInt(2, Domaine);
				pr.setInt(3, Nombre_jours);
				pr.setInt(4, Annee);
				pr.setInt(5, mois);
				pr.setInt(6, Formateur);
				pr.setInt(7, Nombre_participants);
				pr.executeUpdate();
				return "Le nouvel formation a été ajouté avec succés.";
			}
		}catch(SQLException e) {
			return "Opération échouée !";
		}
	}
	
	
	
	
	
public String modifyFormation(Connection cn,int id,String Intitulé,int Domaine,int Nombre_jours,int Annee, int mois,int Formateur,int Nombre_participants) {
		
		try {
			//contrôle sur les données saisi
			if(Intitulé.length()==0 || (Integer)Domaine ==null|| (Integer)Nombre_jours ==null|| (Integer)Annee ==null|| (Integer)mois ==null|| (Integer)Formateur ==null|| (Integer)Nombre_participants ==null) {
				return "Tous les champs sont obligatoires et doivent être remplis correctement !";
			}else if(!DomaineCrud.getDomaineById(cn, Domaine)) {
				return "Ce domaine n'existe Pas!";
			}else if(!FormateurCrud.getFormateurById(cn, Formateur)) {
				return "Ce formateur n'existe Pas!";
			}else if(!FormationCrud.getFormationById(cn, id)) {
				return "Cette formation n'existe Pas!";
			}else {
				pr = cn.prepareStatement("update formation set Intitulé=?,Domaine=?,Nombre_jours=?,Annee=?,mois=?,Formateur=?,Nombre_participants=? where Code_formation = ?");
				pr.setString(1,Intitulé.trim());
				pr.setInt(2, Domaine);
				pr.setInt(3, Nombre_jours);
				pr.setInt(4, Annee);
				pr.setInt(5, mois);
				pr.setInt(6, Formateur);
				pr.setInt(7, Nombre_participants);
				pr.setInt(8,id);
				pr.executeUpdate();
				return "La formation a été modifié avec succés.";
			}
			
		}catch(SQLException e) {
			return "Opération échouée !";
		}
		
	}
	
	
	
	
	
public String deleteFormation(Connection cn,int id) {
	// supprimer formation 
	if(getFormationById(cn, id)) {
	try {
		pr = cn.prepareStatement("delete from formation where Code_formation = ?");
		pr.setInt(1, id);
		pr.executeUpdate();
		return "La formation a été supprimé.";
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		return "Opération échouée !";
	}
	}else {
		return "Il ny a pas du formation avec ce ID!";
	}
	
}
	
	
	
	
public  ArrayList<FormationModel> getAllFormations(Connection cn) {
	ArrayList<FormationModel> resultArray = new ArrayList<FormationModel>();
	try {
		pr = cn.prepareStatement("select Code_formation,Intitulé,Domaine,Nombre_jours,Annee,mois,Formateur,Nombre_participants from formation order by Intitulé asc");
		ResultSet rs = pr.executeQuery();
		while(rs.next()) {
			FormateurModel formateur = FormateurCrud.getSignleFormateurById(cn, rs.getInt(7));
			String formateurName = formateur.getNom()+" "+formateur.getPrenom();
			
			String domaine=Character.toUpperCase(DomaineCrud.getDomaineLibelleById(cn,rs.getInt(3)).charAt(0)) + DomaineCrud.getDomaineLibelleById(cn,rs.getInt(3)).substring(1);
			
			resultArray.add(new FormationModel(rs.getInt(1),rs.getString(2),domaine,rs.getInt(4),rs.getInt(5),rs.getInt(6),formateurName,rs.getInt(8)) );
		}
		
		return resultArray;
		
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Opération échouée !");
			return resultArray;
			
		}
	}
	
	
	
	
	
	
	
public static boolean getFormationById(Connection cn,int id) {
	// verifier si le formation est deja existe
	try {
		pr = cn.prepareStatement("select Code_formation from formation where Code_formation=? ");
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



public static FormationModel getSignleFormationById(Connection cn,int id) {
	// verifier si le formation est deja existe
	try {
		pr = cn.prepareStatement("select Code_formation,Intitulé,Domaine,Nombre_jours,Annee,mois,Formateur,Nombre_participants from formation where Code_formation=? ");
		pr.setInt(1, id);
		ResultSet rs = pr.executeQuery();
		
		boolean existe = rs.next();
		if(existe) {
			FormateurModel formateur = FormateurCrud.getSignleFormateurById(cn, rs.getInt(7));
			String formateurName = formateur.getNom()+" "+formateur.getPrenom();
			
			String domaine=Character.toUpperCase(DomaineCrud.getDomaineLibelleById(cn,rs.getInt(3)).charAt(0)) + DomaineCrud.getDomaineLibelleById(cn,rs.getInt(3)).substring(1);
			
			return new FormationModel(rs.getInt(1),rs.getString(2),domaine,rs.getInt(4),rs.getInt(5),rs.getInt(6),formateurName,rs.getInt(8));
		}
		
		return null;
		
		}catch(SQLException e) {
			// System.out.println(e.getMessage());
			return null;
		}
	}
	

}
