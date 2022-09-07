<?php

// Insertion de la définition de la classe Template.
require_once "Template.class.php";

// Si les informations sur le fichier exitent,
// on génère un message d'erreur.
if (isset($_FILES['file']))
{
	// Place les informations du fichier dans une variable.
	$file = $_FILES['file'];

	// Si une erreur est apparue lors du téléchargement.
	// on génère un message d'erreur.
	if ($file['error'] !== UPLOAD_ERR_OK)
		$message = "Une erreur est apparue lors du téléchargement (".$file['error'].").";

	// Si le fichier est trop volumineux,
	// on génère un message d'erreur.
	else if ($file['size'] > 1048576)
		$message = "Le fichier est trop volumineux.";

	// Si le type du fichier n'est pas correct,
	// on génère un message d'erreur.
	else if ((($file['type'] === "application/pdf") || (stripos($file['type'], "image") === 0)) === false)
		$message = "Le type du fichier n'est pas valide (PDF ou image uniquement).";

	// On déplace le fichier dans le répertoire "upload".
	// on génère un message d'erreur.
	else if (move_uploaded_file($file['tmp_name'], 'upload/'. $file['name']) === false)
		$message = "Le fichier n'a pas pu être copié sur le serveur.";

	// Aucune erreur ne s'est produite,
	// on génère un message de réussite.
	else
		$message = "Le fichier a été téléchargé.";
}

// Si les informations sur le fichier n'exitent pas.
else
{
	$message = "Aucun fichier n'a été passé en paramètre.";
	$file['name'] = "-----";
	$file['size'] = "-----";
	$file['type'] = "-----";
}

// Affichage.
$page = new Template("template.html");
$page->Replace("(MESSAGE)", $message);
$page->Replace("(NAME)", $file['name']);
$page->Replace("(SIZE)", $file['size']);
$page->Replace("(TYPE)", $file['type']);
$page->Display();
