package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Models.UserModel;

public class UtilisateurCrud {

	Scanner sc = new Scanner(System.in);
	PreparedStatement pr =null;
	
	public String signUp(Connection DBConnection,String login,String password,int role, String name) {

		
		try {
			//contréle sur les données saisi
			if(login.length()==0 || password.length()==0 ||role > 2 || role <0 || name.length()==0){
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else {
				if(!getUserByLog(DBConnection,login)) {
					pr = DBConnection.prepareStatement("insert into utilisateur (Login, Name,Password,Role)values(?,?,?,?)");
					pr.setString(1, login.trim());
					pr.setString(2, name.trim());
					pr.setString(3, password.trim());
					pr.setString(4, role==1?"Administrateur":"utilisateur");
					pr.executeUpdate();
					return "L'utilisateur a été ajouté avec succés.";

				}else {
					return "L'utilisateur a déja existe!";
				}
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return "Opération échouée !";
		}
	}
	
	
	public String signIn(Connection DBConnection,String login,String password) {
		// créer un nouvelle utilisateur
		try {
			pr = DBConnection.prepareStatement("select Role from utilisateur where Login=? and Password=?");
			pr.setString(1, login.trim());
			pr.setString(2, password.trim());
			ResultSet rs = pr.executeQuery();
			
			boolean existe = rs.next();
			if(existe) {
				return"Role: "+rs.getString(1).trim();
			}else {
				return "Identifiant ou mot de passe incorrect!";
			}
			}catch(SQLException e) {
				// System.out.println(e.getMessage());
				return "Opération échouée!";
			}
		}
	
	
	public String modifyUser(Connection DBConnection,int id,String login,String password) {
		
		try {
			//contréle sur les données saisi
			if(login.length()==0 || password.length()==0) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else {
				pr = DBConnection.prepareStatement("update utilisateur set Login=?,Password=? where Code_utilisateur = ?");
				pr.setString(1, login.trim());
				pr.setString(2, password.trim());
				pr.setInt(3,id);
				pr.executeUpdate();
				return "L'utilisateur a été Modifié avec succés.";
			}
			
		}catch(SQLException e) {
			return "Opération échouée !";
		}
		
	}
	
	
	
	
	public boolean getUserByLog(Connection DBConnection,String login) {
		//  verifier si l'utilisateur est deja existe 
		try {
			pr = DBConnection.prepareStatement("select Login from utilisateur where Login=? ");
			pr.setString(1, login.trim());
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
	
	public String getUserNameByLogin(Connection DBConnection, String login) {
		//  get user name
		try {
			pr = DBConnection.prepareStatement("select name from utilisateur where Login=? ");
			pr.setString(1, login.trim());
			ResultSet rs = pr.executeQuery();
			if(rs.next()) {
				return rs.getString(1).trim();
			}
			
			return "Utilisateur";
			
			}catch(SQLException e) {
				// System.out.println(e.getMessage());
				return "Utilisateur";
			}
		}
	
	public ArrayList<UserModel> getAllUsers(Connection DBConnection) {
		ArrayList<UserModel> resultArray = new ArrayList<UserModel>();
		try {
			pr = DBConnection.prepareStatement("select Code_utilisateur,Login,Password, Name from utilisateur where Role='Utilisateur' ");
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				resultArray.add(new UserModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
			return resultArray;
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Opération échouée !");
			return resultArray;
			
		}
	}

	
	public String deleteUser(Connection DBConnection,String login) {
		if(this.getUserByLog(DBConnection, login)) {
		try {
			pr = DBConnection.prepareStatement("delete from utilisateur where Login = ?");
			pr.setString(1, login);
			pr.executeUpdate();
			return "L'utilisateur a été supprimé.";
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return "Opération échouée !";
		}
		}else {
			return "Il ny a pas d'utilisateur avec ce login";
		}
		
	}
	
	

}
