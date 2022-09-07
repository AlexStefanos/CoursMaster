<?php

// Insertion de la définition de la classe Template.
require_once "Template.class.php";

// Si le champ "civilite" n'existe pas, alors le formulaire n'a pas encore été rempli.
// On affiche alors le formulaire.
if (isset($_POST["civilite"]) === false)
	DisplayForm();

// Si le champ "civilite" existe, alors le formulaire a été rempli.
// On affiche une page de validation.
else
	DisplayValidation();

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
	$page = new Template("validation.html");
	
	$page->Replace("(CIVILITE)", $_POST["civilite"]);
	$page->Replace("(PRENOM)", $_POST["prenom"]);
	$page->Replace("(NOM)", $_POST["nom"]);
	$page->Replace("(EMAIL)", $_POST["email"]);
	$page->Replace("(NIVEAU)", $_POST["niveau"]);
	$page->Replace("(COMPETENCE)", implode(", ", $_POST["competence"]));
	$page->Replace("(AUTRE_COMPETENCE)", nl2br(htmlspecialchars($_POST["autre_competence"])));
	
	$page->Display();
}
