#include <stdio.h>
#include <stdlib.h>

typedef struct point{
  int x, y;
}point;

typedef struct cellule{
  point pos;
  struct cellule * prec, * suiv;
}cellule;

cellule * NouvCel(point p){
	cellule *cel;
	cel = (cellule *)malloc(sizeof(cellule));
	if (!cel){
		printf("Erreur d'allocation !\n");
		exit(EXIT_FAILURE);
	}

	cel->pos.x = p.x;
	cel->pos.y = p.y;
	cel->prec = NULL;
	cel->suiv = NULL;
	return cel;
}

void InsererCellule(int pl, cellule *cel, cellule *liste){
	int position = 0;
	while ((position != pl) && (liste->suiv != NULL)){
		liste = liste->suiv;
		position++;
	}

	if (liste->suiv == NULL){
			cel->prec = liste;
			liste->suiv = cel;
	} else if (position == pl){
		liste=liste->prec;
		cel->prec = liste;
		cel->suiv = liste->suiv;
		liste->suiv = cel;
		liste->suiv->prec = cel;
	}

}

void SupprimeCellule(int pl, cellule *liste){
	int pos = 0;
	while (pos != pl && liste->suiv != NULL){
		liste = liste->suiv;
		pos++;
	}
	if (pos == pl){
		if (liste->suiv == NULL)
			liste->prec->suiv = NULL;
		else {
			liste->prec->suiv = liste->suiv;
			liste->suiv->prec = liste->prec;
		}
	}
}

void Afficher(cellule *liste){
	cellule * celcourante = liste->suiv;
	printf("Les points dans l'ordre voulue :\n");
	do {
		printf("\tx = %d, y = %d\n", celcourante->pos.x, celcourante->pos.y);
		celcourante = celcourante->suiv;
	} while ( celcourante != NULL);
  free(celcourante);
}
int main(int argc, char* argv[]){
	int pos;
  char choix;
  point p;
	point tamp = {tamp.x=-1, tamp.y=-1};
  cellule *NCel = NULL;
	cellule *liste = NouvCel(tamp);
	do {
		choix = 'a';

		printf("Que voulez-vous faire ?\n\tCréer une cellule (0)\n\tInsérer une cellule (1)\n\tSupprimer une cellule (2)\n\tAfficher la liste (3)\n\tQuitter (q)\n\t- Choix ? ");
		scanf("%s", &choix);
		switch (choix){
			case '0':
				printf("Coordonnées de la nouvelle cellule (x y): ");
				scanf("%d %d", &p.x, &p.y);
				NCel = NouvCel(p);
				break;
			case '1':
				if (NCel == NULL){
					printf("Vous devez d'abord crée une cellule !\n\n");
					break;
				}
				printf("Position de la cellule: ");
				scanf("%d", &pos);
				InsererCellule(pos, NCel, liste);
				break;
			case '2':
				printf("Position de la cellule: (>=1) ");
				scanf("%d", &pos);
				SupprimeCellule(pos, liste);
				break;
			case '3':
				Afficher(liste);

				break;
		}
	}while (choix != 'q');
  free(NCel);
  free(liste);
	return EXIT_SUCCESS;
}
