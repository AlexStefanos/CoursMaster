// *********************************************************************
// Plie ou déplie un paragraphe.
// *********************************************************************
function toggle()
{
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
