function surface(longueur, largeur)
{
	return longueur * largeur;
}

function onSurface()
{
	// Saisie de la longueur et de la largeur.
	var longueur = prompt("Saisissez la longueur : ");
	var largeur = prompt("Saisissez la largeur : ");

	// Calcul de la surface.
	var s = surface(longueur, largeur);

	// Affichage de la surface.
	alert("La surface est : " + s);
}

function onValidation()
{
	// Demande à l'utilisateur de valider l'option.
	var isValid = confirm("Souhaitez-vous valider cette option ?");

	// Ouverture du document.
	document.open();

	// Si l'utilisateur a validé l'option, on affiche un message de validation.
	if (isValid)
		document.write("L'option est validée");

	// Sinon, on affiche un message de non validation.
	else
		document.write("L'option n'est pas validée");

	// Fermeture du document.
	document.close();
}

function onTable()
{
	// Saisie de la table à afficher.
	var table_number = prompt("Quelle table souhaitez-vous afficher ?");

	// Ouverture du document.
	document.open();

	// Affiche la table.
	document.write(table(table_number));

	// Fermeture du document.
	document.close();
}

function table(number)
{
	// Variable qui contiendra le résultat.
	var result;

	// Écrit la balise ouvrante de l'élément "table".
	result  = '<table border="1">\n';

	// Ajoute la ligne de titre.
	result += '    <tr>\n';
	result += '        <th colspan="2">Table des ' + number + '</th>\n';
	result += '    </tr>\n';

	// Ajoute les lignes de la table de multiplication.
	for (var i = 1; i <= 10; i++)
	{
		result += '    <tr>\n';
		result += '        <td>' + i + ' x ' + number + '</td>\n';
		result += '        <td>' + (i * number) + '</td>\n';
		result += '    </tr>\n';
	}

	// Termine avec la balise fermante de l'élément "table".
	result += "</table>";

	// Renvoie le tableau HTML de la table de multiplication.
	return result;
}
