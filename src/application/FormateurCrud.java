package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Models.FormateurModel;

public class FormateurCrud {

	static Scanner sc = new Scanner(System.in);
	static PreparedStatement pr =null;
	
	public String createFormateur(Connection cn,String Nom,String Prenom,int Domaine,String Email,int Num_telephone) {
		// saisir les information du nouvelle Formateur

		try {
			//contréle sur les données saisi
			if(Nom.length()==0 ||Prenom.length()==0 ||(Integer)Domaine ==null ||Email.length()==0 || (Integer)Num_telephone ==null) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else if(!DomaineCrud.getDomaineById(cn, Domaine)) {
				return "Ce Domaine n'existe Pas!";
			}else {
				pr = cn.prepareStatement("insert into Formateur (Nom,Prenom,Domaine,Email,Num_telephone) values (?,?,?,?,?)");
				pr.setString(1, Nom.trim());
				pr.setString(2, Prenom.trim());
				pr.setInt(3,Domaine);
				pr.setString(4, Email.trim());
				pr.setInt(5,Num_telephone);
				pr.executeUpdate();
				return "Le nouvel formateur a été ajouté avec succés.";
			}
		}catch(SQLException e) {
			return "Opération échouée !";
		}
	}
	
	
public String modifyFormateur(Connection cn,int id,String Nom,String Prenom,int Domaine,String Email,int Num_telephone) {
		
		try {
			//contréle sur les données saisi
			if(Nom.length()==0 ||Prenom.length()==0 ||(Integer)Domaine ==null ||Email.length()==0 || (Integer)Num_telephone ==null) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else if(!DomaineCrud.getDomaineById(cn, Domaine)) {
				return "Ce Domaine n'existe Pas!";
			}else if(!getFormateurById(cn, id)) {
				return "Ce Formateur n'existe Pas!";
			}else {
				pr = cn.prepareStatement("update Formateur set Nom=?,Prenom=?,Domaine=?,Email=?,Num_telephone=? where Code_formateur = ?");
				pr.setString(1, Nom.trim());
				pr.setString(2, Prenom.trim());
				pr.setInt(3,Domaine);
				pr.setString(4, Email.trim());
				pr.setInt(5,Num_telephone);
				pr.setInt(6,id);
				pr.executeUpdate();
				return "Le Formateur a été modifié avec succés.";
			}
			
		}catch(SQLException e) {
			return "Opération échouée !";
		}
		
	}
	
	
	
public String deleteFormateur(Connection cn,int id) {
	// supprimer Formateur 
	if(getFormateurById(cn, id)) {
	try {
		pr = cn.prepareStatement("delete from Formateur where Code_formateur = ?");
		pr.setInt(1, id);
		pr.executeUpdate();
		return "Le formateur a été supprimé.";
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		return "Opération échouée !";
	}
	}else {
		return "Il ny a pas de formateur avec ce ID!";
	}
}
	
	
	public  ArrayList<FormateurModel> getAllFormateurs(Connection cn) {
		ArrayList<FormateurModel> resultArray = new ArrayList<FormateurModel>();
		try {
			pr = cn.prepareStatement("select Code_formateur,Nom,Prenom,Domaine,Email,Num_telephone from Formateur order by Nom asc");
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				String domaine=Character.toUpperCase(DomaineCrud.getDomaineLibelleById(cn,rs.getInt(4)).charAt(0)) + DomaineCrud.getDomaineLibelleById(cn,rs.getInt(4)).substring(1);
				
				resultArray.add(new FormateurModel(rs.getInt(1),rs.getString(2),rs.getString(3),domaine,rs.getString(5),rs.getInt(6)) );
			}
			
			return resultArray;
			
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				System.out.println("Opération échouée !");
				return resultArray;
				
			}
		}
	
	public static FormateurModel getSignleFormateurById(Connection cn,int id) {
		// verifier si le Formateur est deja existe
		try {
			pr = cn.prepareStatement("select Nom,Prenom,Domaine,Email,Num_telephone from Formateur where Code_formateur=? ");
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			
			boolean existe = rs.next();
			if(existe) {
				String domaine =DomaineCrud.getDomaineLibelleById(cn, rs.getInt(3));
				
				return new FormateurModel(id, rs.getString(1), rs.getString(2), domaine, rs.getString(4), rs.getInt(5));
			}
			
			return null;
			
			}catch(SQLException e) {
				// System.out.println(e.getMessage());
				return null;
			}
		}
	
	
	
	
	public static boolean getFormateurById(Connection cn,int id) {
		// verifier si le Formateur est deja existe
		try {
			pr = cn.prepareStatement("select Code_formateur from Formateur where Code_formateur=? ");
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
	
	
	
	public static int getFormateurIdByName(Connection cn,String name) {
		// verifier si le formateur est deja existe
		try {
			pr = cn.prepareStatement("select Code_formateur from Formateur where CONCAT(Nom,' ',Prenom) =? ");
			pr.setString(1, name);
			ResultSet rs = pr.executeQuery();
			
			boolean existe = rs.next();
			if(existe) {
				return rs.getInt(1);
			}
			
			return 0;
			
			}catch(SQLException e) {
				// System.out.println(e.getMessage());
				return -1;
			}
		}
	

}
