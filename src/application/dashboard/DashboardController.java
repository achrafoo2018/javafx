package application.dashboard;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import application.*;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;

import application.Models.DomaineModel;
import application.Models.FormateurModel;
import application.Models.FormationModel;
import application.Models.ParticipantModel;
import application.Models.ProfilModel;
import application.Models.UserModel;
import application.log.LogController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DashboardController implements Initializable  {

	// database instance
	private DataBase db;
	private Connection DBconnection;
	private UtilisateurCrud userCRUD;
	private ProfilCrud profilCRUD;
	private DomaineCrud domaineCRUD;
	private FormateurCrud formateurCRUD;
	private FormationCrud formationCRUD;
	private ParticipantCrud participantCRUD;

	// Name of current logged user
	public static String loggedUser;
	//menu
	@FXML
	private Button goToDomaines;

	@FXML
	private Button goToParticipant;

	@FXML
	private URL filePath;



	@FXML
	private Button goToFormations;

	@FXML
	private Button goToInstructeurs;

	@FXML
	private Button goToProfils;

	@FXML
	private Button goToUtilisateurs;

	//panes
	//menu

	@FXML
	private Pane profilsPane;
	@FXML
	private Pane userPane;
	@FXML
	private Pane domainesPane;
	@FXML
	private Pane formateurPane;
	@FXML
	private Pane formationPane;
	@FXML
	private Pane participantPane;


	@FXML
	private HBox UsersButtonHolder;
	@FXML
	private Pane menu;


	// users frame
	@FXML
	private Button createUserButton;
	@FXML
	private Button modifyUserButton;
	@FXML
	private Button deleteUserButton;

	@FXML
	private Label userCreationLabel;
	@FXML
	private Label windowTitle;
	@FXML
	private TextField newLogin;
	@FXML
	private TextField newName;
	@FXML
	private Text loggedUserName;
	@FXML
	private TextField newPassword;
	@FXML
	private TextField userDeleteLogin;

	@FXML
	private TextField userModifyLogin;

	@FXML
	private TextField userModifyPassword;

	@FXML
	private TableView<UserModel> usersTable;
	@FXML
	private TableColumn<UserModel,Integer> userIDColumn;
	@FXML
	private TableColumn<UserModel,String> userLoginColumn;
	@FXML
	private TableColumn<UserModel,String> userPasswordColumn;

	@FXML
	private ComboBox<Integer> userIdsCombo;


	// profile frame
	@FXML
	private Button createProfilButton;
	@FXML
	private Button modifyProfilButton;
	@FXML
	private Button deleteProfilButton;

	@FXML
	private TableView<ProfilModel> profilTable;
	@FXML
	private TableColumn<ProfilModel,Integer> profilIDColumn;
	@FXML
	private TableColumn<ProfilModel,String> profilLibelleColumn;
	@FXML
	private ComboBox<Integer> profilIdsCombo;
	@FXML
	private Label profilMessageLabel;
	@FXML
	private TextField newProfil;
	@FXML
	private TextField profilModifyLibelle;
	@FXML
	private TextField profilDeleteLogin;


	// Domaine frame
	@FXML
	private Button createDomaineButton;
	@FXML
	private Button modifyDomaineButton;
	@FXML
	private Button deleteDomaineButton;

	@FXML
	private TableView<DomaineModel> domaineTable;
	@FXML
	private TableColumn<DomaineModel,Integer> domaineIDColumn;
	@FXML
	private TableColumn<DomaineModel,String> domaineLibelleColumn;
	@FXML
	private ComboBox<Integer> domaineIdsCombo;
	@FXML
	private Label domaineMessageLabel;

	//create TextFields
	@FXML
	private TextField newDomaine;
	@FXML
	private TextField domaineModifyLibelle;
	@FXML
	private TextField domaineDeleteLogin;

	// modify TextFields
	@FXML
	private TextField modifierFormateurNom;
	@FXML
	private TextField modifierFormateurPrenom;
	@FXML
	private TextField modifierFormateurEmail;
	@FXML
	private TextField modifierFormateurTelephone;




	// Formateur frame
	@FXML
	private Button createFormateurButton;
	@FXML
	private Button deleteFormateurButton;
	@FXML
	private Button modifyFormateurButton;


	@FXML
	private TableView<FormateurModel> formateurTable;
	@FXML
	private TableColumn<FormateurModel,Integer> formateurIDColumn;
	@FXML
	private TableColumn<FormateurModel,String> formateurNomColumn;
	@FXML
	private TableColumn<FormateurModel,String> formateurPrenomColumn;
	@FXML
	private TableColumn<FormateurModel,String> formateurDomaineColumn;
	@FXML
	private TableColumn<FormateurModel,String> formateurEmailColumn;
	@FXML
	private TableColumn<FormateurModel,Integer> formateurTelephoneColumn;


	@FXML
	private ComboBox<String> newFormateurDomaineCombo;
	@FXML
	private ComboBox<Integer> modifierFormateurIdCombo;
	@FXML
	private ComboBox<String> modifierFormateurDomaineCombo;
	@FXML
	private ComboBox<Integer> deleteFormateurIdCombo;
	@FXML
	private TextField newFormateurNom;
	@FXML
	private TextField newFormateurPrenom;
	@FXML
	private TextField newFormateurEmail;
	@FXML
	private TextField newFormateurTelephone;
	@FXML
	private Label formateurMessageLabel;

	// Formation frame
	@FXML
	private Button createFormationButton;
	@FXML
	private Button modifyFormationButton;
	@FXML
	private Button deleteFormationButton;


	@FXML
	private TableView<FormationModel> formationTable;
	@FXML
	private TableColumn<FormationModel,Integer> formationIDColumn;
	@FXML
	private TableColumn<FormationModel,String> formationIntituleColumn;
	@FXML
	private TableColumn<FormationModel,String> formationDomaineColumn;
	@FXML
	private TableColumn<FormationModel,Integer> formationJoursColumn;
	@FXML
	private TableColumn<FormationModel,Integer> formationMoisColumn;
	@FXML
	private TableColumn<FormationModel,Integer> formationAnneeColumn;
	@FXML
	private TableColumn<FormationModel,String> formationFormateurColumn;
	@FXML
	private TableColumn<FormationModel,Integer> formationParticipantsColumn;



	@FXML
	private ComboBox<String> newFormationDomaineCombo;
	@FXML
	private ComboBox<String> newFormationFormateurCombo;

	@FXML
	private ComboBox<Integer> modifierFormationIDCombo;
	@FXML
	private ComboBox<String> modifierFormationDomaineCombo;
	@FXML
	private ComboBox<String> modifierFormationFormateurCombo;

	@FXML
	private ComboBox<Integer> deleteFormationIdCombo;
	@FXML
	private TextField newFormationIntitule;
	@FXML
	private TextField newFormationJours;
	@FXML
	private TextField newFormationMois;
	@FXML
	private TextField newFormationAnnee;
	@FXML
	private TextField newFormationParticipants;


	@FXML
	private TextField modifierParticipantNom;
	@FXML
	private TextField modifierParticipantPrenom;

	@FXML
	private TextField modifierFormationIntitule;
	@FXML
	private TextField modifierFormationJours;
	@FXML
	private TextField modifierFormationMois;
	@FXML
	private TextField modifierFormationAnnee;
	@FXML
	private TextField modifierFormationParticipants;
	@FXML
	private Label formationMessageLabel;




	// Participant frame
	/*

		@FXML
		private Button modifyFormateurButton;
	*/
	@FXML
	private Button createParticipantButton;

	@FXML
	private Button deleteParticipantButton;

	@FXML
	private Button modifyParticipantButton;


	@FXML
	private TableView<ParticipantModel> participantTable;
	@FXML
	private TableColumn<ParticipantModel,String> participantMatriculeColumn;
	@FXML
	private TableColumn<ParticipantModel,String> participantNomColumn;
	@FXML
	private TableColumn<ParticipantModel,String> participantPrenomColumn;
	@FXML
	private TableColumn<ParticipantModel,Date> participantDateColumn;
	@FXML
	private TableColumn<ParticipantModel,String> participantProfilColumn;

	@FXML
	private TextField newParticipantMatricule;
	@FXML
	private TextField newParticipantNom;
	@FXML
	private TextField newParticipantPrenom;
	@FXML
	private DatePicker newParticipantDate;
	@FXML
	private ComboBox<String> newParticipantProfilCombo;

	@FXML
	private ComboBox<String> modifierParticipantProfilCombo;
	@FXML
	private ComboBox<String> modifierParticipantIdCombo;
	@FXML
	private DatePicker modifierParticipantDate;

	@FXML
	private ComboBox<String> deleteParticipantIdCombo;
	@FXML
	private Label participantMessageLabel;








	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		db = new DataBase();
		DBconnection = db.connecterBase();
		filePath=arg0;

		// user Screen setups
		userCRUD = new UtilisateurCrud();
		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		userLoginColumn.setCellValueFactory(new PropertyValueFactory<>("Login"));
		userPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));


		// profil Screen setups
		profilCRUD = new ProfilCrud();
		profilIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		profilLibelleColumn.setCellValueFactory(new PropertyValueFactory<>("Libelle"));


		// Domaine Screen setups
		domaineCRUD = new DomaineCrud();
		domaineIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		domaineLibelleColumn.setCellValueFactory(new PropertyValueFactory<>("Libelle"));
		loadDomaines();
		loadDomainesIDS();

		// Formateur Screen setups
		formateurCRUD = new FormateurCrud();
		formateurIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		formateurNomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
		formateurPrenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
		formateurDomaineColumn.setCellValueFactory(new PropertyValueFactory<>("Domaine"));
		formateurEmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
		formateurTelephoneColumn.setCellValueFactory(new PropertyValueFactory<>("Num_telephone"));


		// Formation Screen setups
		formationCRUD = new FormationCrud();
		formationIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		formationIntituleColumn.setCellValueFactory(new PropertyValueFactory<>("Intitule"));
		formationDomaineColumn.setCellValueFactory(new PropertyValueFactory<>("Domaine"));
		formationJoursColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre_jours"));
		formationMoisColumn.setCellValueFactory(new PropertyValueFactory<>("mois"));
		formationAnneeColumn.setCellValueFactory(new PropertyValueFactory<>("Annee"));
		formationFormateurColumn.setCellValueFactory(new PropertyValueFactory<>("Formateur"));
		formationParticipantsColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre_participants"));

		// participant Screen setups
		participantCRUD = new ParticipantCrud();
		participantMatriculeColumn.setCellValueFactory(new PropertyValueFactory<>("Matricule_participant"));
		participantNomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
		participantPrenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
		participantProfilColumn.setCellValueFactory(new PropertyValueFactory<>("Profil"));
		participantDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date_naissance"));
		// datepicker configuration
		newParticipantDate.setConverter(converter);
		LocalDate minDate = LocalDate.of(1950, 1, 1);
		LocalDate maxDate = LocalDate.now();
		newParticipantDate.setDayCellFactory(d ->
				new DateCell() {
					@Override public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
					}});

		modifierParticipantDate.setConverter(converter);
		modifierParticipantDate.setDayCellFactory(d ->
				new DateCell() {
					@Override public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
					}});

		loadAll();

		// update menu on role
		if(LogController.role =="user") {
			menu.getChildren().remove(UsersButtonHolder);
			windowTitle.setText("Profils");
			profilsPane.toFront();
		}
	}

	@FXML
	private void exit(ActionEvent event){
		// fermer le programme
		System. exit(0);
	}

	// date converter
	StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
		DateTimeFormatter dateFormatter =
				DateTimeFormatter.ofPattern("dd-MM-yyyy");

		@Override
		public String toString(LocalDate date) {
			if (date != null) {
				return dateFormatter.format(date);
			} else {
				return "";
			}
		}
		@Override
		public LocalDate fromString(String string) {
			if (string != null && !string.isEmpty()) {
				return LocalDate.parse(string, dateFormatter);
			} else {
				return null;
			}
		}
	};





	private void loadAll() {
		loadDomaines();
		loadDomainesIDS();
		loadDomainesLibelles();
		loadFormateurs();
		loadFormateursIDS();
		loadFormateursNames();
		loadFormation();
		loadFormationsIDS();
		loadProfils();
		loadProfilsIDS();
		loadUsers();
		loadUsersIDS();
		loadParticipant();
		loadParticipantMatricule();
		loadProfilsLibelles();
		this.loggedUserName.setText(String.valueOf(this.getLoggedUser()));
	}




	@FXML
	private void changeWindow(ActionEvent event){
		// menu action holder
		if(event.getSource() == goToUtilisateurs ) {
			windowTitle.setText("Utilisateurs");
			userPane.toFront();
		}
		else if(event.getSource() == goToProfils ) {
			windowTitle.setText("Profils");
			profilsPane.toFront();
		}
		else if(event.getSource() == goToDomaines ) {
			windowTitle.setText("Domaines");
			domainesPane.toFront();
		}
		else if(event.getSource() == goToFormations ) {
			windowTitle.setText("Formations");
			formationPane.toFront();
		}
		else if(event.getSource() == goToInstructeurs ) {
			windowTitle.setText("Formateurs");
			formateurPane.toFront();
		}
		else if(event.getSource() == goToParticipant ){
			windowTitle.setText("Participants");
			participantPane.toFront();
		}
	}



	public void loadUsers() {
		// get all users
		ArrayList<UserModel> myUsers = userCRUD.getAllUsers(DBconnection);

		ObservableList<UserModel> userObservables = FXCollections.observableArrayList() ;
		usersTable.setItems(userObservables);

		for(int i=0;i<myUsers.size();i++) {
			userObservables.add(myUsers.get(i));
		}


	}

	public void loadUsersIDS() {
		// remplire comboBox
		ArrayList<UserModel> myUsers = userCRUD.getAllUsers(DBconnection);

		ObservableList<Integer> userIdsObservables = FXCollections.observableArrayList() ;
		userIdsCombo.setItems(userIdsObservables);

		for(int i=0;i<myUsers.size();i++) {
			userIdsObservables.add(myUsers.get(i).getID());

		}
		Collections.sort(userIdsObservables);

	}

	@FXML
	private void signUp(ActionEvent event){
		userCreationLabel.setVisible(true);
		userCreationLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String login=newLogin.getText().trim();
		String password=newPassword.getText().trim();
		String name=newName.getText().trim();

		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(login)) {
			userCreationLabel.setText("Login n'accepte que des chaines de caractére!");
		}else if(userCRUD.getUserByLog(DBconnection, login)){
			userCreationLabel.setText("Login est deja existe!");
		}else {


			String result = userCRUD.signUp(DBconnection,login,password,0, name);
			// verifier si l'utilisateur a été ajouté
			if(result.contains("L'utilisateur a été ajouté avec succés.")) {
				userCreationLabel.setText("L'utilisateur a été ajouté avec succés.");
				userCreationLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				userCreationLabel.setText(result);

			}


		}

	}

	@FXML
	private void ModifierUtilisateur(ActionEvent event){
		userCreationLabel.setVisible(true);
		userCreationLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String login=userModifyLogin.getText().trim();
		String password=userModifyPassword.getText().trim();


		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(login)) {
			userCreationLabel.setText("Login n'accepte que des chaines de caractére!");
		}else if(userIdsCombo.getValue()==null) {
			userCreationLabel.setText("Sélectionner l'identifiant du utilisateur é modifier!");
		}
		else if(userCRUD.getUserByLog(DBconnection, login)){
			userCreationLabel.setText("Login est deja existe!");
		}else {

			int id=userIdsCombo.getValue();
			String result = userCRUD.modifyUser(DBconnection,id,login,password);
			// verifier si l'utilisateur a été ajouté
			if(result.contains("L'utilisateur a été Modifié avec succés.")) {
				userCreationLabel.setText("L'utilisateur a été Modifié avec succés.");
				userCreationLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				userCreationLabel.setText(result);

			}


		}

	}

	@FXML
	private void deleteUser(ActionEvent event){
		userCreationLabel.setVisible(true);
		userCreationLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String login=userDeleteLogin.getText().trim();

		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(login)) {
			userCreationLabel.setText("Login n'accepte que des chaines de caractére!");
		}else {


			String result = userCRUD.deleteUser(DBconnection,login);
			// verifier si l'utilisateur a été ajouté
			if(result.contains("L'utilisateur a été supprimé.")) {
				userCreationLabel.setText("L'utilisateur a été supprimé.");
				userCreationLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				userCreationLabel.setText(result);

			}


		}

	}

	// create User button handler
	@FXML
	private void createUserButtonActivation(KeyEvent event){
		// activer/disactiver le button de login
		String login=newLogin.getText().trim();
		String password=newPassword.getText().trim();
		if(login.length()!=0 && password.length()!=0) {
			createUserButton.setDisable(false);
		}else {
			createUserButton.setDisable(true);
		}
	}

	// modify User button handler
	@FXML
	private void modifyUserButtonActivation(KeyEvent event){
		// activer/disactiver le button de login
		String login=userModifyLogin.getText().trim();
		String password=userModifyPassword.getText().trim();
		if(login.length()!=0 && password.length()!=0) {
			modifyUserButton.setDisable(false);
		}else {
			modifyUserButton.setDisable(true);
		}
	}

	// delete User button handler
	@FXML
	private void deleteUserButtonActivation(KeyEvent event){
		// activer/disactiver le button de login
		String login=userDeleteLogin.getText().trim();
		if(login.length()!=0 ) {
			deleteUserButton.setDisable(false);
		}else {
			deleteUserButton.setDisable(true);
		}
	}









	public void loadProfils() {

		// get all profiles
		ArrayList<ProfilModel> myProfils = profilCRUD.getAllProfils(DBconnection);

		ObservableList<ProfilModel> profilObservables = FXCollections.observableArrayList() ;
		profilTable.setItems(profilObservables);

		for(int i=0;i<myProfils.size();i++) {
			profilObservables.add(myProfils.get(i));
		}
	}





	public void loadProfilsIDS() {
		// remplire comboBox
		ArrayList<ProfilModel> myProfils = profilCRUD.getAllProfils(DBconnection);

		ObservableList<Integer> profilIdsObservables = FXCollections.observableArrayList() ;
		profilIdsCombo.setItems(profilIdsObservables);

		for(int i=0;i<myProfils.size();i++) {
			profilIdsObservables.add(myProfils.get(i).getID());

		}
		Collections.sort(profilIdsObservables);
	}

	@FXML
	private void createProfil(ActionEvent event){
		profilMessageLabel.setVisible(true);
		profilMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String libelle=newProfil.getText().trim().toLowerCase();

		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(libelle)) {
			profilMessageLabel.setText("Le Libelle du nouvel profil n'accepte que des chaines de caractére!");
		}else if(profilCRUD.getProfilByLibelle(DBconnection, libelle)){
			profilMessageLabel.setText("Le Profile est deja existe!");
		}else {


			String result = profilCRUD.createProfil(DBconnection,libelle);
			// verifier si le profil a été ajouté
			if(result.contains("Le Profil a été ajouté avec succés.")) {
				profilMessageLabel.setText("Le Profil a été ajouté avec succés.");
				profilMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				profilMessageLabel.setText(result);

			}


		}

	}

	@FXML
	private void ModifierProfil(ActionEvent event){
		profilMessageLabel.setVisible(true);
		profilMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String libelle=profilModifyLibelle.getText().trim().toLowerCase();

		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(libelle)) {
			profilMessageLabel.setText("Le Libelle n'accepte que des chaines de caractére!");
		}else if(profilIdsCombo.getValue()==null) {
			profilMessageLabel.setText("Sélectionner l'identifiant du profil é modifier!");
		}
		else if(profilCRUD.getProfilByLibelle(DBconnection, libelle)){
			profilMessageLabel.setText("Le libelle est deja existe!");
		}else {

			int id=profilIdsCombo.getValue();
			String result = profilCRUD.modifyProfil(DBconnection,id,libelle);
			// verifier si l'utilisateur a été ajouté
			if(result.contains("Le profil a été Modifié avec succés.")) {
				profilMessageLabel.setText("Le profil a été Modifié avec succés.");
				profilMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				profilMessageLabel.setText(result);

			}


		}

	}

	@FXML
	private void deleteProfil(ActionEvent event){
		profilMessageLabel.setVisible(true);
		profilMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String libelle=profilDeleteLogin.getText().trim().toLowerCase();

		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(libelle)) {
			profilMessageLabel.setText("Le Libelle n'accepte que des chaines de caractére!");
		}else {


			String result = profilCRUD.deleteProfil(DBconnection,libelle);
			// verifier si l'utilisateur a été ajouté
			if(result.contains("Le profil a été supprimé.")) {
				profilMessageLabel.setText("Le profil a été supprimé.");
				profilMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				profilMessageLabel.setText(result);

			}


		}

	}

	// create profil button handler
	@FXML
	private void createProfilButtonActivation(KeyEvent event){
		// activer/disactiver le button de crée profil
		String login=newProfil.getText().trim();
		if(login.length()!=0 ) {
			createProfilButton.setDisable(false);
		}else {
			createProfilButton.setDisable(true);
		}
	}

	// modify Profil button handler
	@FXML
	private void modifyProfilButtonActivation(KeyEvent event){
		// activer/disactiver le button de login
		String libelle=profilModifyLibelle.getText().trim();
		if(libelle.length()!=0) {
			modifyProfilButton.setDisable(false);
		}else {
			modifyProfilButton.setDisable(true);
		}
	}

	// delete Profil button handler
	@FXML
	private void deleteProfilButtonActivation(KeyEvent event){
		// activer/disactiver le button de login
		String libelle=profilDeleteLogin.getText().trim();
		if(libelle.length()!=0 ) {
			deleteProfilButton.setDisable(false);
		}else {
			deleteProfilButton.setDisable(true);
		}
	}







	// Domaine functions **********************************************************************

	public void loadDomaines() {
		// remplire le tableau

		ArrayList<DomaineModel> myDomaines = domaineCRUD.getAllDomaines(DBconnection);

		ObservableList<DomaineModel> domaineObservables = FXCollections.observableArrayList() ;
		domaineTable.setItems(domaineObservables);

		for(int i=0;i<myDomaines.size();i++) {
			domaineObservables.add(myDomaines.get(i));

		}
	}



	public void loadDomainesIDS() {
		// remplire comboBox
		ArrayList<DomaineModel> myDomaine = domaineCRUD.getAllDomaines(DBconnection);

		ObservableList<Integer> domaineIdsObservables = FXCollections.observableArrayList() ;
		domaineIdsCombo.setItems(domaineIdsObservables);

		for(int i=0;i<myDomaine.size();i++) {
			domaineIdsObservables.add(myDomaine.get(i).getID());

		}
		Collections.sort(domaineIdsObservables);
	}

	@FXML
	private void createDomaine(ActionEvent event){
		domaineMessageLabel.setVisible(true);
		domaineMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String libelle=newDomaine.getText().trim().toLowerCase();

		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(libelle)) {
			domaineMessageLabel.setText("Le Libelle du nouvel domaine n'accepte que des chaines de caractére!");
		}else if(domaineCRUD.getDomaineByLibelle(DBconnection, libelle)){
			domaineMessageLabel.setText("Le Domaine est deja existe!");
		}else {


			String result = domaineCRUD.createDomaine(DBconnection,libelle);
			// verifier si le domaine a été ajouté
			if(result.contains("Le domaine a été ajouté avec succés.")) {
				domaineMessageLabel.setText("Le domaine a été ajouté avec succés.");
				domaineMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				domaineMessageLabel.setText(result);
			}
		}

	}


	@FXML
	private void ModifierDomaine(ActionEvent event){
		domaineMessageLabel.setVisible(true);
		domaineMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String libelle=domaineModifyLibelle.getText().trim().toLowerCase();

		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(libelle)) {
			domaineMessageLabel.setText("Le Libelle n'accepte que des chaines de caractére!");
		}else if(domaineIdsCombo.getValue()==null) {
			domaineMessageLabel.setText("Sélectionner l'identifiant du domaine é modifier!");
		}
		else if(domaineCRUD.getDomaineByLibelle(DBconnection, libelle)){
			domaineMessageLabel.setText("Le libelle est deja existe!");
		}else {

			int id=domaineIdsCombo.getValue();
			String result = domaineCRUD.modifyDomaine(DBconnection,id,libelle);
			// verifier si le domaine a été ajouté
			if(result.contains("Le domaine a été modifié avec succés.")) {
				domaineMessageLabel.setText("Le domaine a été modifié avec succés.");
				domaineMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				domaineMessageLabel.setText(result);

			}


		}

	}





	@FXML
	private void deleteDomaine(ActionEvent event){
		domaineMessageLabel.setVisible(true);
		domaineMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String libelle=domaineDeleteLogin.getText().trim().toLowerCase();

		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(libelle)) {
			domaineMessageLabel.setText("Le Libelle n'accepte que des chaines de caractére!");
		}else {


			String result = domaineCRUD.deleteDomaine(DBconnection,libelle);
			// verifier si le domaine a été ajouté
			if(result.contains("Le domaine a été supprimé.")) {
				domaineMessageLabel.setText("Le domaine a été supprimé.");
				domaineMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				domaineMessageLabel.setText(result);

			}


		}

	}




	// create domaine button handler
	@FXML
	private void createDomaineButtonActivation(KeyEvent event){
		// activer/disactiver le button de crée du domaine
		String login=newDomaine.getText().trim();
		if(login.length()!=0 ) {
			createDomaineButton.setDisable(false);
		}else {
			createDomaineButton.setDisable(true);
		}
	}

	// modify domaine button handler
	@FXML
	private void modifyDomaineButtonActivation(KeyEvent event){
		// activer/disactiver le button de modification dudomaine
		String libelle=domaineModifyLibelle.getText().trim();
		if(libelle.length()!=0) {
			modifyDomaineButton.setDisable(false);
		}else {
			modifyDomaineButton.setDisable(true);
		}
	}

	// delete domaine button handler
	@FXML
	private void deleteDomaineButtonActivation(KeyEvent event){
		// activer/disactiver le button de suppression du domaine
		String libelle=domaineDeleteLogin.getText().trim();
		if(libelle.length()!=0 ) {
			deleteDomaineButton.setDisable(false);
		}else {
			deleteDomaineButton.setDisable(true);
		}
	}







	// Formateur functions **********************************************************************

	// checkbox fill-in
	public void loadFormateurs() {
		// remplire le tableau

		ArrayList<FormateurModel> myFormateurs = formateurCRUD.getAllFormateurs(DBconnection);

		ObservableList<FormateurModel> formateurObservables = FXCollections.observableArrayList() ;
		formateurTable.setItems(formateurObservables);
		formateurTable.setFixedCellSize(25);
		if(myFormateurs.size() > 0)
			formateurTable.prefHeightProperty().bind(formateurTable.fixedCellSizeProperty().multiply(Bindings.size(formateurTable.getItems()).add(1.01)));
		else
			formateurTable.prefHeightProperty().bind(formateurTable.fixedCellSizeProperty().multiply(3.01));
		formateurTable.minHeightProperty().bind(formateurTable.prefHeightProperty());
		formateurTable.maxHeightProperty().bind(formateurTable.prefHeightProperty());
		for(int i=0;i<myFormateurs.size();i++) {
			formateurObservables.add(myFormateurs.get(i));

		}
	}


	public void loadDomainesLibelles() {
		// remplire comboBox
		ArrayList<DomaineModel> myDomaine = domaineCRUD.getAllDomaines(DBconnection);

		ObservableList<String> domaineIdsObservables = FXCollections.observableArrayList() ;
		newFormateurDomaineCombo.setItems(domaineIdsObservables);
		newFormationDomaineCombo.setItems(domaineIdsObservables);
		modifierFormateurDomaineCombo.setItems(domaineIdsObservables);
		modifierFormationDomaineCombo.setItems(domaineIdsObservables);
		for(int i=0;i<myDomaine.size();i++) {
			String dom=Character.toUpperCase(myDomaine.get(i).getLibelle().charAt(0)) + myDomaine.get(i).getLibelle().substring(1);
			domaineIdsObservables.add(dom);

		}
		Collections.sort(domaineIdsObservables);
	}







	public void loadFormateursIDS() {
		// remplire comboBox
		ArrayList<FormateurModel> myFormateur = formateurCRUD.getAllFormateurs(DBconnection);

		ObservableList<Integer> formateurIdsObservables = FXCollections.observableArrayList() ;
		deleteFormateurIdCombo.setItems(formateurIdsObservables);
		modifierFormateurIdCombo.setItems(formateurIdsObservables);
		for(int i=0;i<myFormateur.size();i++) {
			formateurIdsObservables.add(myFormateur.get(i).getID());
		}
		Collections.sort(formateurIdsObservables);
	}


	// functions
	@FXML
	private void createFormateur(ActionEvent event){
		formateurMessageLabel.setVisible(true);
		formateurMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String nom=newFormateurNom.getText().trim();
		String prenom=newFormateurPrenom.getText().trim();
		String email=newFormateurEmail.getText().trim();
		String telephone=newFormateurTelephone.getText().trim();
		int domaineId= domaineCRUD.getDomaineIdByLibelle(DBconnection, newFormateurDomaineCombo.getValue()==null?"":newFormateurDomaineCombo.getValue());

		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(nom)) {
			formateurMessageLabel.setText("Le nom du nouvel instructeur n'accepte que des chaines de caractére!");
		}else if(LogController.containsNumber(prenom)) {
			formateurMessageLabel.setText("Le prenom du nouvel instructeur n'accepte que des chaines de caractére!");
		}else if(!LogController.isNumber(telephone)) {
			formateurMessageLabel.setText("Le numero du telephone du nouvel instructeur n'accepte que des chiffre!");
		}else if(telephone.length() != 8) {
			formateurMessageLabel.setText("Le numero du telephone du nouvel instructeur doit étre composée de 8 chiffre!");
		}else if(!LogController.isEmail(email)) {
			formateurMessageLabel.setText("E-mail non valide!");
		}else if(domaineId==0){
			formateurMessageLabel.setText("Saisir le domaine du nouvel instructeur!");
		}else if(domaineId==-1){
			formateurMessageLabel.setText("Opération échouée!");
		}else {


			String result = formateurCRUD.createFormateur(DBconnection,nom,prenom,domaineId,email,Integer.parseInt(telephone));
			// verifier si le formateur a été ajouté
			if(result.contains("Le nouvel formateur a été ajouté avec succés.")) {
				formateurMessageLabel.setText("Le nouvel formateur a été ajouté avec succés.");
				formateurMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				formateurMessageLabel.setText(result);
			}
		}

	}



	@FXML
	private void ModifierFormateur(ActionEvent event){
		formateurMessageLabel.setVisible(true);
		formateurMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		int id= modifierFormateurIdCombo.getValue();
		String nom=modifierFormateurNom.getText().trim();
		String prenom=modifierFormateurPrenom.getText().trim();
		String email=modifierFormateurEmail.getText().trim();
		String telephone=modifierFormateurTelephone.getText().trim();
		int domaineId= DomaineCrud.getDomaineIdByLibelle(DBconnection, modifierFormateurDomaineCombo.getValue()==null?"":modifierFormateurDomaineCombo.getValue());



		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(nom)) {
			formateurMessageLabel.setText("Le nouvel nom d'instructeur n'accepte que des chaines de caractére!");
		}else if(LogController.containsNumber(prenom)) {
			formateurMessageLabel.setText("Le nouvel prenom d'instructeur n'accepte que des chaines de caractére!");
		}else if(!LogController.isNumber(telephone)) {
			formateurMessageLabel.setText("Le nouvel numero du telephone d'instructeur n'accepte que des chiffre!");
		}else if(telephone.length() != 8) {
			formateurMessageLabel.setText("Le nouvel numero du telephone d'instructeur doit étre composée de 8 chiffre!");
		}else if(!LogController.isEmail(email)) {
			formateurMessageLabel.setText("Le nouvel E-mail non valide!");
		}else if(domaineId==0){
			formateurMessageLabel.setText("Saisir le domaine d'instructeur!");
		}else if(domaineId==-1){
			formateurMessageLabel.setText("Opération échouée!");
		}else {


			String result = formateurCRUD.modifyFormateur(DBconnection,id,nom,prenom,domaineId,email,Integer.parseInt(telephone));
			// verifier si le formateur a été modifié
			if(result.contains("Le Formateur a été modifié avec succés.")) {
				formateurMessageLabel.setText("Le Formateur a été modifié avec succés.");
				formateurMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				formateurMessageLabel.setText(result);
			}
		}

	}





	@FXML
	private void deleteFormateur(ActionEvent event){

		String result=null;
		int id;
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Supprimer");
		alert.setHeaderText("Êtes-vous sûr de supprimer ce formateur ?");
		if (alert.showAndWait().get() == ButtonType.OK){
			formateurMessageLabel.setVisible(true);
			formateurMessageLabel.setStyle("-fx-background-color:#f93154;");

			// collecter les donner saisit par l'utilisateur
			 id=deleteFormateurIdCombo.getValue();
			 result = formateurCRUD.deleteFormateur(DBconnection,id);
		}
		// verifier si le domaine a été ajouté
		if(result.contains("Le formateur a été supprimé.")) {
			formateurMessageLabel.setText("Le formateur a été supprimé.");
			formateurMessageLabel.setStyle("-fx-background-color:#33b5e5;");
			loadAll();
		}else {
			formateurMessageLabel.setText(result);
		}


	}



	// create Instructeur button handler
	@FXML
	private void createInstructerButtonActivation(KeyEvent event){
		// activer/disactiver le button de crée du instructeur
		String nom=newFormateurNom.getText().trim();
		String prenom=newFormateurPrenom.getText().trim();
		String email=newFormateurEmail.getText().trim();
		String telephone=newFormateurTelephone.getText().trim();
		if(nom.length()!=0 && prenom.length()!=0 && email.length()!=0 && telephone.length()!=0) {
			createFormateurButton.setDisable(false);
		}else {
			createFormateurButton.setDisable(true);
		}
	}



	// modify formateur button handler
	@FXML
	private void modifyFormateurButtonActivation(KeyEvent event){
		// activer/disactiver le button de modification du formateur
		String nom=modifierFormateurNom.getText().trim();
		String prenom=modifierFormateurPrenom.getText().trim();
		String email=modifierFormateurEmail.getText().trim();
		String telephone=modifierFormateurTelephone.getText().trim();


		if(nom.length()!=0 && prenom.length()!=0 && email.length()!=0 && telephone.length()!=0 && modifierFormateurIdCombo.getValue() != null && modifierFormateurDomaineCombo.getValue() != null ) {
			modifyFormateurButton.setDisable(false);
		}else {
			modifyFormateurButton.setDisable(true);
		}
	}

	@FXML
	private void modifyFormateurButtonActivationAction(ActionEvent event){
		// activer/disactiver le button de modification du formateur
		String nom=modifierFormateurNom.getText().trim();
		String prenom=modifierFormateurPrenom.getText().trim();
		String email=modifierFormateurEmail.getText().trim();
		String telephone=modifierFormateurTelephone.getText().trim();

		if(modifierFormateurIdCombo.getValue() != null && event.getSource() ==modifierFormateurIdCombo) {
			int id =modifierFormateurIdCombo.getValue();
			FormateurModel myformateur = formateurCRUD.getSignleFormateurById(DBconnection, id);
			modifierFormateurNom.setText(myformateur.getNom());
			modifierFormateurPrenom.setText(myformateur.getPrenom());
			modifierFormateurEmail.setText(myformateur.getEmail());
			modifierFormateurTelephone.setText( Integer.toString(myformateur.getNum_telephone()) );
			modifierFormateurDomaineCombo.setValue(myformateur.getDomaine());;
		}





		if(nom.length()!=0 && prenom.length()!=0 && email.length()!=0 && telephone.length()!=0 && modifierFormateurIdCombo.getValue() != null && modifierFormateurDomaineCombo.getValue() != null ) {
			modifyFormateurButton.setDisable(false);
		}else {
			modifyFormateurButton.setDisable(true);
		}
	}


	// delete formateur button handler
	@FXML
	private void deleteFormateurButtonActivation(ActionEvent event){
		// activer/disactiver le button de suppression du Formateur
		if(deleteFormateurIdCombo.getValue() != null ) {
			deleteFormateurButton.setDisable(false);
		}else {
			deleteFormateurButton.setDisable(true);
		}
	}




	// Formation functions **********************************************************************

	// checkbox fill-in
	public void loadFormation() {
		// remplire le tableau

		ArrayList<FormationModel> myFormations = formationCRUD.getAllFormations(DBconnection);

		ObservableList<FormationModel> formationObservables = FXCollections.observableArrayList() ;
		formationTable.setItems(formationObservables);

		for(int i=0;i<myFormations.size();i++) {
			formationObservables.add(myFormations.get(i));
		}
	}



	public void loadFormateursNames() {
		// remplire le tableau

		ArrayList<FormateurModel> myFormateurs = formateurCRUD.getAllFormateurs(DBconnection);

		ObservableList<String> formateurObservables = FXCollections.observableArrayList() ;
		newFormationFormateurCombo.setItems(formateurObservables);
		modifierFormationFormateurCombo.setItems(formateurObservables);
		for(int i=0;i<myFormateurs.size();i++) {
			formateurObservables.add(myFormateurs.get(i).getNom()+" "+myFormateurs.get(i).getPrenom());

		}
	}


	public void loadFormationsIDS() {
		// remplire comboBox
		ArrayList<FormationModel> myFormation = formationCRUD.getAllFormations(DBconnection);

		ObservableList<Integer> formationIdsObservables = FXCollections.observableArrayList() ;
		deleteFormationIdCombo.setItems(formationIdsObservables);
		modifierFormationIDCombo.setItems(formationIdsObservables);
		for(int i=0;i<myFormation.size();i++) {
			formationIdsObservables.add(myFormation.get(i).getID());
		}
		Collections.sort(formationIdsObservables);
	}



	public static void setLoggedUser(String loggedUser) {
		DashboardController.loggedUser = loggedUser;
		System.out.println("loggedUser : "+loggedUser);
	}

	public String getLoggedUser() {
		return this.loggedUser;
	}
	// functions
	@FXML
	private void createFormation(ActionEvent event){
		formationMessageLabel.setVisible(true);
		formationMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String Intitule=newFormationIntitule.getText().trim();
		String Jours=newFormationJours.getText().trim();
		String Mois=newFormationMois.getText().trim();
		String Annee=newFormationAnnee.getText().trim();
		String Participants=newFormationParticipants.getText().trim();
		int domaineId= DomaineCrud.getDomaineIdByLibelle(DBconnection, newFormationDomaineCombo.getValue()==null?"":newFormationDomaineCombo.getValue());
		int formateurId= FormateurCrud.getFormateurIdByName(DBconnection, newFormationFormateurCombo.getValue()==null?"":newFormationFormateurCombo.getValue());

		java.util.Date d=new java.util.Date();
		int year=d.getYear()+1900;


		// verifier les donner saisit par l'utilisateur
		if(!LogController.isNumber(Jours) || Integer.parseInt(Jours)<0) {
			formationMessageLabel.setText("Le nombre du jours du nouvel formation n'accepte que des chiffre et doit étre positive!");
		}else if(!LogController.isNumber(Mois) || Integer.parseInt(Mois)<0 || Integer.parseInt(Mois)>12) {
			formationMessageLabel.setText("Le mois du nouvel formation n'accepte que des chiffre et doit étre entre 1-12!");
		}else if(!LogController.isNumber(Annee) || Integer.parseInt(Annee)<0 || Integer.parseInt(Annee)<year) {
			formationMessageLabel.setText("L'année du nouvel formation n'accepte que des chiffre et doit étre supérieur ou égal é "+year+"!");
		}else if(!LogController.isNumber(Participants) || Integer.parseInt(Participants)<4) {
			formationMessageLabel.setText("Le nombre des participants du nouvel formation n'accepte que des chiffre et doit étre plus que 3!");
		}else if(domaineId==0){
			formationMessageLabel.setText("Saisir le domaine du nouvel formation!");
		}else if(formateurId==0){
			formationMessageLabel.setText("Saisir l'instructeur du nouvel formation!");
		}else if(domaineId==-1 ||formateurId==-1){
			formationMessageLabel.setText("Opération échouée!");
		}else {


			String result = formationCRUD.createFormation(DBconnection,Intitule,domaineId,Integer.parseInt(Jours),Integer.parseInt(Annee), Integer.parseInt(Mois),formateurId,Integer.parseInt(Participants));
			// verifier si le formation a été ajouté
			if(result.contains("Le nouvel formation a été ajouté avec succés.")) {
				formationMessageLabel.setText("Le nouvel formation a été ajouté avec succés.");
				formationMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				formationMessageLabel.setText(result);
			}
		}

	}





	@FXML
	private void modifierFormation(ActionEvent event){
		formationMessageLabel.setVisible(true);
		formationMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String Intitule=modifierFormationIntitule.getText().trim();
		String Jours=modifierFormationJours.getText().trim();
		String Mois=modifierFormationMois.getText().trim();
		String Annee=modifierFormationAnnee.getText().trim();
		String Participants=modifierFormationParticipants.getText().trim();
		int domaineId= DomaineCrud.getDomaineIdByLibelle(DBconnection, modifierFormationDomaineCombo.getValue()==null?"":modifierFormationDomaineCombo.getValue());
		int formateurId= FormateurCrud.getFormateurIdByName(DBconnection, modifierFormationFormateurCombo.getValue()==null?"":modifierFormationFormateurCombo.getValue());

		java.util.Date d=new java.util.Date();
		int year=d.getYear()+1900;


		// verifier les donner saisit par l'utilisateur
		if(!LogController.isNumber(Jours) || Integer.parseInt(Jours)<0) {
			formationMessageLabel.setText("Le nouvel nombre du jours du formation n'accepte que des chiffre et doit étre positive!");
		}else if(!LogController.isNumber(Mois) || Integer.parseInt(Mois)<0 || Integer.parseInt(Mois)>12) {
			formationMessageLabel.setText("Le nouvel mois du formation n'accepte que des chiffre et doit étre entre 1-12!");
		}else if(!LogController.isNumber(Annee) || Integer.parseInt(Annee)<0 || Integer.parseInt(Annee)<year) {
			formationMessageLabel.setText("Le nouvel année du formation n'accepte que des chiffre et doit étre supérieur ou égal é "+year+"!");
		}else if(!LogController.isNumber(Participants) || Integer.parseInt(Participants)<4) {
			formationMessageLabel.setText("Le nouvel nombre des participants du formation n'accepte que des chiffre et doit étre plus que 3!");
		}else if(domaineId==0){
			formationMessageLabel.setText("Saisir le nouvel domaine du formation!");
		}else if(formateurId==0){
			formationMessageLabel.setText("Saisir le nouvel instructeur du formation!");
		}else if(domaineId==-1 ||formateurId==-1){
			formationMessageLabel.setText("Opération échouée!");
		}else {


			String result = formationCRUD.modifyFormation(DBconnection,modifierFormationIDCombo.getValue(),Intitule,domaineId,Integer.parseInt(Jours),Integer.parseInt(Annee), Integer.parseInt(Mois),formateurId,Integer.parseInt(Participants));
			// verifier si le formation a été modifée
			if(result.contains("La formation a été modifié avec succés.")) {
				formationMessageLabel.setText("La formation a été modifié avec succés.");
				formationMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				formationMessageLabel.setText(result);
			}
		}

	}









	@FXML
	private void deleteFormation(ActionEvent event){
		formationMessageLabel.setVisible(true);
		formationMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		int id=deleteFormationIdCombo.getValue();

		String result = formationCRUD.deleteFormation(DBconnection,id);
		// verifier si la formation a été ajouté
		if(result.contains("La formation a été supprimé.")) {
			formationMessageLabel.setText("La formation a été supprimé.");
			formationMessageLabel.setStyle("-fx-background-color:#33b5e5;");
			loadAll();
		}else {
			formationMessageLabel.setText(result);
		}


	}






	// create Formation button handler
	@FXML
	private void createFormationButton(ActionEvent event){
		// activer/disactiver le button de crée du formation
		String Intitule=newFormationIntitule.getText().trim();
		String Jours=newFormationJours.getText().trim();
		String Mois=newFormationMois.getText().trim();
		String Annee=newFormationAnnee.getText().trim();
		String Participants=newFormationParticipants.getText().trim();

		if(Intitule.length()!=0 && Jours.length()!=0 && Mois.length()!=0 && Annee.length()!=0 && Participants.length()!=0 && newFormationFormateurCombo.getValue() != null && newFormationDomaineCombo.getValue() != null) {
			createFormationButton.setDisable(false);
		}else {
			createFormationButton.setDisable(true);
		}
	}

	@FXML
	private void createFormationButtonActivation(KeyEvent event){
		// activer/disactiver le button de crée du formation
		String Intitule=newFormationIntitule.getText().trim();
		String Jours=newFormationJours.getText().trim();
		String Mois=newFormationMois.getText().trim();
		String Annee=newFormationAnnee.getText().trim();
		String Participants=newFormationParticipants.getText().trim();

		if(Intitule.length()!=0 && Jours.length()!=0 && Mois.length()!=0 && Annee.length()!=0 && Participants.length()!=0 && newFormationFormateurCombo.getValue() != null && newFormationDomaineCombo.getValue() != null) {
			createFormationButton.setDisable(false);
		}else {
			createFormationButton.setDisable(true);
		}
	}


	// modifier Formation button handler
	@FXML
	private void modifierFormationButton(ActionEvent event){
		// activer/disactiver le button de modifier du formation
		String Intitule=modifierFormationIntitule.getText().trim();
		String Jours=modifierFormationJours.getText().trim();
		String Mois=modifierFormationMois.getText().trim();
		String Annee=modifierFormationAnnee.getText().trim();
		String Participants=modifierFormationParticipants.getText().trim();

		if(modifierFormationIDCombo.getValue() != null && event.getSource() ==modifierFormationIDCombo) {
			int id =modifierFormationIDCombo.getValue();
			FormationModel myformation = formationCRUD.getSignleFormationById(DBconnection, id);
			modifierFormationIntitule.setText(myformation.getIntitule());
			modifierFormationJours.setText(myformation.getNombre_jours()+"");
			modifierFormationMois.setText(myformation.getMois()+"");
			modifierFormationAnnee.setText(myformation.getAnnee()+"");
			modifierFormationParticipants.setText(myformation.getNombre_participants()+"");
			modifierFormationDomaineCombo.setValue(myformation.getDomaine());
			modifierFormationFormateurCombo.setValue(myformation.getFormateur());
		}

		if(Intitule.length()!=0 && Jours.length()!=0 && Mois.length()!=0 && Annee.length()!=0 && Participants.length()!=0 && modifierFormationIDCombo.getValue() != null && modifierFormationDomaineCombo.getValue() != null && modifierFormationFormateurCombo.getValue() != null) {
			modifyFormationButton.setDisable(false);
		}else {
			modifyFormationButton.setDisable(true);
		}
	}

	@FXML
	private void modifierFormationButtonActivation(KeyEvent event){
		// activer/disactiver le button de modifier du formation
		String Intitule=modifierFormationIntitule.getText().trim();
		String Jours=modifierFormationJours.getText().trim();
		String Mois=modifierFormationMois.getText().trim();
		String Annee=modifierFormationAnnee.getText().trim();
		String Participants=modifierFormationParticipants.getText().trim();

		if(Intitule.length()!=0 && Jours.length()!=0 && Mois.length()!=0 && Annee.length()!=0 && Participants.length()!=0 && modifierFormationIDCombo.getValue() != null && modifierFormationDomaineCombo.getValue() != null && modifierFormationFormateurCombo.getValue() != null) {
			modifyFormationButton.setDisable(false);
		}else {
			modifyFormationButton.setDisable(true);
		}
	}

	// delete Formation button handler
	@FXML
	private void deleteFormationButtonActivation(ActionEvent event){
		// activer/disactiver le button de suppression du Formation
		if(deleteFormationIdCombo.getValue() != null ) {
			deleteFormationButton.setDisable(false);
		}else {
			deleteFormationButton.setDisable(true);
		}
	}



	// Participant functions **********************************************************************

	// checkbox fill-in
	public void loadParticipant() {
		// remplire le tableau

		ArrayList<ParticipantModel> myParticipants = participantCRUD.getAllEtudiant(DBconnection);

		ObservableList<ParticipantModel> participantObservables = FXCollections.observableArrayList() ;
		participantTable.setItems(participantObservables);

		for(int i=0;i<myParticipants.size();i++) {
			participantObservables.add(myParticipants.get(i));

		}
	}

	public void loadParticipantMatricule() {
		// remplire comboBox
		ArrayList<ParticipantModel> myParticipants = participantCRUD.getAllEtudiant(DBconnection);


		ObservableList<String> participantObservables = FXCollections.observableArrayList() ;
		deleteParticipantIdCombo.setItems(participantObservables);
		modifierParticipantIdCombo.setItems(participantObservables);

		for(int i=0;i<myParticipants.size();i++) {
			participantObservables.add(myParticipants.get(i).getMatricule_participant());

		}
	}


	public void loadProfilsLibelles() {
		// remplire comboBox
		ArrayList<ProfilModel> myProfils = profilCRUD.getAllProfils(DBconnection);

		ObservableList<String> profilsIdsObservables = FXCollections.observableArrayList() ;
		newParticipantProfilCombo.setItems(profilsIdsObservables);
		modifierParticipantProfilCombo.setItems(profilsIdsObservables);

		for(int i=0;i<myProfils.size();i++) {
			String dom=Character.toUpperCase(myProfils.get(i).getLibelle().charAt(0)) + myProfils.get(i).getLibelle().substring(1);
			profilsIdsObservables.add(dom);

		}
		Collections.sort(profilsIdsObservables);
	}



	// functions
	@FXML
	private void createParticipant(ActionEvent event){
		participantMessageLabel.setVisible(true);
		participantMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String mat=newParticipantMatricule.getText().trim();
		String nom=newParticipantNom.getText().trim();
		String prenom=newParticipantPrenom.getText().trim();
		int profilId= ProfilCrud.getParticipantById(DBconnection, newParticipantProfilCombo.getValue()==null?"":newParticipantProfilCombo.getValue());
		Date birthday = Date.valueOf(newParticipantDate.getValue().toString());


		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(nom)) {
			participantMessageLabel.setText("Le nom du nouvel participant n'accepte que des chaines de caractére!");
		}else if(LogController.containsNumber(prenom)) {
			participantMessageLabel.setText("Le prenom du nouvel participant n'accepte que des chaines de caractére!");
		}else if(!LogController.isNumber(mat) && mat.length()==8) {
			participantMessageLabel.setText("Le matricule du nouvel participant n'accepte que des chiffres et composée de 8 chiffres!");
		}else if(profilId==0){
			participantMessageLabel.setText("Saisir le profil du nouvel participant!");
		}else if(profilId==-1){
			participantMessageLabel.setText("Opération échouée!");
		}


		else{
			String result = participantCRUD.createEtudiant(DBconnection,Integer.parseInt(mat),nom,prenom,profilId,birthday);
			// verifier si l'participant a été ajouté
			if(result.contains("Le nouvel participant a été ajouté avec succés.")) {
				participantMessageLabel.setText("Le nouvel participant a été ajouté avec succés.");
				participantMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				participantMessageLabel.setText(result);
			}
		}



	}


	//modifier Participant
	@FXML
	private void ModifierParticipant(ActionEvent event){
		participantMessageLabel.setVisible(true);
		participantMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		String mat=modifierParticipantIdCombo.getValue().trim();
		String nom=modifierParticipantNom.getText().trim();
		String prenom=modifierParticipantPrenom.getText().trim();
		int profilId= ProfilCrud.getParticipantById(DBconnection, modifierParticipantProfilCombo.getValue()==null?"":modifierParticipantProfilCombo.getValue());
		Date birthday = Date.valueOf(modifierParticipantDate.getValue().toString());




		// verifier les donner saisit par l'utilisateur
		if(LogController.containsNumber(nom)) {
			participantMessageLabel.setText("Le nouvel nom du participant a modifier n'accepte que des chaines de caractére!");
		}else if(LogController.containsNumber(prenom)) {
			participantMessageLabel.setText("Le prenom du nouvel participant n'accepte que des chaines de caractére!");
		}else if(!LogController.isNumber(mat) && mat.length()==8) {
			participantMessageLabel.setText("Le nouvel matricule du participant a modifier n'accepte que des chiffres et composée de 8 chiffres!");
		}else if(profilId==0){
			participantMessageLabel.setText("Saisir le nouvel profil du participant a modifer!");
		}else if(profilId==-1){
			participantMessageLabel.setText("Opération échouée!");
		}


		else{

			String result = participantCRUD.modifyEtudiant(DBconnection,Integer.parseInt(mat),nom,prenom,profilId,birthday);
			// verifier si l'participant a été modifier
			if(result.contains("L'participant a été modifié avec succés.")) {
				participantMessageLabel.setText("L'participant a été modifié avec succés.");
				participantMessageLabel.setStyle("-fx-background-color:#33b5e5;");
				loadAll();
			}else {
				participantMessageLabel.setText(result);
			}
		}

	}






	//delete Participant
	@FXML
	private void deleteParticipant(ActionEvent event){
		participantMessageLabel.setVisible(true);
		participantMessageLabel.setStyle("-fx-background-color:#f93154;");

		// collecter les donner saisit par l'utilisateur
		int id=Integer.parseInt(deleteParticipantIdCombo.getValue());

		String result = participantCRUD.deleteEtudiant(DBconnection,id);
		// verifier si l'etudiant a été ajouté
		if(result.contains("L'participant a été supprimé.")) {
			participantMessageLabel.setText("L'participant a été supprimé.");
			participantMessageLabel.setStyle("-fx-background-color:#33b5e5;");
			loadAll();
		}else {
			participantMessageLabel.setText(result);
		}



	}




	// create etudiant button handler
	@FXML
	private void createParticipantButton(ActionEvent event){
		// activer/disactiver le button de crée du participant
		String mat=newParticipantMatricule.getText().trim();
		String nom=newParticipantNom.getText().trim();
		String prenom=newParticipantPrenom.getText().trim();


		if(mat.length()!=0 && nom.length()!=0 && prenom.length()!=0 && newParticipantProfilCombo.getValue() != null && newParticipantDate.getValue() != null) {
			createParticipantButton.setDisable(false);
		}else {
			createParticipantButton.setDisable(true);
		}
	}





	@FXML
	private void createParticipantButtonActivation(KeyEvent event){
		// activer/disactiver le button de crée du participant
		String mat=newParticipantMatricule.getText().trim();
		String nom=newParticipantNom.getText().trim();
		String prenom=newParticipantPrenom.getText().trim();


		if(mat.length()!=0 && nom.length()!=0 && prenom.length()!=0 && newParticipantProfilCombo.getValue() != null && newParticipantDate.getValue() != null) {
			createParticipantButton.setDisable(false);
		}else {
			createParticipantButton.setDisable(true);
		}
	}



	// modifier etudiant button handler
	@FXML
	private void modifyParticipantButtonActivationAction(ActionEvent event){
		// activer/disactiver le button de modification du formateur
		String nom=modifierParticipantNom.getText().trim();
		String prenom=modifierParticipantPrenom.getText().trim();

		if(modifierParticipantIdCombo.getValue() != null && event.getSource() ==modifierParticipantIdCombo) {
			int id =Integer.parseInt( modifierParticipantIdCombo.getValue() );
			ParticipantModel myparticipant = ParticipantCrud.getSignleParticipantById(DBconnection, id);
			modifierParticipantNom.setText(myparticipant.getNom());
			modifierParticipantPrenom.setText(myparticipant.getPrenom());
			String myDate=myparticipant.getDate_naissance().toString();
			modifierParticipantDate.setValue(LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(myDate)));
			modifierParticipantProfilCombo.setValue(myparticipant.getProfil().toLowerCase());
		}





		if(nom.length()!=0 && prenom.length()!=0 && modifierParticipantProfilCombo.getValue() != null && modifierParticipantDate.getValue() != null ) {
			modifyParticipantButton.setDisable(false);
		}else {
			modifyParticipantButton.setDisable(true);
		}
	}



	@FXML
	private void modifierParticipantButtonActivation(KeyEvent event){
		// activer/disactiver le button de modifier du participant
		String nom=modifierParticipantNom.getText().trim();
		String prenom=modifierParticipantPrenom.getText().trim();

		if(nom.length()!=0 && prenom.length()!=0 && modifierParticipantProfilCombo.getValue() != null && modifierParticipantDate.getValue() != null ) {
			modifyParticipantButton.setDisable(false);
		}else {
			modifyParticipantButton.setDisable(true);
		}
	}


	// delete Formation button handler
	@FXML
	private void deleteParticipantButtonActivation(ActionEvent event){
		// activer/disactiver le button de suppression du Formation
		if(deleteParticipantIdCombo.getValue() != null ) {
			deleteParticipantButton.setDisable(false);
		}else {
			deleteParticipantButton.setDisable(true);
		}
	}


	@FXML
	private void Logout(ActionEvent event){
		try {
			//redirection to another stage
			URL myURL = new URL(filePath.toString().replace("/dashboard/DashboardScreen.fxml", "/log/LogScreen.fxml"));
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




	}


}
