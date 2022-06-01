-- Creation du base de donnée:
CREATE DATABASE gestion_formation;

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
INSERT INTO `domaine`(`libelle`) VALUES ('hotellerie');
INSERT INTO `domaine`(`libelle`) VALUES ('agriculture');
INSERT INTO `domaine`(`libelle`) VALUES ('audiovisuel');
INSERT INTO `domaine`(`libelle`) VALUES ('comptabilité');
INSERT INTO `domaine`(`libelle`) VALUES ('gestion');
INSERT INTO `domaine`(`libelle`) VALUES ('informatique');
INSERT INTO `domaine`(`libelle`) VALUES ('mecanique');
INSERT INTO `domaine`(`libelle`) VALUES ('commerce');
INSERT INTO `domaine`(`libelle`) VALUES ('marketing');
INSERT INTO `domaine`(`libelle`) VALUES ('medical');
INSERT INTO `domaine`(`libelle`) VALUES ('paramédical');
INSERT INTO `domaine`(`libelle`) VALUES ('chimique');
INSERT INTO `domaine`(`libelle`) VALUES ('electronique');
INSERT INTO `domaine`(`libelle`) VALUES ('logistique');
INSERT INTO `domaine`(`libelle`) VALUES ('droit');
INSERT INTO `domaine`(`libelle`) VALUES ('communication');
INSERT INTO `domaine`(`libelle`) VALUES ('assurance');
INSERT INTO `domaine`(`libelle`) VALUES ('design');
INSERT INTO `domaine`(`libelle`) VALUES ('securite');

-- Remplire Profil
INSERT INTO `Profil`(`libelle`) VALUES ('Architecte Logiciel');
INSERT INTO `Profil`(`libelle`) VALUES ('Architecte Infrastructure');
INSERT INTO `Profil`(`libelle`) VALUES ('Technicien Informatique');
INSERT INTO `Profil`(`libelle`) VALUES ('Scrum Master');
INSERT INTO `Profil`(`libelle`) VALUES ('Ingénieur Systèmes');
INSERT INTO `Profil`(`libelle`) VALUES ('Ingénieur Réseau');
INSERT INTO `Profil`(`libelle`) VALUES ('Développeur');
INSERT INTO `Profil`(`libelle`) VALUES ('Infirmier');
INSERT INTO `Profil`(`libelle`) VALUES ('Analyste Financier');
INSERT INTO `Profil`(`libelle`) VALUES ('Credit Manager');
INSERT INTO `Profil`(`libelle`) VALUES ('Expert Financier');
INSERT INTO `Profil`(`libelle`) VALUES ('Logisticien');
INSERT INTO `Profil`(`libelle`) VALUES ('Analyste Marketing');
INSERT INTO `Profil`(`libelle`) VALUES ('Webmarketeur');
INSERT INTO `Profil`(`libelle`) VALUES ('Directeur Marketing');
INSERT INTO `Profil`(`libelle`) VALUES ('Directeur Média');
INSERT INTO `Profil`(`libelle`) VALUES ('Avocat');
