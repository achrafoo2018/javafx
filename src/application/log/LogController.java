package application.log;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.DataBase;
import application.UtilisateurCrud;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LogController implements Initializable {

	
	private DataBase db;
	private Connection cnx;
	private URL filePath;
	public static String role ="user";
	
	 @FXML
    private VBox vbox;
    @FXML
	private Label loginError;
    @FXML
	private Label loginError2;
    
    // signIn elements
    @FXML
	private TextField signInName;
    @FXML
	private PasswordField signInPassword;
    @FXML
	private Button signInButton;

    // signUp elements
    @FXML
	private Button createButton;
    @FXML
	private TextField signUpLogin;
    @FXML
	private PasswordField signUpPassword;
    @FXML
	private RadioButton roleAdmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	// constructeur 
    	db = new DataBase();
		cnx = db.connecterBase();
		filePath=url;
		
    	
    	// animation
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 21);
        t.play();
        
    }
    
    @FXML
    private void open_signin(ActionEvent event){
    	// transition (slide)
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 21);
        t.play();
        t.setOnFinished((e) ->{
        	loginError.setVisible(false);
        	loginError2.setVisible(false);
        });
    }   
    @FXML
    private void open_signup(ActionEvent event){
    	// transition inverse (slide back)
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) ->{
        	loginError.setVisible(false);
        	loginError2.setVisible(false);
        });
    } 
    @FXML
    private void exit(ActionEvent event){
    	// fermer le programme
    	System. exit(0);
    }
	
    
    @FXML
    private void signIn(ActionEvent event){
    	
    	loginError.setVisible(true);
    	loginError.setStyle("-fx-background-color:#f93154;");
    	UtilisateurCrud user = new UtilisateurCrud();
    	// collecter les donner saisit par l'utilisateur
    	String login=signInName.getText().trim();
    	String password=signInPassword.getText().trim();
		
		
		// verifier si le login et le mot de passe sont correct
			String result = user.signIn(cnx,login,password);
			// verifier si les donner sont correct
			if(result.contains("Role")) {
				role =result.contains("Administrateur") ? "admin" : "user";
				try {
					//redirection to another stage
					URL myURL = new URL(filePath.toString().replace("/log/LogScreen.fxml","/dashboard/DashboardScreen.fxml"));
					Parent root = FXMLLoader.load(myURL);
					Scene scene = new Scene(root);
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.centerOnScreen();
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				loginError.setText(result);
				
			}
			
		//}
		
		
		
		
		
    }
    

    
    public static boolean containsNumber(String st) {
    	//verifier s'il existe un nombre dans la chaine passer on parametre
    	char[] chars = st.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
           if(Character.isDigit(c)){
              sb.append(c);
           }
        }
        
        return !sb.isEmpty();
        
    }
    
    public static boolean isNumber(String st) {
    	//verifier si tous les chiffre du st sont des chiffre
    	char[] chars = st.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
           if(Character.isDigit(c)){
              sb.append(c);
           }
        }
        return sb.length()==st.length();
        
    }
    
    public static boolean isEmail(String email) {
    	Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        return mat.matches();
    }
    
    // signIn handler
    @FXML
    private void loginButtonActivation(KeyEvent event){
    	// activer/disactiver le button de login
    	String login=signInName.getText().trim();
    	String password=signInPassword.getText().trim();
    	if(login.length()!=0 && password.length()!=0) {
    		signInButton.setDisable(false);
    	}else {
    		signInButton.setDisable(true);
    	}
    }
    
    // signUp handler
    
    @FXML
    private void SignUpButtonActivation(KeyEvent event){
    	// activer/disactiver le button de SignUp
    	String login=signUpLogin.getText().trim();
    	String password=signUpPassword.getText().trim();
    	if(login.length()!=0 && password.length()!=0) {
    		createButton.setDisable(false);
    	}else {
    		createButton.setDisable(true);
    	}
    }
    
    
    
    @FXML
    private void signUp(ActionEvent event){
    	loginError2.setVisible(true);
    	loginError2.setStyle("-fx-background-color:#f93154;");
    	UtilisateurCrud user = new UtilisateurCrud();
    	// collecter les donner saisit par l'utilisateur
    	String login=signUpLogin.getText().trim();
    	String password=signUpPassword.getText().trim();
		
		
		// verifier les donner saisit par l'utilisateur
		if(containsNumber(login) || containsNumber(password)) {
			loginError2.setText("Login et Password néacceptent que des chaines de caractére!");
		}else {
			
			
			role =roleAdmin.isSelected()?"admin":"user";
			String result = user.signUp(cnx,login,password,roleAdmin.isSelected()?1:0);
			// verifier si l'utilisateur a été ajouté
			if(result.contains("L'utilisateur a été ajouté avec succés.")) {

				try {
					//redirection to another stage
					URL myURL = new URL(filePath.toString().replace("/log/LogScreen.fxml","/dashboard/DashboardScreen.fxml"));
					Parent root = FXMLLoader.load(myURL);
					Scene scene = new Scene(root);
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.centerOnScreen();
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				loginError2.setText(result);
				
			}
			
			
		}
			
    }
    
    
    
}
