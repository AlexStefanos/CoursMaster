// Paragraphe à plier/déplier.
var p;

// Triangle à modifier.
var triangle;

// *********************************************************************
// Plie ou déplie un paragraphe.
// ---------------------------------------------------------------------
// Entrée : Élément qui a appelé la fonction.
// *********************************************************************
function toggle(h)
{
	// Récupère le paragraphe situé juste après le titre.
	p = h.nextElementSibling;

	// Récupère le triangle qui est le dernier élément fils du titre.
	triangle = h.lastElementChild;
	
	// Si le paragraphe est déjà affiché, on le plie.
	if (getComputedStyle(p).display === "block")
		fold();

	// Sinon, on le déplie.
	else
		unfold();
}

// *********************************************************************
// Plie un paragraphe.
// *********************************************************************
function fold()
{
	// Camoufle le paragraphe.
	p.style.display = "none";
	
	// Mise à jour de l'affichage du triangle.
	triangle.innerHTML = "&triangleright;";
}

// *********************************************************************
// Déplie un paragraphe.
// *********************************************************************
function unfold()
{
	// Affiche le paragraphe.
	p.style.display = "block";

	// Mise à jour de l'affichage du triangle.
	triangle.innerHTML = "&triangledown;";
}
