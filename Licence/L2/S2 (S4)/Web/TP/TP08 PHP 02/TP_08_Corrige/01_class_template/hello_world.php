<?php

// Exemple de code qui génère les parties à afficher.
$title = "Hello World";
$body = "<p>Hello World</p>";

// Gestion du modèle (chargement, remplacement et affichage).
// ---------------------------------------------------------------------

// Chargement du modèle dans une variable.
$page = file_get_contents("template.html");

// Si la page a bien été chargée correctement.
if ($page !== false)
{
	// Remplace les parties à afficher.
	$page = str_replace("(TITLE)", $title, $page);
	$page = str_replace("(BODY)", $body, $page);

	// Affichage.
	echo $page;
}
