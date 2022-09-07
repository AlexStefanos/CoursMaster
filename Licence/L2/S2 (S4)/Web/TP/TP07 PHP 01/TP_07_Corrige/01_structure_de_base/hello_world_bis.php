<?php

// Chargement du modèle dans une variable.
$page = file_get_contents("template.html");

// Si la page a bien été chargée correctement.
if ($page !== false)
{
	// Remplace le titre de la page.
	$page = str_replace("(TITLE)", "Hello World", $page);

	// Remplace le corps de la page.
	$page = str_replace("(BODY)", "<p>Hello World</p>", $page);

	// Affichage.
	echo $page;
}
