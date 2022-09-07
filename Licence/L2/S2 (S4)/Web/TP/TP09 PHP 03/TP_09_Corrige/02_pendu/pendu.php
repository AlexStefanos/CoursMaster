<?php

// Définition de la classe Template.
require_once "Template.class.php";

// Ouverture de la session.
session_start();

// Si aucune lettre n'est passée par la méthode POST.
if ((isset($_POST['letter']) === false))
{
	// Et que le mot à chercher n'est pas non plus passé par la méthode POST,
	// on affiche la page de saisie du mot.
	if (isset($_POST['word']) === false)
		exit(file_get_contents("get_word.html"));
		
	// Le mot à chercher est passé par la méthode POST.
	// Il s'agit donc d'une nouvelle session qui commence.
	// Il faut l'initialiser, c'est-à-dire créer les variables de session.
	else
	{
		// Avant d'initialiser les variables de session,
		// on vérifie que le mot à chercher ne contient que des lettres.
		// Si ce n'est pas le cas, on réaffiche la page de saisie du mot.
		if (ctype_alpha($_POST['word']) === false)
			exit(file_get_contents("get_word.html"));
		
		// Sauvegarde dans la session le mot à chercher sous sa forme capitale.
		$_SESSION['word'] = strtoupper($_POST['word']);
		
		// Création d'un tableau qui contiendra les lettres déjà proposées.
		// Pour le moment, aucune lettre n'a été proposée.
		// Le tableau est donc vide.
		$_SESSION['letters'] = array();
		
		// Compteur de lettres erronées.
		// Ce compteur s'incrémentera uniquement si une lettre proposée
		// n'appartient pas au mot à chercher.
		$_SESSION['count'] = 0;
	}
	
	// On place une chaîne vide dans la variable qui contiendra la lettre proposée.
	$letter = "";
}

// Une lettre a été passé par la méthode POST.
// Cela signifie que la session a déjà commencé.
// On stocke simplement la lettre sous sa forme capitale dans une variable.
else
	$letter = strtoupper($_POST['letter']);

// Récupère les variables de session.
$word = $_SESSION['word'];
$letters = $_SESSION['letters'];
$count = $_SESSION['count'];

// Si la lettre appartient à l'alphabet.
if (ctype_alpha($letter) === true)
{
	// Et si la lettre n'a pas déjà été saisie.
	if (in_array($letter, $letters) === false)
	{
		// On l'ajoute au tableau des lettres déjà saisies.
		// À partir de la version 5.4 de PHP, l'instruction
		// suivante est préférable : $letters[] = $letter;
		array_push($letters, $letter);
		
		// Si la lettre n'appartient pas au mot,
		// on incrémente le compteur de mauvaises réponses.
		if (strpos($word, $letter) === false)
			$count++;
	}
}

// Génère la chaîne qui servira à l'affichage du mot.
// Les lettres inconnues sont remplacées par des tirets.
$dashed_word = GetDashedWord($word, $letters);

// Si le joueur a atteint le nombre maximal d'essais, ou s'il a gagné.
if ($count === 9 || $dashed_word === $word)
{
	// Le mot complet sera affiché.
	$full_word = $word;
	
	// La saisie d'une lettre sera désactivée.
	$disabled = "disabled";
}

// Sinon, le joueur n'a pas encore trouvé le mot et il lui reste des essais.
else
{
	// Le mot complet ne sera pas affiché.
	// Il est préférable d'afficher un espace insécable plutôt qu'une
	// chaîne vide pour des raisons de mise en page.
	$full_word = "&nbsp";
	
	// La saisie d'une lettre restera activée.
	$disabled = "";
}

// Affichage.	
$page = new Template("get_letter.html");
$page->Replace("(DASHED_WORD)", $dashed_word);
$page->Replace("(COUNT)", $count);
$page->Replace("(FULL_WORD)", $full_word);
$page->Replace("(LETTERS)", implode(", ", $letters));
$page->Replace("(DISABLED)", $disabled);
$page->Display();

// Sauvegarde les variables modifiées dans la session.
// Cela permettra de les récupérer, lors de la prochaine exécution du script.
$_SESSION['letters'] = $letters;
$_SESSION['count'] = $count;

// *********************************************************************
// GetDashedWord()
// ---------------------------------------------------------------------
// Génère une chaîne de caractères qui remplace les lettres du mot $word
// par des tirets si ces lettres ne sont pas présentent dans
// le tableau $letters.
// ---------------------------------------------------------------------
// Entrées : $word    = Mot complet (sans espace ni tiret).
//                      Ce mot doit contenir uniquement des caractères
//                      de l'alphabet.
//           $letters = Tableau contenant des lettres de l'alphabet.
//
// Sortie  : Renvoie une chaîne de caractères où les lettres du mot
//           $word sont remplacées par des tirets si ces lettres ne
//           sont pas présentes dans le tableau $letters.
// *********************************************************************
function GetDashedWord($word, $letters)
{
	// Initialise la chaîne de retour.
	$dashed_word = "";
	
	// Récupère toutes les lettres du mot dans un tableau de caractères.
	$chars = str_split($word);
	
	// Pour chaque lettre du mot.
	foreach ($chars as $c)
	{
		// Si le caractère est présent dans le mot,
		// on l'ajoute à la chaîne de retour.
		// Sinon, on ajoute un tiret.
		if (in_array($c, $letters))
			$dashed_word .= $c;
		else
			$dashed_word .= "-";
	}
	
	// Renvoie la chaîne de retour.
	return $dashed_word;
}
