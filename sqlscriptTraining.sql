-- Creation du base de donnée:

--
-- Database: `gestion_formation`
--

-- Table Utilisateur
CREATE TABLE utilisateur(
    Code_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    Login VARCHAR(100),
    Password VARCHAR(100),
    Name VARCHAR(100),
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
INSERT INTO `domaine`(`libelle`) VALUES ('sante');
INSERT INTO `domaine`(`libelle`) VALUES ('sport');
INSERT INTO `domaine`(`libelle`) VALUES ('tourisme');
INSERT INTO `domaine`(`libelle`) VALUES ('transport');
INSERT INTO `domaine`(`libelle`) VALUES ('autre');


INSERT INTO `profil`(`libelle`) VALUES ('Architecte Logiciel');
INSERT INTO `profil`(`libelle`) VALUES ('Architecte Infrastructure');
INSERT INTO `profil`(`libelle`) VALUES ('Technicien Informatique');
INSERT INTO `profil`(`libelle`) VALUES ('Scrum Master');
INSERT INTO `profil`(`libelle`) VALUES ('Ingénieur Systèmes');
INSERT INTO `profil`(`libelle`) VALUES ('Ingénieur Réseau');
INSERT INTO `profil`(`libelle`) VALUES ('Data Scientist');
INSERT INTO `profil`(`libelle`) VALUES ('Ingénieur Logiciel');
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
INSERT INTO `profil`(`libelle`) VALUES ('Notaire');
INSERT INTO `profil`(`libelle`) VALUES ('Ingénieur Mécanique');


INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('admin', 'admin', 'admin', 'Administrateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('user', 'user', 'user', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('achraf', 'achraf123', 'Achraf Ben Soltane', 'Administrateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('hamma', 'hamma123', 'Mohamed Amine', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('zeineb', 'user3', 'Zeineb Zayet', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('ryan', 'user4', 'Ryan Escobar', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('miguel', 'user5', 'Miguel Valentine', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('vince', 'user6', 'Vince Mitchell', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('ronald', 'user7', 'Ronald Spencer', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('vaughn', 'user8', 'Vaughn Huffman', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('paul', 'user9', 'Paul Mcintosh', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('carey', 'user10', 'Carey Jones', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('francine', 'user11', 'Francine Farrell', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('adela', 'user12', 'Adela Davenport', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('molly', 'user13', 'Molly Walton', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('donna', 'user14', 'Donna Walls', 'Utilisateur');
INSERT INTO `utilisateur`(`Login`, `Password`, `Name`, `Role`) VALUES ('christina', 'user15', 'Christina Anthony', 'Utilisateur');




INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Ranyelld","Florida",9,"franyelld1@sun.com","5133334947");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Clampe","Renaldo",4,"rclampe4@seattletimes.com","3411833184");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Fryd","Eal",9,"efryd5@nps.gov","5613674878");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Lanktree","Gaultiero",1,"glanktree6@addtoany.com","8855632006");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Fitzsymon","Kelcie",4,"kfitzsymon7@devhub.com","5139068873");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Yakubowicz","Annaliese",1,"ayakubowicz8@jimdo.com","2648351176");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Murrison","Bunny",6,"bmurrison9@tiny.cc","3979834856");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Plet","Patricio",5,"ppleta@shareasale.com","5712895439");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Eubank","Corilla",9,"ceubankb@netlog.com","2891079256");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Churchley","Ado",4,"achurchleyc@ehow.com","1531792229");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Prigg","Anitra",2,"apriggf@seesaa.net","7966955478");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Tasseler","Roseanna",7,"rtasselerg@elegantthemes.com","6116253485");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Logsdail","Celia",8,"clogsdailh@theatlantic.com","8993637981");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Smalls","Julienne",6,"jsmallsi@myspace.com","7741415386");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Tidmas","Elisabeth",3,"etidmasj@webs.com","6469263342");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Noton","Claudina",5,"cnotonk@archive.org","4323424732");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Canland","Guglielma",10,"gcanlandl@pbs.org","5768075066");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Dan","Simona",7,"sdanm@slashdot.org","8478254119");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Conerding","Truman",2,"tconerdingn@washington.edu","8755389570");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Aldhouse","Augy",10,"aaldhouseo@dell.com","5868867373");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Gandy","Catarina",7,"cgandyp@google.cn","7342525750");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Moorman","Gualterio",6,"gmoormanq@umich.edu","1419020453");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Basindale","Jacquie",3,"jbasindaler@ca.gov","4629524383");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Paulat","Iseabal",5,"ipaulats@webmd.com","8276708306");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Derobert","Todd",9,"tderobertt@walmart.com","9046762071");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Windows","Mick",2,"mwindowsu@fotki.com","9163468812");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Beiderbecke","Arda",4,"abeiderbeckev@theguardian.com","1051056087");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Arter","Dud",5,"dartery@discuz.net","9561648695");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Giorgio","Zebedee",4,"zgiorgioz@army.mil","9772505899");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Valenta","Dierdre",4,"dvalenta10@tiny.cc","8443993740");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("McBlain","Noble",2,"nmcblain12@netscape.com","8207029192");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Lorenzetto","Gerti",10,"glorenzetto13@angelfire.com","5421657155");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Firmager","Yolande",1,"yfirmager14@yale.edu","1294885501");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Spaight","North",10,"nspaight15@bravesites.com","1378462961");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Ghilardi","Lyman",3,"lghilardi16@imdb.com","4699953540");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Pirnie","Rhianon",3,"rpirnie17@amazon.co.uk","4095601442");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Bedle","Clemens",9,"cbedle18@amazonaws.com","1078083623");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Drieu","Aurie",8,"adrieu19@mediafire.com","4728766768");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Schaffler","Nanete",10,"nschaffler1a@mail.ru","4963297774");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Weddell","Hedy",10,"hweddell1b@tripadvisor.com","4132686880");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Hartland","Gisela",1,"ghartland1c@cnbc.com","3306974329");
INSERT INTO `formateur`(`Nom`, `Prenom`, `Domaine`, `Email`, `Num_telephone`) VALUES ("Masurel","Brenna",6,"bmasurel1d@hud.gov","2944891088");



INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (1, 'BEN Soltane', 'ACHRAF', 1, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (2, 'Ammar', 'Mohamed', 1, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (3, 'Zayet', 'Zeineb', 5, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (4, 'Escobar', 'Ryan', 9, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (5, 'Valentine', 'Miguel', 1, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (6, 'Mitchell', 'Vince', 4, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (7, 'Huffman', 'Vaughn', 1, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (8, 'Mcintosh', 'Paul', 3, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (9, 'Jones', 'Carey', 1, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (10, 'Farrell', 'Francine', 2, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (11, 'Davenport', 'Adela', 1, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (12, 'Walton', 'Molly', 7, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (13, 'Walls', 'Donna', 1, '1995-01-01');
INSERT INTO `participant`(`Matricule_participant`, `Nom`, `Prenom`, `Profil`, `Date_naissance`) VALUES (14, 'Anthony', 'Christina', 6, '1995-01-01');


INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (1, 'HTML and CSS', 1, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (2, 'Python', 2, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (3, 'Java', 3, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (4, 'JavaScript', 4, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (5, 'Swift', 5, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (6, 'C++', 6, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (7, 'C#', 7, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (8, 'R', 8, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (9, 'Golang (Go)', 9, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (10, 'PHP', 10, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (11, 'Ruby', 11, 30, 2018, 1, 1, 10);
INSERT INTO `formation`(`Code_formation`, `Intitulé`, `Domaine`, `Nombre_jours`, `Annee`, `mois`, `Formateur`, `Nombre_participants`) VALUES (12, 'C', 12, 30, 2018, 1, 1, 10);
