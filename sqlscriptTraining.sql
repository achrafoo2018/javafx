-- Creation du base de donnée:

--
-- Database: `gestion_formation`
--

-- Table Utilisateur
CREATE TABLE utilisateur(
    Code_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    Login VARCHAR(100),
    Password VARCHAR(100),
    Role VARCHAR(15) CHECK (Role IN ('Administrateur', 'Utilisateur'))
    );
	
	
-- Table Domaine
CREATE TABLE domaine(
    Code_domaine INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) UNIQUE
    );


-- Table Profil
CREATE TABLE profil(
    Code_profil INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) UNIQUE
    );
	
-- Table Formateur
CREATE TABLE formateur(
    Code_formateur INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(100),
    Prenom VARCHAR(100),
    Domaine INT,
    Email VARCHAR(100),
    Num_telephone INT,
    CONSTRAINT FOREIGN KEY (Domaine) REFERENCES domaine(Code_domaine)
    );
	
	
-- Table Formation
CREATE TABLE formation(
    Code_formation INT AUTO_INCREMENT PRIMARY KEY,
    Intitulé VARCHAR(100),
    Domaine INT,
    Nombre_jours INT,
    Annee INT,
    mois INT,
    Formateur INT,
    Nombre_participants INT,
    CONSTRAINT FOREIGN KEY (Domaine) REFERENCES domaine(Code_domaine),
    CONSTRAINT FOREIGN KEY (Formateur) REFERENCES formateur(Code_formateur)
    );



-- Table Participant
CREATE TABLE participant(
    Matricule_participant INT PRIMARY KEY,
    Nom VARCHAR(100),
    Prenom VARCHAR(100),
    Profil INT,
    Date_naissance DATE,
    CONSTRAINT FOREIGN KEY (Profil) REFERENCES profil(Code_profil)
    );


-- Table Participation
CREATE TABLE participation(
    Matricule_participant INT,
    Code_formation INT,
    CONSTRAINT FOREIGN KEY (Matricule_participant) REFERENCES participant(Matricule_participant),
	CONSTRAINT FOREIGN KEY (Code_formation) REFERENCES formation(Code_formation),
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
INSERT INTO `profil`(`libelle`) VALUES ('Architecte Logiciel');
INSERT INTO `profil`(`libelle`) VALUES ('Architecte Infrastructure');
INSERT INTO `profil`(`libelle`) VALUES ('Technicien Informatique');
INSERT INTO `profil`(`libelle`) VALUES ('Scrum Master');
INSERT INTO `profil`(`libelle`) VALUES ('Ingénieur Systèmes');
INSERT INTO `profil`(`libelle`) VALUES ('Ingénieur Réseau');
INSERT INTO `profil`(`libelle`) VALUES ('Développeur');
INSERT INTO `profil`(`libelle`) VALUES ('Infirmier');
INSERT INTO `profil`(`libelle`) VALUES ('Analyste Financier');
INSERT INTO `profil`(`libelle`) VALUES ('Credit Manager');
INSERT INTO `profil`(`libelle`) VALUES ('Expert Financier');
INSERT INTO `profil`(`libelle`) VALUES ('Logisticien');
INSERT INTO `profil`(`libelle`) VALUES ('Analyste Marketing');
INSERT INTO `profil`(`libelle`) VALUES ('Webmarketeur');
INSERT INTO `profil`(`libelle`) VALUES ('Directeur Marketing');
INSERT INTO `profil`(`libelle`) VALUES ('Directeur Média');
INSERT INTO `profil`(`libelle`) VALUES ('Avocat');
