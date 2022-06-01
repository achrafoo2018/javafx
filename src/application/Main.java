package application;
	

import java.io.IOException;
import java.sql.Connection;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class Main extends Application {
	
	private Task copyWorker;
	private Text LoadingLabel;
	private Parent root;
	private Scene scene;
	private Stage stage;
	private DataBase db;
	private Connection cnx;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			   root = FXMLLoader.load(getClass().getResource("LoadingScreen.fxml"));
			   scene = new Scene(root);
			   stage=primaryStage;
			   primaryStage.initStyle(StageStyle.UNDECORATED);
			   primaryStage.centerOnScreen();
			   primaryStage.setResizable(false);
			   primaryStage.setScene(scene);
			   primaryStage.show();
			   
			   LoadingLabel = (Text) scene.lookup("#LoadingLabel");
			   
			   copyWorker = createWorker(primaryStage);
			                  
               new Thread(copyWorker).start();
               PauseTransition delay = new PauseTransition(Duration.seconds(3));
               delay.setOnFinished( event -> {
            	   stage.close();
            	   
            	   try {
					root = FXMLLoader.load(getClass().getResource("log/LogScreen.fxml"));
					scene = new Scene(root);
					stage.setScene(scene);
	            	stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			   
               } );
               delay.play();
               
               
               
               
               
		} catch(Exception e) {
			   e.printStackTrace();
			  }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public Task createWorker(Stage myStage) {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(20);
                    if(i==10){
		                LoadingLabel.setText("Turning On Modules...");
		            }
		            
		            if(i==20){
		            	LoadingLabel.setText("Loading Modules...");
		            }
		            
		            if(i==50){
		            	LoadingLabel.setText("Connecting to Database...");
		            	db = new DataBase();
		        		cnx = db.connecterBase();
		            }
		            
		            if(i==70){
		            	LoadingLabel.setText("Connecting Successful...");
		            }
		            
		            if(i==80){
		            	LoadingLabel.setText("Launching Application...");
		            }
		            
                    updateProgress(i,100);

                }
                return true;
            }
        };
    }
	
	
}
