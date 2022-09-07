<?php

// Définition de la classe Template.
require_once "Template.class.php";

// Ouverture de la session.
session_start();

// Si l'une des variables n'est pas présente dans la session,
// l'accès n'est pas autorisé.
if (!isset($_SESSION['admin']) ||
	!isset($_SESSION['login']) ||
	!isset($_SESSION['first_name']) ||
	!isset($_SESSION['last_name']))
{
	AccessDenied();
}

// Si le contact n'est pas un utilisateur, l'accès est refusé.
if ($_SESSION['admin'] != 0)
	AccessDenied();

// ---------------------------------------------------------------------
// À ce stade du script, l'accès est autorisé.
// ---------------------------------------------------------------------

// Affichage de la page d'accueil d'un utilisateur.
$page = new Template("user.template.html");
$page->Replace("(LOGIN)", $_SESSION['login']);
$page->Replace("(FIRST_NAME)", $_SESSION['first_name']);
$page->Replace("(LAST_NAME)", $_SESSION['last_name']);
$page->Display();

// *********************************************************************
// AccessDenied()
// ---------------------------------------------------------------------
// Affiche une page qui contient un message d'accès refusé.
// Le script est alors stoppé.
// *********************************************************************
function AccessDenied()
{
	exit("Access Denied");
}
