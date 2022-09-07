function tester_formulaire()
{
	// Création d'une variable qui contiendra l'affichage du résultat.
	var result;
	
	// Ajoute les informations personnelles.
	result  = "Nom : " + monFormulaire.nom.value + "\n";
	result += "Prénom : " + monFormulaire.prenom.value + "\n";
	result += "Civilité : " + monFormulaire.civilite.value + "\n";
	result += "Adresse e-mail : " + monFormulaire.email.value + "\n";
	result += "Mot de passe : " + monFormulaire.mot_de_passe.value + "\n\n";
	
	// Ajoute le niveau.
//	result += "Niveau : " + monFormulaire.niveau.value + "\n";	// Fonctionne sur certains navigateurs.
	for (var i = 0; i < monFormulaire.niveau.length; i++)
		if (monFormulaire.niveau[i].checked)
			result += "Niveau : " + monFormulaire.niveau[i].value + "\n";
	
	// Ajoute les compétences.
	// -----------------------------------------------------------------
	
	// Création d'une variable qui contiendra les compétences.
	var competence = "";
	
	// Ajoute la compétence C si elle est cochée.
	if (monFormulaire.comp_c.checked)
		competence += "C, ";

	// Ajoute la compétence C++ si elle est cochée.
	if (monFormulaire.comp_cpp.checked)
		competence += "C++, ";

	// Ajoute la compétence Java si elle est cochée.
	if (monFormulaire.comp_java.checked)
		competence += "Java, ";
	
	// Ajoute la compétence Caml si elle est cochée.
	if (monFormulaire.comp_caml.checked)
		competence += "Caml, ";
	
	// S'il y a au moins une compétence, on supprime la dernière virgule.
	if (competence.length != 0)
		competence = competence.substr(0, competence.length - 2);

	//  Sinon, aucune compétence n'est cochée, on affichera 'Aucune'.
	else
		competence = "Aucune";

	// Ajoute les compétences.
	result += "Competence : " + competence + "\n\n";
	
	// -----------------------------------------------------------------
	
	// Si d'autres compétences ont été saisies, on les ajoute.
	if (monFormulaire.autre_competence.value.length != 0)
		result += "Autres compétences :\n" + monFormulaire.autre_competence.value + "\n\n";
	
	// Ajoute les informations diverses.
	result += "Couleur : " + monFormulaire.couleur.value + "\n";
	result += "Fichier : " + monFormulaire.cv.value + "\n";
	result += "Type de fichier : " + monFormulaire.file_type.value + "\n";
	
	// Affichage.
	alert(result);
}
