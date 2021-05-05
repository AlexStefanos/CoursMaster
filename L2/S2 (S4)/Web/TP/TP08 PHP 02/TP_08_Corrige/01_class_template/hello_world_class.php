<?php

// Insertion de la définition de la classe Template.
require_once "Template.class.php";

// Exemple de code qui génère les parties à afficher.
$title = "Hello World";
$body = "<p>Hello World</p>";

// Gestion du modèle.
// ---------------------------------------------------------------------

$page = new Template("template.html");
$page->Replace("(TITLE)", $title);
$page->Replace("(BODY)", $body);
$page->Display();
