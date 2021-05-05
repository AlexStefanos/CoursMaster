<?php

// Définition de la classe Template.
require_once "Template.class.php";

// Ouverture de la session.
session_start();

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

// Si le login et le mot de passe n'ont pas été envoyé par la méthode POST.
// Cela signifie que la page a été lancée directement.
if (!isset($_POST['login']) || !isset($_POST['password']))
{
	// On affiche la page sans message d'erreur.
	$page = new Template("login.template.html");
	$page->Replace("(ERROR)", "");
	$page->Display();
}

// Si le login et le mot de passe ont été envoyé par la méthode POST.
// Cela signifie que la page a été lancée à partir du formulaire.
else
{
	// Lance une requête avec le login et le mot de passe.
	// (Le mot de passe est directement codé par une fonction MD5 du serveur de la base de données.)
	$result = $pdo->prepare("SELECT * FROM contact WHERE login = :login AND password = MD5(:password)");
	$result->execute($_POST);
	
	// Si le résultat contient un contact.
	// (Une seule ligne est disponible, car un login est unique.)
	// (Il ne peut pas y avoir deux contacts avec le même login.)
	if ($row = $result->fetch(PDO::FETCH_OBJ))
	{
		// On sauvegarde quelques informations dans la session.
		// Ces informations sont nécessaires aux pages d'acceuil,
		// pour que leurs accès soient autorisés.
		$_SESSION['login'] = $row->login;
		$_SESSION['first_name'] = $row->first_name;
		$_SESSION['last_name'] = $row->last_name;
		$_SESSION['admin'] = $row->admin;
		
		// Si le contact est un administrateur,
		// on redirige vers la page d'accueil administrateur.
		if ($row->admin == 1)
			header("location: admin.php");

		// Sinon, le contact est un utilisateur.
		// On redirige vers la page d'acceuil utilisateur.
		else
			header("location: user.php");
	}
	
	// Sinon le résultat ne contient pas de contact.
	else
	{
		$page = new Template("login.template.html");
		$page->Replace("(ERROR)", "Le login ou le mot de passe est incorrect");
		$page->Display();
	}
}
