<?php

// Définition de la classe Template.
require_once "Template.class.php";

// Si le formulaire n'a pas été soumis correctement.
// On stoppe le script immédiatement.
if (isset($_POST['article_0']) === false)
	exit("Erreur");

// Initialise le numéro de ligne à zéro.
$line = 0;

// Initialise une chaîne de caractères qui contiendra le fichier CSV.
// La première ligne du fichier doit contenir le titre des champs.
$csv = "Article;Prix Unitaire;Quantité;Prix\n";

// Tant qu'une ligne du formulaire existe.
while (isset($_POST['article_'.$line]) === true)
{
	// Ajoute les valeurs de chaque champ dans la variable.
	// Chaque champ est séparé par un point virgule.
	// On ajoute un saut de ligne après le dernier champ.
	$csv .= $_POST['article_'.$line].";";
	$csv .= $_POST['unitaire_'.$line].";";
	$csv .= $_POST['quantite_'.$line].";";
	$csv .= $_POST['prix_'.$line]."\n";

	// Passage à la ligne suivante.
	$line++;
}

// Ajoute le champ total.
$csv .= ";;Total;".$_POST['total']."\n";

// Création du fichier CSV.
file_put_contents("feuille_calcul.csv", $csv);

// Affichage.
$page = new Template("template.html");
$page->Replace("(FILE_PATH)", "feuille_calcul.csv");
$page->Replace("(FILE_NAME)", "feuille_calcul.csv");
$page->Display();
