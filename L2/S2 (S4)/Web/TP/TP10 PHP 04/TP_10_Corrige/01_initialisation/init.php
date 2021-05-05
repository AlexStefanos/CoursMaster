<?php

// Connexion à la base de données.
try
{
	$pdo = new PDO('pgsql:host=opale.ens.math-info.univ-paris5.fr;dbname=BD<login>', '<login>', '<password>');
}

// Gestion de l'erreur de connexion.
catch (Exception $e)
{
	exit($e->getMessage());
}

// Suppression de la table Contact si elle existe.
$result = $pdo->exec("DROP TABLE IF EXISTS contact");
if ($result === false)
{
	print_r($pdo->errorInfo());
	exit;
}

// Création de la table Contact.
$query = "CREATE TABLE contact
			(
				id SERIAL PRIMARY KEY,
				login VARCHAR(20) NOT NULL UNIQUE,
				password CHAR(32) NOT NULL,
				first_name VARCHAR(20) NOT NULL,
				last_name  VARCHAR(20) NOT NULL,
				age SMALLINT NOT NULL,
				email VARCHAR(64) NOT NULL UNIQUE,
				admin SMALLINT NOT NULL
			)";

$result = $pdo->exec($query);
if ($result === false)
{
	print_r($pdo->errorInfo());
	exit;
}

// Création de quelques entrées de la table Contact.
InsertContact("rene", "rene", "Rene", "Descartes", 30, "rene.descartes@gm.com", 1);
InsertContact("jim", "jim", "Jimmy", "Hendrix", 25, "jimmy.hendrix@gm.com");
InsertContact("fossi", "fossi", "Roger", "Fossi", 45, "roger.fossi@gm.com");
InsertContact("slash", "slash", "John", "Roger", 18, "john.roger@gm.com");
InsertContact("marc2", "marc2", "Marc", "Letonne", 23, "marc.letonne@yh.com");
InsertContact("maud", "maud", "Maude", "Labrue", 45, "sophie.labrue@gm.com");
InsertContact("roger", "roger", "Roger", "Dupont", 45, "roger.dupont@yh.com");
InsertContact("rog3", "rog3", "Roger", "Bertina", 32, "roger.bertin@ht.com");
InsertContact("mano", "mano", "Manu", "Raille", 64, "manu.raille@yh.com");
InsertContact("marco", "marco", "Marc", "Meunier", 12, "marc.meunier@gm.com");
InsertContact("soso", "soso", "Sophie", "Bonaud", 45, "sophie.bonaud@gm.com");
InsertContact("rog2", "rog2", "Roger", "Microbi", 45, "roger.micorbi@ht.com");
InsertContact("marcus", "marcus", "Marc", "Mineur", 55, "marc.mineur@gm.com");
InsertContact("maude", "maude", "Maude", "Suie", 30, "sophie.suie@gm.com");
InsertContact("roger3", "roger3", "Roger", "Fossi", 15, "roger3@gm.com");
InsertContact("rog", "rog", "Roger", "Raille", 43, "roger.raille@gm.com");
InsertContact("marc", "marc", "Marc", "Laloie", 29, "marc.laloie@ht.com");
InsertContact("sophie", "sophie", "Sophie", "Berthier", 30, "sophie.berthier@gm.com");
InsertContact("saufy", "saufy", "Sophie", "Juste", 22, "sophie.juste@gm.com");
InsertContact("maumau", "maumau", "Maude", "Artol", 22, "sophie.artol@gm.com");
InsertContact("kant", "kant", "Emmanuel", "Kant", 30, "emmanuel.kant@gm.com", 1);

// Affichage.
echo "Initialisation : OK";

// *********************************************************************
// InsertContact()
// ---------------------------------------------------------------------
// Création d'une entrée de la table Contact.
// ---------------------------------------------------------------------
// Entrées : $login = Login du contact.
//           $password = Mot de passe du contact.
//           $first_name = Prénom du contact.
//           $last_name = Nom du contact.
//           $age = Âge du contact.
//           $email = Adresse e-mail du contact.
// (facul)   $admin = 0 pour un utilisateur (valeur par défaut).
//                  = 1 pour un administrateur.
// *********************************************************************
function InsertContact($login, $password, $first_name, $last_name, $age, $email, $admin = 0)
{
	global $pdo;
	
	$result = $pdo->exec("INSERT INTO contact VALUES
							(DEFAULT,
							'".$login."',
							'".md5($password)."',
							'".$first_name."',
							'".$last_name."',
							".$age.",
							'".$email."',
							".$admin.")"
						);
	
	if ($result === false)
	{
		print_r($pdo->errorInfo());
		exit;
	}
}
