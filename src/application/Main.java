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
	private Text LoadingPercent;
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
			   LoadingPercent = (Text) scene.lookup("#LoadingPercent");
			   ProgressBar progressBar = (ProgressBar) scene.lookup("#LoadingBar");
			   
			   copyWorker = createWorker(primaryStage);
			   
			   progressBar.progressProperty().unbind();
               progressBar.progressProperty().bind(copyWorker.progressProperty());
               copyWorker.messageProperty().addListener(new ChangeListener<String>() {
                   public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                       System.out.println(newValue);

                   }
               });
               
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
		            
		            LoadingPercent.setText(i+" %");
                    updateProgress(i,100);

                }
                return true;
            }
        };
    }
	
	
}
