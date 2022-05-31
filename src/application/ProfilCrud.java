package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Models.ProfilModel;
import application.Models.UserModel;

public class ProfilCrud {

	Scanner sc = new Scanner(System.in);
	static PreparedStatement pr =null;
	
	
	
	public String createProfil(Connection cn,String libelle) {
		// saisir les information du nouvelle profil

		try {
			//contréle sur les données saisi
			if(libelle.length()==0) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else {
				pr = cn.prepareStatement("insert into Profil (libelle) values (?)");
				pr.setString(1, libelle.trim().toLowerCase());
				pr.executeUpdate();
				return "Le Profil a été ajouté avec succés.";
			}
		}catch(SQLException e) {
			return "Opération échouée !";
		}
	}
	
	
	
public String modifyProfil(Connection cn,int id,String libelle) {
		
		try {
			//contréle sur les données saisi
			if(libelle.length()==0 ) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else {
				pr = cn.prepareStatement("update Profil set libelle=? where Code_profil = ?");
				pr.setString(1, libelle.trim());
				pr.setInt(2,id);
				pr.executeUpdate();
				return "Le profil a été Modifié avec succés.";
			}
			
		}catch(SQLException e) {
			return "Opération échouée !";
		}
		
	}
	
	
	
	public String deleteProfil(Connection cn,String libelle) {
		// supprimer profil 
		if(this.getProfilByLibelle(cn, libelle)) {
		try {
			pr = cn.prepareStatement("delete from Profil where libelle = ?");
			pr.setString(1, libelle);
			pr.executeUpdate();
			return "Le profil a été supprimé.";
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return "Opération échouée !";
		}
		}else {
			return "Il ny a pas du profil avec ce libelle!";
		}
		
	}
	
	public boolean getProfilByLibelle(Connection cn,String libelle) {
		// verifier si le profil est deja existe
		try {
			pr = cn.prepareStatement("select libelle from Profil where libelle=? ");
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
	
	public  ArrayList<ProfilModel> getAllProfils(Connection cn) {
		ArrayList<ProfilModel> resultArray = new ArrayList<ProfilModel>();
		try {
			pr = cn.prepareStatement("select Code_profil,libelle from Profil order by libelle asc");
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				resultArray.add(new ProfilModel(rs.getInt(1),Character.toUpperCase(rs.getString(2).charAt(0)) + rs.getString(2).substring(1)));
			}
			
			return resultArray;
			
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				System.out.println("Opération échouée !");
				return resultArray;
				
			}
		}
	
	
	
	public static boolean getProfilById(Connection cn,int id) {
		// verifier si le profil est deja existe
		try {
			pr = cn.prepareStatement("select libelle from Profil where Code_profil=? ");
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
	
	
	public static String getProfilLibelleById(Connection cn,int id) {
		// verifier si le profil est deja existe
		try {
			pr = cn.prepareStatement("select libelle from Profil where Code_profil=? ");
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
	
	
	public static int getParticipantById(Connection cn,String libelle) {
		// verifier si le profil est deja existe
		try {
			pr = cn.prepareStatement("select Code_profil from Profil where libelle=? ");
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
