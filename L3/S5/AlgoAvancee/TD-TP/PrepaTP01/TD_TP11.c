#include <stdio.h>
#include <stdlib.h>

typedef struct coord {
	int x;
	int y;
}coord;
typedef struct cellule {
	point pt;
	struct cellule *suiv, *pred;
}cellule;

cellule *nouvcel(point p) {
	cellule *cel;
	cel = (cellule*)malloc(sizeof(cellule));
	if (cel == NULL) {
		printf("Pas assez de mémoire\n");
		return (NULL);
	}
	cel->suiv=NULL;
	cel->pred=NULL;
	cel->pt=p;
	return cel;
}

void inserer(int pl, cellule *cel, cellule *liste) {
	cellule *celcour;
	int q = 0;
	celcour = liste;
	while (pl != q && celcour != NULL) {
		celcour = celcour->suiv;
		q++;
	}
	if (celcour == NULL)
		printf("La place n'existe pas");
	else {
		cel->suiv = celcour->suiv;
		cel->pred = celcour;
		if (celcour->suiv != NULL)
			celcour->suiv->pred = cel;
		celcour->suiv = cel; 
	}
}

void supprimerCellule (int pl, cellule *liste) {
	int q = 0;
	cellule *celcour;
	celcour = liste;
	while (pl != q && celcour != NULL) {
		celcour = celcour->suiv;
		q++
	}
	if (celcour == NULL) 
		printf("On est sur la bonne position\n");
	if (celcour->suiv != NULL) {
		celcour->suiv->pred = celcour->pred;
		celcour->pred->suiv = celcour->suiv;
	}
	free(celcour);
}

void afficher_cellule(cellule *liste) {
	int len = liste/liste[0];
}
