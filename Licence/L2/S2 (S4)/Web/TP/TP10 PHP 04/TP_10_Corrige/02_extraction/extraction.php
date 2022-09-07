<?php

// Définition de la classe Template.
require_once "Template.class.php";

// Connexion à la base de données.
try
{
	$pdo = new PDO('pgsql:host=opale.ens.math-info.univ-paris5.fr;dbname=BD<login>', '<login>', '<password>');
}

// Gestion de l'erreur de connexion.
catch (Exception $e)
{
	exit($e->getMessage());
}

// Requêtes et générations des tableaux HTML.
// ---------------------------------------------------------------------

$result = $pdo->query("SELECT * FROM contact");
$html_table = GetHtmlTable("Table complète", $result);

$result = $pdo->query("SELECT * FROM contact WHERE admin = 1");
$html_table .= GetHtmlTable("Administrateur", $result);

$result = $pdo->query("SELECT * FROM contact WHERE age < 30");
$html_table .= GetHtmlTable("Âge inférieur à 30 ans", $result);

$result = $pdo->query("SELECT * FROM contact WHERE age >= 30 AND age < 40");
$html_table .= GetHtmlTable("Âge entre 30 ans (inclus) et 40 ans (exclus)", $result);

$result = $pdo->query("SELECT * FROM contact WHERE age >= 40");
$html_table .= GetHtmlTable("Âge supérieur ou égal à 40 ans", $result);

$result = $pdo->query("SELECT * FROM contact WHERE email LIKE '%yh.com'");
$html_table .= GetHtmlTable("Adresse de type 'yh.com'", $result);

$result = $pdo->query("SELECT * FROM contact WHERE first_name='Roger'");
$html_table .= GetHtmlTable("Tous les Roger", $result);

$result = $pdo->query("SELECT * FROM contact WHERE first_name='Roger' and age = 45");
$html_table .= GetHtmlTable("Tous les Roger de 45 ans", $result);

$result = $pdo->query("SELECT * FROM contact ORDER BY last_name");
$html_table .= GetHtmlTable("Ordre alphabétique sur le nom", $result);

$result = $pdo->query("SELECT * FROM contact ORDER BY age");
$html_table .= GetHtmlTable("Du plus jeune au plus vieux", $result);

$result = $pdo->query("SELECT * FROM contact ORDER BY age DESC");
$html_table .= GetHtmlTable("Du plus vieux au plus jeune", $result);

// ---------------------------------------------------------------------

// Affichage.
$page = new Template("extraction.template.html");
$page->Replace("(TABLE)", $html_table);
$page->Display();

// *********************************************************************
// GetHtmlTable()
// ---------------------------------------------------------------------
// Génère un tableau HTML à partir du résultat d'une requête.
// ---------------------------------------------------------------------
// Entrées : $caption = Titre du tableau.
//           &$result = Résultat de la requête.
// *********************************************************************
function GetHtmlTable($caption, &$result)
{
	$table  = "<table>\n";
	
	$table .= "<caption>".$caption."</caption>\n";

	$table .= "<tr>\n";
	$table .= "    <th>ID</th>\n";
	$table .= "    <th>Login</th>\n";
	$table .= "    <th>Prénom</th>\n";
	$table .= "    <th>Nom</th>\n";
	$table .= "    <th>Âge</th>\n";
	$table .= "    <th>E-Mail</th>\n";
	$table .= "    <th>Admin</th>\n";
	$table .= "</tr>\n";
	
	while ($row = $result->fetch(PDO::FETCH_OBJ))
	{
		if ($row->admin == 1)
			$admin = "oui";
		else
			$admin = "non";
		
		$table .= "<tr>\n";
		$table .= "    <td>".$row->id."</td>\n";
		$table .= "    <td>".$row->login."</td>\n";
		$table .= "    <td>".$row->first_name."</td>\n";
		$table .= "    <td>".$row->last_name."</td>\n";
		$table .= "    <td>".$row->age."</td>\n";
		$table .= "    <td>".$row->email."</td>\n";
		$table .= "    <td>".$admin."</td>\n";
		$table .= "</tr>\n";
	}

	$table .= "</table>\n";
	
	return $table;
}
