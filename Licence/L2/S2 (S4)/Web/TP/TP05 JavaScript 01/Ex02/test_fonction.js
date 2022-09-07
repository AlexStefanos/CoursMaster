function surface(var longueur, var largeur) {
  return longeur * largeur;
}

function onSurface() {
  var longueur = prompt("Saisissew la longueur : ");
  var largeur = prompt("Saisissew la largeur : ");

  var s = surface(longueur, largeur);

  alert("La surface est : " + s);
}

function onValidation() {
  var isValid = confirm("Souhaitez-vous valider cette option ?");

  document.open();

  if (isValid)
    document.write("L'option est validée");
  else
    document.write("L'option n'est pas validée");

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
