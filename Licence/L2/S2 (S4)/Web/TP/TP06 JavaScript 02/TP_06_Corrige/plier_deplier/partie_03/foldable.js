// Paragraphe à plier/déplier.
var p;

// *********************************************************************
// Plie ou déplie un paragraphe.
// ---------------------------------------------------------------------
// Entrée : Élément qui a appelé la fonction.
// *********************************************************************
function toggle(h)
{
	// Récupère le paragraphe situé juste après le titre.
	p = h.nextElementSibling;
	
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
}

// *********************************************************************
// Déplie un paragraphe.
// *********************************************************************
function unfold()
{
	// Affiche le paragraphe.
	p.style.display = "block";
}
