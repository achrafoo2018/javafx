package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

	public static Connection connecterBase() {
		Connection con=null;
		String url="jdbc:mysql://localhost:3306/gestion_formation";
		String user="root";
		String password="toor";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,password);
			System.out.println("connection etablie.");
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("error de connexion! "+e.getMessage());
			System. exit(0);
		}
		return con;
		
		
		
	}

}
