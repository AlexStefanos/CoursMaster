<?php

// Insertion de la définition de la classe Template.
require_once "Template.class.php";

// Si le champ "civilite" existe, alors le formulaire a été rempli.
// On affiche une page de validation.
if (isset($_POST["civilite"]) === true)
	DisplayValidation();

// Si la variable a été passée par la méhtode GET, alors on affiche la confirmation.
else if (isset($_GET["valid"]) === true)
	DisplayConfirmation();

// Sinon, on affiche le formulaire.
else
	DisplayForm();

// *********************************************************************
// DisplayForm()
// ---------------------------------------------------------------------
// Affiche le formulaire.
// *********************************************************************
function DisplayForm()
{
	$page = new Template("form.html");
	$page->Display();
}

// *********************************************************************
// DisplayValidation()
// ---------------------------------------------------------------------
// Affiche une page de validation.
// Cette page affiche les valeurs saisies par l'utilisateur.
// *********************************************************************
function DisplayValidation()
{
	// Démarre la session.
	session_start();

	// Sauvegarde les données dans la session.
	$_SESSION["civilite"] = $_POST["civilite"];
	$_SESSION["prenom"] = $_POST["prenom"];
	$_SESSION["nom"] = $_POST["nom"];
	$_SESSION["email"] = $_POST["email"];
	$_SESSION["niveau"] = $_POST["niveau"];
	$_SESSION["competence"] = implode(", ", $_POST["competence"]);
	$_SESSION["autre_competence"] = $_POST["autre_competence"];
	
	// Affichage.
	$page = new Template("validation.html");
	$page->Replace("(CIVILITE)", $_SESSION["civilite"]);
	$page->Replace("(PRENOM)", $_SESSION["prenom"]);
	$page->Replace("(NOM)", $_SESSION["nom"]);
	$page->Replace("(EMAIL)", $_SESSION["email"]);
	$page->Replace("(NIVEAU)", $_SESSION["niveau"]);
	$page->Replace("(COMPETENCE)", $_SESSION["competence"]);
	$page->Replace("(AUTRE_COMPETENCE)", nl2br(htmlspecialchars($_SESSION["autre_competence"])));
	$page->Display();
}

// *********************************************************************
// DisplayConfirmation()
// ---------------------------------------------------------------------
// Affiche une page de confirmation.
// Les données sont sauvegardées dans un fichier texte.
// *********************************************************************
function DisplayConfirmation()
{
	// Démarre la session.
	session_start();

	// Création de la chaîne de sauvegarde.
	$s  = "Civilité : ".$_SESSION["civilite"]."\n";
	$s .= "Prénom : ".$_SESSION["prenom"]."\n";
	$s .= "Nom : ".$_SESSION["nom"]."\n";
	$s .= "E-mail : ".$_SESSION["email"]."\n";
	$s .= "Niveau : ".$_SESSION["niveau"]."\n";
	$s .= "Compétence : ".$_SESSION["competence"]."\n";
	$s .= "Autres compétences :\n".$_SESSION["autre_competence"];
	
	// Termine la session
	session_destroy();
	
	// Sauvegarde du fichier et génération du message.
	if (file_put_contents("data.txt", $s) === false)
		$message = "Vos données n'ont pas pu être sauvegardées sur notre serveur.";
	else
		$message = "Nous vous confirmons que vos données sont sauvegardées sur notre serveur.";
	
	// Affichage.
	$page = new Template("confirmation.html");
	$page->Replace("(MESSAGE)", $message);
	$page->Display();
}
