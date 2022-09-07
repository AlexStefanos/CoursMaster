// Nombre de pas d'incrémentation (en pixels).
var STEP_NUMBER = 15;

// Durée d'un pas d'incrémentation (en millisecondes).
var STEP_TIME = 15;

// Paragraphe à plier/déplier.
var p;

// Triangle à modifier.
var triangle;

// Taille par défaut d'un paragraphe.
var default_height;

// Taille actuelle d'un paragraphe.
var current_height;

// Minuterie.
// Quand aucune minuterie n'est active, cette valeur doit être définie à null.
// Cela nous permet de savoir quand une minuterie est active ou non.
var timer = null;

// *********************************************************************
// Plie ou déplie un paragraphe.
// ---------------------------------------------------------------------
// Entrée : Élément qui a appelé la fonction.
// *********************************************************************
function toggle(h)
{
	// Si la taille par défaut n'a pas encore été récupèrée,
	// c'est qu'il s'agit du premier clic sur ce titre.
	if (default_height === undefined)
	{
		// Récupère le paragraphe situé juste après le titre.
		p = h.nextElementSibling;

		// Récupère le triangle qui est le dernier élément fils du titre.
		triangle = h.lastElementChild;

		// Récupère la hauteur du paragraphe pendant qu'il est affiché.
		// Cette valeur devient la hauteur par défaut du paragraphe.
		default_height = p.offsetHeight;
	}
	
	// Si aucune minuterie n'est déjà active.
	// Si une minuterie est déjà active, cela signifie qu'un paragraphe est en train d'être plié ou déplié.
	// Par conséquent, il est préférable de ne rien faire.
	if (timer === null)
	{
		// Si le paragraphe est déjà affiché, on le plie.
		if (getComputedStyle(p).display === "block")
			fold();

		// Sinon, on le déplie.
		else
			unfold();
	}
}

// *********************************************************************
// Plie un paragraphe.
// *********************************************************************
function fold()
{
	// Puisque le paragraphe est déplié, on sait que sa hauteur
	// courante est la hauteur par défaut.
	current_height = default_height;

	// Mise à jour de l'affichage du triangle.
	triangle.innerHTML = "&triangleright;";
	
	// Lance un timer qui va plier le paragraphe petit à petit.
	// La fonction folding() s'exécutera toutes les STEP_TIME millisecondes.
	timer = setInterval(folding, STEP_TIME);
}

// *********************************************************************
// Déplie un paragraphe.
// *********************************************************************
function unfold()
{
	// Puisque le paragraphe est plié, on sait que sa hauteur est nulle.
	current_height = 0;

	// Affiche le paragraphe.
	// (Comme le paragraphe était plié, il n'était pas affiché.)
	p.style.display = "block";

	// Mise à jour de l'affichage du triangle.
	triangle.innerHTML = "&triangledown;";

	// Lance un timer qui va déplier le paragraphe petit à petit.
	// La fonction unfolding() s'exécutera toutes les STEP_TIME millisecondes.
	timer = setInterval(unfolding, STEP_TIME);
}

// *********************************************************************
// Plie une portion de paragraphe.
// *********************************************************************
function folding()
{
	// Décrémente la hauteur courante.
	current_height -= default_height / STEP_NUMBER;
	
	// Si la hauteur courante devient négative ou nulle.
	if (current_height <= 0)
	{
		// On stoppe le time.
		clearInterval(timer);
		timer = null;
		
		// On définit la valeur courante à 0.
		current_height = 0;
		
		// On camoufle le paragraphe.
		p.style.display = "none";
	}

	// Assigne la hauteur courante au paragraphe.
	p.style.height = current_height + "px";
	
	// Règle l'opacité en fonction de la valeur courante.
	p.style.opacity = current_height / default_height;
}

// *********************************************************************
// Déplie une portion de paragraphe.
// *********************************************************************
function unfolding()
{
	// Incrémente la hauteur courante.
	current_height += default_height / STEP_NUMBER;
	
	// Si la hauteur courante devient supérieure ou égale à la hauteur par défaut.
	if (current_height >= default_height)
	{
		// On stoppe le timer.
		clearInterval(timer);
		timer = null;

		// On définit la valeur courante à la valeur par défaut.
		current_height = default_height;
	}

	// Assigne la hauteur courante au paragraphe.
	p.style.height = current_height + "px";
	
	// Règle l'opacité en fonction de la valeur courante.
	p.style.opacity = current_height / default_height;
}
