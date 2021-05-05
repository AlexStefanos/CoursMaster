<?php

// Définit une constante qui contient le nombre de tables de multiplication possibles.
define("TABLE_MAX", 20);

// Si le numéro de la table n'est pas passé par l'URL,
// on crée une suite de liens qui pointeront vers cette même page
// en passant le numéro de la table dans l'URL.
if (isset($_GET['table']) === false)
{
	for ($i = 1; $i <= TABLE_MAX; $i++)
		$body .= "<a href=\"".$_SERVER['PHP_SELF']."?table=".$i."\">Table des ".$i."</a><br>\n";
}

// Si le numéro de la table de multiplication est passé par l'URL,
// on crée un tableau HTML qui contiendra la table de multiplication.
else
{
	$body  = "<table border=\"1\">\n";
	
	$body .= "<tr>\n";
	$body .= "    <th colspan=\"2\">Table des ".$_GET['table']."</th>\n";
	$body .= "</tr>\n";

	for ($i = 1; $i <= 10; $i++)
	{
		$body .= "<tr>\n";
		$body .= "    <td>".$i." x ".$_GET['table']."</td>\n";
		$body .= "    <td>".($i * $_GET['table'])."</td>\n";
		$body .= "</tr>\n";
	}

	$body .= "</table>";
}

// Chargement du modèle et affichage.
$page = file_get_contents("template.html");
if ($page !== false)
{
	$page = str_replace("(TITLE)", "Tables de mutiplication", $page);
	$page = str_replace("(BODY)", $body, $page);
	echo $page;
}
