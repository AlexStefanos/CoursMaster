<?php

// Affiche les clés et les valeurs du tableau $_GET[].
foreach ($_GET as $key => $value)
	$body .= "\$_GET['".$key."'] = ".$value."<br>\n";

// Chargement du modèle et affichage.
$page = file_get_contents("template.html");
if ($page !== false)
{
	$page = str_replace("(TITLE)", "GET", $page);
	$page = str_replace("(BODY)", $body, $page);
	echo $page;
}
