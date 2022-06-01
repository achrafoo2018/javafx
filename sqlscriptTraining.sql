-- Creation du base de donnée:
-- CREATE DATABASE gestion_formation;

-- Table Utilisateur
CREATE TABLE Utilisateur(
    Code_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    Login VARCHAR(100),
    Password VARCHAR(100),
    Role VARCHAR(15) CHECK (Role IN ('Administrateur', 'Utilisateur'))
    );
	
	
-- Table Domaine
CREATE TABLE Domaine(
    Code_domaine INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) UNIQUE
    );


-- Table Profil
CREATE TABLE Profil(
    Code_profil INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) UNIQUE
    );
	
-- Table Formateur
CREATE TABLE Formateur(
    Code_formateur INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(100),
    Prenom VARCHAR(100),
    Domaine INT,
    Email VARCHAR(100),
    Num_telephone INT,
    CONSTRAINT FOREIGN KEY (Domaine) REFERENCES Domaine(Code_domaine)
    );
	
	
-- Table Formation
CREATE TABLE Formation(
    Code_formation INT AUTO_INCREMENT PRIMARY KEY,
    Intitulé VARCHAR(100),
    Domaine INT,
    Nombre_jours INT,
    Annee INT,
    mois INT,
    Formateur INT,
    Nombre_participants INT,
    CONSTRAINT FOREIGN KEY (Domaine) REFERENCES Domaine(Code_domaine),
    CONSTRAINT FOREIGN KEY (Formateur) REFERENCES Formateur(Code_formateur)
    );



-- Table Participant
CREATE TABLE Participant(
    Matricule_participant INT PRIMARY KEY,
    Nom VARCHAR(100),
    Prenom VARCHAR(100),
    Profil INT,
    Date_naissance DATE,
    CONSTRAINT FOREIGN KEY (Profil) REFERENCES Profil(Code_profil)
    );


-- Table Participation
CREATE TABLE Participation(
    Matricule_participant INT,
    Code_formation INT,
    CONSTRAINT FOREIGN KEY (Matricule_participant) REFERENCES Participant(Matricule_participant),
	CONSTRAINT FOREIGN KEY (Code_formation) REFERENCES Formation(Code_formation),
	primary key (Code_formation, Matricule_participant)
    );



-- Remplire Domaine
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('hotellerie');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('agriculture');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('audiovisuel');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('comptabilité');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('gestion');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('informatique');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('mecanique');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('commerce');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('marketing');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('medical');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('paramédical');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('chimique');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('electronique');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('logistique');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('droit');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('communication');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('assurance');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('design');
INSERT INTO `gestion_formation`.`domaine`(`libelle`) VALUES ('securite');

-- Remplire Profil
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Architecte Logiciel');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Architecte Infrastructure');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Technicien Informatique');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Scrum Master');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Ingénieur Systèmes');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Ingénieur Réseau');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Développeur');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Infirmier');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Analyste Financier');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Credit Manager');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Expert Financier');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Logisticien');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Analyste Marketing');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Webmarketeur');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Directeur Marketing');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Directeur Média');
INSERT INTO `gestion_formation`.`Profil`(`libelle`) VALUES ('Avocat');
