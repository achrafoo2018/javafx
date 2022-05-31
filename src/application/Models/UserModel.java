package application.Models;

public class UserModel {

	public int ID;
	public String Login; 
	public String Password;
	public UserModel(int id, String login, String password) {
		super();
		this.ID = id;
		this.Login = login;
		this.Password = password;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

}
