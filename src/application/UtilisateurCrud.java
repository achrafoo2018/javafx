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
	
	public String signUp(Connection cn,String login,String password,int role) {

		
		try {
			//contréle sur les données saisi
			if(login.length()==0 || password.length()==0 ||role > 2 || role <0) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else {
				if(!getUserByLog(cn,login)) {
				pr = cn.prepareStatement("insert into Utilisateur (Login,Password,Role)values(?,?,?)");
				pr.setString(1, login.trim());
				pr.setString(2, password.trim());
				pr.setString(3, role==1?"Administrateur":"Utilisateur");
				pr.executeUpdate();
				return "L'utilisateur a été ajouté avec succés.";
			
			}else {
				return "L'utilisateur a déja existe!";
			}
			}
			
			
		}catch(SQLException e) {
			return "Opération échouée !";
		}
	}
	
	
	public String signIn(Connection cn,String login,String password) {
		// créer un nouvelle utilisateur
		try {
			pr = cn.prepareStatement("select Role from Utilisateur where Login=? and Password=?");
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
	
	
	public String modifyUser(Connection cn,int id,String login,String password) {
		
		try {
			//contréle sur les données saisi
			if(login.length()==0 || password.length()==0) {
				return "Tous les champs sont obligatoires et doivent étre remplis correctement !";
			}else {
				pr = cn.prepareStatement("update Utilisateur set Login=?,Password=? where Code_utilisateur = ?");
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
	
	
	
	
	public boolean getUserByLog(Connection cn,String login) {
		//  verifier si l'utilisateur est deja existe 
		try {
			pr = cn.prepareStatement("select Login from Utilisateur where Login=? ");
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
	
	
	
	public ArrayList<UserModel> getAllUsers(Connection cn) {
		ArrayList<UserModel> resultArray = new ArrayList<UserModel>();
		try {
			pr = cn.prepareStatement("select Code_utilisateur,Login,Password from utilisateur where Role='Utilisateur' ");
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				resultArray.add(new UserModel(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			
			return resultArray;
			
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				System.out.println("Opération échouée !");
				return resultArray;
				
			}
		}
	
	public String deleteUser(Connection cn,String login) {
		if(this.getUserByLog(cn, login)) {
		try {
			pr = cn.prepareStatement("delete from utilisateur where Login = ?");
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
