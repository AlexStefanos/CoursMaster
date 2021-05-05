// Nombre de pas d'incrémentation (en pixels).
var STEP_NUMBER = 15;

// Durée d'un pas d'incrémentation (en millisecondes).
var STEP_TIME = 15;

// Paragraphe à plier/déplier.
var p;

// Triangle à modifier.
var triangle;

// Tailles par défaut des paragraphes.
var default_height = [];

// Taille actuelle d'un paragraphe.
var current_height;

// Titres à plier et à déplier.
var titles = [];

// Index du titre qui précède le paragraphe en train d'être plié ou déplié.
var index;

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
	// Récupère le paragraphe situé juste après le titre.
	p = h.nextElementSibling;

	// Récupère le triangle qui est le dernier élément fils du titre.
	triangle = h.lastElementChild;

	// Récupère l'index du titre.
	index = titles.indexOf(h);

	// Si cet index vaut -1, cela signifie qu'il n'est pas dans le tableau.
	// C'est donc la première fois que l'on clique sur ce titre.
	if (index === -1)
	{
		// Place le titre dans le tableau et récupère son index.
		index = titles.push(h) - 1;

		// Récupère la hauteur du paragraphe pendant qu'il est affiché.
		// Cette valeur devient la hauteur par défaut du paragraphe.
		default_height[index] = p.offsetHeight;
	}

	// Si aucune minuterie n'est déjà active.
	// (Si une minuterie est déjà active, cela signifie qu'un paragraphe
	// est en train d'être plié ou déplié.
	// Par conséquent, il est préférable de ne rien faire.)
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
	current_height = default_height[index];

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
	current_height -= default_height[index] / STEP_NUMBER;
	
	// Si la hauteur courante devient négative ou nulle.
	if (current_height <= 0)
	{
		// On stoppe le timer.
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
	p.style.opacity = current_height / default_height[index];
}

// *********************************************************************
// Déplie une portion de paragraphe.
// *********************************************************************
function unfolding()
{
	// Incrémente la hauteur courante.
	current_height += default_height[index] / STEP_NUMBER;
	
	// Si la hauteur courante devient supérieure ou égale à la hauteur par défaut.
	if (current_height >= default_height[index])
	{
		// On stoppe le timer.
		clearInterval(timer);
		timer = null;

		// On définit la valeur courante à la valeur par défaut.
		current_height = default_height[index];
	}

	// Assigne la hauteur courante au paragraphe.
	p.style.height = current_height + "px";
	
	// Règle l'opacité en fonction de la valeur courante.
	p.style.opacity = current_height / default_height[index];
}
