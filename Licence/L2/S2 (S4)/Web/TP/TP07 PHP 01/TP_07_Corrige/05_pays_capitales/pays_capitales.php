<?php
// Définit les variables qui serviront au tri en fonction des paramètres
// passés par l'URL.
// $sort = Définit la colonne du tri.
//       = "country" : Le tri se fera par pays.
//       = "city"    : Le tri se fera par capitales.
// $order = Définit le sens du tri.
//        = "normal"  : Le tri se fera par ordre alphabétique.
//        = "reverse" : Le tri se fera par ordre alphabétique inverse.
// ---------------------------------------------------------------------

// Si la colonne du tri a été passée par l'URL, on l'assigne à la
// variable $sort.
// Sinon, on donne à cette variable une valeur par défaut.
if (isset($_GET['sort']))
	$sort = $_GET['sort'];
else
	$sort = "country";

// Si le sens du tri a été passé par l'URL, on l'assigne à la
// variable $order.
// Sinon, on donne à cette variable une valeur par défaut.
if (isset($_GET['order']))
	$order = $_GET['order'];
else
	$order = "normal";

// ---------------------------------------------------------------------

// Création du tableau associatif qui contient les pays et les capitales.
$table = array(
"Algérie" => "Alger",
"Pays-Bas" => "Amsterdam",
"Irak" => "Bagdad",
"Mali" => "Bamako",
"Thaïlande" => "Bangkok",
"Serbie" => "Belgrade",
"Allemagne" => "Berlin",
"Liban" => "Beyrouth",
"Colombie" => "Bogota",
"Brésil" => "Brasilia",
"Slovaquie" => "Bratislava",
"Belgique" => "Bruxelles",
"Roumanie" => "Bucarest",
"Hongrie" => "Budapest",
"Argentine" => "Buenos Aires",
"Danemark" => "Copenhague",
"Sénégal" => "Dakar",
"Irlande" => "Dublin",
"Ukraine" => "Kiev",
"Pérou" => "Lima",
"Portugal" => "Lisbonne",
"Royaume-Uni" => "Londres",
"Espagne" => "Madrid",
"Mexique" => "Mexico",
"Inde" => "New Delhi",
"Norvège" => "Oslo",
"France" => "Paris",
"Italie" => "Rome",
"Chili" => "Santiago",
"Libye" => "Tripoli",
"Tunisie" => "Tunis",
);

//  Si le tri se fait par pays.
if ($sort === "country")
{
	// Si l'ordre du tri est l'ordre alphabétique.
	if ($order === "normal")
	{
		// Tri le tableau par clé (le pays est la clé).
		ksort($table);
		
		// Création des liens qui s'affichent dans l'en-tête du tableau.
		$country_link = "<a href=\"".$_SERVER['PHP_SELF']."?sort=country&order=reverse\">Pays &blacktriangle;</a>";
		$city_link = "<a href=\"".$_SERVER['PHP_SELF']."?sort=city&order=normal\">Capitale</a>";
	}
	
	// Si l'ordre du tri est l'ordre alphabétique inverse.
	else if ($order === "reverse")
	{
		// Tri le tableau par clé en order inverse (le pays est la clé).
		krsort($table);

		// Création des liens qui s'affichent dans l'en-tête du tableau.
		$country_link = "<a href=\"".$_SERVER['PHP_SELF']."?sort=country&order=normal\">Pays &blacktriangledown;</a>";
		$city_link = "<a href=\"".$_SERVER['PHP_SELF']."?sort=city&order=normal\">Capitale</a>";
	}
}
	
//  Si le tri se fait par capitales.
else if ($sort === "city")
{
	// Si l'ordre du tri est l'ordre alphabétique.
	if ($order === "normal")
	{
		// Tri le tableau par valeur (la capitale est la valeur).
		asort($table);

		// Création des liens qui s'affichent dans l'en-tête du tableau.
		$country_link = "<a href=\"".$_SERVER['PHP_SELF']."?sort=country&order=normal\">Pays</a>";
		$city_link = "<a href=\"".$_SERVER['PHP_SELF']."?sort=city&order=reverse\">Capitale &blacktriangle;</a>";
	}
	
	// Si l'ordre du tri est l'ordre alphabétique inverse.
	else if ($order === "reverse")
	{
		// Tri le tableau par valeur en order inverse (la capitale est la valeur).
		arsort($table);

		// Création des liens qui s'affichent dans l'en-tête du tableau.
		$country_link = "<a href=\"".$_SERVER['PHP_SELF']."?sort=country&order=normal\">Pays</a>";
		$city_link = "<a href=\"".$_SERVER['PHP_SELF']."?sort=city&order=normal\">Capitale &blacktriangledown;</a>";
	}
}

// Création de la page HTML.
// ---------------------------------------------------------------------

$html_table  = "<table>\n";

$html_table .= "<tr>\n";
$html_table .= "    <th>".$country_link."</th>\n";
$html_table .= "    <th>".$city_link."</th>\n";
$html_table .= "</tr>\n";

foreach ($table as $country => $city)
{
	$html_table .= "<tr>\n";
	$html_table .= "    <td>".$country."</td>\n";
	$html_table .= "    <td>".$city."</td>\n";
	$html_table .= "</tr>\n";
}

$html_table .= "</table>";

// Affichage.
// ---------------------------------------------------------------------

$page = file_get_contents("pays_capitales.html");
if ($page !== false)
	echo str_replace("(TABLE)", $html_table, $page);
