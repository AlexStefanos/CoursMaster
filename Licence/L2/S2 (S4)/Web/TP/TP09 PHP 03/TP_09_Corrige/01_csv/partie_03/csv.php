<?php

// Définition de la classe Template.
require_once "Template.class.php";

// Si le formulaire n'a pas été soumis correctement.
// On stoppe le script immédiatement.
if (isset($_POST['article_0']) === false)
	exit("Erreur");

// Si le répertoire "csv_file" n'existe pas, on le crée.
if (file_exists("csv_file") === false)
	mkdir("csv_file");
	
// ---------------------------------------------------------------------
$files = scandir("csv_file");

foreach ($files as $f)
{
	$file = "csv_file/".$f;
	
	if (is_file($file) === true)
		if ((time() - filemtime($file)) > 60)
			unlink($file);
}
// ---------------------------------------------------------------------

// Création d'un nom de fichier aléatoire.
// Ce nom ne doit pas déjà exister.
do
{
	// Nom de fichier seul.
	$file_name = "feuille_calcul_".mt_rand().".csv";
	
	// Chemin complet du fichier.
	$file_path = "csv_file/".$file_name;
	
} while (file_exists($file_path) === true);

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
file_put_contents($file_path, $csv);

// Affichage.
$page = new Template("template.html");
$page->Replace("(FILE_PATH)", $file_path);
$page->Replace("(FILE_NAME)", $file_name);
$page->Display();
