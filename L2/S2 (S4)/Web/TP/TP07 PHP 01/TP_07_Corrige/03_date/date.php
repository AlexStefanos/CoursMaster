<?php

// Création d'un tableau pour les jours de la semaine.
$day = array("dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi");

// Création d'un tableau pour les mois.
$month = array("janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre");

// Génération de la date du jour.
$date = $day[date("w")]." ".date("j")." ".$month[date("n") - 1]." ".date("Y");

// Chargement du modèle et affichage.
$page = file_get_contents("date.html");
if ($page !== false)
{
	$page = str_replace("(DATE)", $date, $page);
	echo $page;
}
