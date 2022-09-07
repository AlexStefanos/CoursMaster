// Nombre de lignes.
var line_number = 10;

// *********************************************************************
// Initialisation de la feuille de calcul.
// *********************************************************************
function init()
{
	// Récupère tous les champs textuels de type "number".
	var inputs = document.querySelectorAll("input[type='number']");
	
	// On ajoute à un événement onChange à tous les champs textuels de typs "number".
	// Quand la valeur d'un de ces champs change, la fonction calculeTotal() est appelée.
	for (var i = 0; i < inputs.length; i++)
		inputs[i].addEventListener("change", calculeTotal, false);

	// Lance le calcul du total.
	// (Les sommes seront positinnées à zéro.)
	calculeTotal();
}

// *********************************************************************
// Effectue le calcul du prix pour chaque ligne ainsi que le total.
// L'affichage de tous les champs est mis à jour.
// *********************************************************************
function calculeTotal()
{
	// Déclare une variable qui contiendra le total de la somme.
	var total_value = 0;
	
	// Calcul le prix et met à jour l'affichage pour toutes les lignes.
	// Le prix d'une ligne est ajouté au total.
	for (var i = 0; i < line_number; i++)
		total_value += calculePrix(i);
	
	// Mise à jour de l'affichage du total.
	total.value = total_value;
}

// *********************************************************************
// Effectue le calcul du prix pour une ligne.
// L'affichage du prix de la ligne est mis à jour.
// ---------------------------------------------------------------------
// Entrée : Numéro de la ligne à mettre à jour.
// Sortie : Renvoie le prix de la ligne.
// *********************************************************************
function calculePrix(ligne)
{
	// Récupère les identifiants des champs pour le prix unitaire et la quantité.
	var unitaire = "unitaire_" + ligne;
	var quantite = "quantite_" + ligne;

	// Récupère la valeur entière contenue dans ces champs.
	unitaire_value = parseInt(document.getElementById(unitaire).value, 10);
	quantite_value = parseInt(document.getElementById(quantite).value, 10);

	// Si les valeurs n'ont pas pu être converties correctement, on les met à zéro.
	if (isNaN(unitaire_value))
		unitaire_value = 0;
	if (isNaN(quantite_value))
		quantite_value = 0;
	
	// Calcul du prix.
	var prix = unitaire_value * quantite_value;
	
	// Mise à jour de l'affichage du prix.
	document.getElementById("prix_" + ligne).value = prix;
	
	// Renvoie le prix.
	return prix;
}

// *********************************************************************
// Ajoute une ligne au tableau.
// *********************************************************************
function ajouteLigne()
{
	// Création d'un nouvel élément <tr>.
	var tr = document.createElement("tr");
	
	// Ajoute à l'élément <tr> tous les éléments <td>.
	// Le nombre de ligne total est également incrémenté.
	tr.innerHTML = getTd(line_number++);

	// Récupère le dernier élément <tr> du tableau.
	// (Il s'agit de la ligne affichant le total.)
	var tr_lignes = document.getElementsByTagName("tr");
	var last_tr = tr_lignes[tr_lignes.length - 1];
	
	// Insère la nouvelle ligne juste avant le total.
	last_tr.parentNode.insertBefore(tr, last_tr);

	// Actualise l'affichage des prix et du total.
	calculeTotal();
}

// *********************************************************************
// Génère les balises <td> d'une ligne à ajouter.
// ---------------------------------------------------------------------
// Entrée : Numéro de la ligne à ajouter.
// Sortie : Renvoie les balises <td> de la ligne.
// *********************************************************************
function getTd(ligne)
{
	var td;
	
	td  = '<td><input type="text" name="article_' + ligne + '" id="article_' + ligne + '"></td>';
	td += '<td><input onChange="calculeTotal();" type="number" name="unitaire_' + ligne + '" id="unitaire_' + ligne + '"></td>';
	td += '<td><input onChange="calculeTotal();" type="number" name="quantite_' + ligne + '" id="quantite_' + ligne + '"></td>';
	td += '<td><input type="text" name="prix_' + ligne + '" id="prix_' + ligne + '" readonly></td>';
	
	return td;
}
