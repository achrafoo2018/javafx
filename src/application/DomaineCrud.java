package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Models.DomaineModel;


public class DomaineCrud {

	static Scanner sc = new Scanner(System.in);
	static PreparedStatement pr =null;
	
	
	
	public String createDomaine(Connection cn,String libelle) {
		// saisir les information du nouvelle domaine

		try {
			//contréle sur les données saisi
			if(libelle.length()==0) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else {
				pr = cn.prepareStatement("insert into domaine (libelle) values (?)");
				pr.setString(1, libelle.trim().toLowerCase());
				pr.executeUpdate();
				return "Le domaine a été ajouté avec succés.";
			}
		}catch(SQLException e) {
			return "Opération échouée !";
		}
	}
	
	
	
public String modifyDomaine(Connection cn,int id,String libelle) {
		
		try {
			//contréle sur les données saisi
			if(libelle.length()==0 ) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else {
				pr = cn.prepareStatement("update domaine set libelle=? where Code_domaine = ?");
				pr.setString(1, libelle.trim());
				pr.setInt(2,id);
				pr.executeUpdate();
				return "Le domaine a été modifié avec succés.";
			}
			
		}catch(SQLException e) {
			return "Opération échouée !";
		}
		
	}
	
	
	
	public String deleteDomaine(Connection cn,String libelle) {
		// supprimer domaine 
		if(this.getDomaineByLibelle(cn, libelle)) {
		try {
			pr = cn.prepareStatement("delete from domaine where libelle = ?");
			pr.setString(1, libelle);
			pr.executeUpdate();
			return "Le domaine a été supprimé.";
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return "Opération échouée !";
		}
		}else {
			return "Il ny a pas du domaine avec ce libelle!";
		}
		
	}
	
	public boolean getDomaineByLibelle(Connection cn,String libelle) {
		// verifier si le domaine est deja existe
		try {
			pr = cn.prepareStatement("select libelle from domaine where libelle=? ");
			pr.setString(1, libelle.trim());
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
	
	public  ArrayList<DomaineModel> getAllDomaines(Connection cn) {
		ArrayList<DomaineModel> resultArray = new ArrayList<DomaineModel>();
		try {
			pr = cn.prepareStatement("select Code_domaine,libelle from domaine order by libelle asc");
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				resultArray.add(new DomaineModel(rs.getInt(1),Character.toUpperCase(rs.getString(2).charAt(0)) + rs.getString(2).substring(1)));
			}
			
			return resultArray;
			
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				System.out.println("Opération échouée !");
				return resultArray;
				
			}
		}
	
	public static boolean getDomaineById(Connection cn,int id) {
		// verifier si le domaine est deja existe
		try {
			pr = cn.prepareStatement("select libelle from domaine where Code_domaine=? ");
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
	
	public static String getDomaineLibelleById(Connection cn,int id) {
		// verifier si le domaine est deja existe
		try {
			pr = cn.prepareStatement("select libelle from domaine where Code_domaine=? ");
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			
			boolean existe = rs.next();
			if(existe) {
				return rs.getString(1);
			}
			
			return "n'existe pas";
			
			}catch(SQLException e) {
				// System.out.println(e.getMessage());
				return "erreur";
			}
		}
	
	
	public static int getDomaineIdByLibelle(Connection cn,String libelle) {
		// verifier si le domaine est deja existe
		try {
			pr = cn.prepareStatement("select Code_domaine from domaine where libelle=? ");
			pr.setString(1, libelle);
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
