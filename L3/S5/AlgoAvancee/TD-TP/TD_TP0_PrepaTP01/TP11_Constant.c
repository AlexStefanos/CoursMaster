/*
 ============================================================================
 Name        : Tp11_programmation_impérative.c
 Author      : Constant
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

/*
 ============================================================================
 Name        : TP_11.c
 Author      :
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
typedef struct point {
	int x;
	int y;
}point;

typedef  struct  cellule{
	point pt;
	struct cellule * suivant;
	struct cellule * precedent;
}cellule;




point *creerPoint(){
	int x,y;
	point *pt=NULL;
	pt=(point*)malloc(sizeof(point));
	printf("Veuillez saisir les coordonnées: \n x:\n");
	fflush(stdin);
	scanf("%d",&x);
	printf("y: \n");

	fflush(stdin);
	scanf("%d",&y);

	pt->x=x;
	pt->y=y;



	return pt;
}


cellule *NouvCel () {
	cellule * nouvelle_cellule=NULL;
	point * p=NULL;
	p=creerPoint();

	nouvelle_cellule=(cellule*)malloc(sizeof(cellule));
	if(nouvelle_cellule==NULL){
			printf("problème allocation mémoire\n");
			return(NULL);
	}
	nouvelle_cellule->suivant=NULL;
	nouvelle_cellule->precedent=NULL;
	nouvelle_cellule->pt=*p;
	free(p);
	printf("Votre point a bien été créé \n");

	return nouvelle_cellule;
};

void InsererCellule(int pl, cellule *cel, cellule *liste){
	int ind=0;
	cellule *cellule_courante=liste;

	while(pl !=ind && cellule_courante!=NULL){
		cellule_courante=cellule_courante->suivant;
		ind++;
	}
	if(cellule_courante==NULL){
		printf("Nous ne pouvons pas placer votre cellule à cet endroit\n\n");
	}else {
		cel->suivant=cellule_courante->precedent;
		cel->precedent=cellule_courante;
		if(cellule_courante->suivant != NULL){
			cellule_courante->suivant->precedent=cel;
		}
		cellule_courante->suivant=cel;
		printf("Votre cellule a bien été placé à l'emplacement %d\n\n",pl);
	}
}

void SupprimerCellule(int pl , cellule *liste){
	int ind=0;
	cellule *cellule_courante;
	cellule_courante=liste;

	while(pl !=ind && cellule_courante!=NULL){
		cellule_courante=cellule_courante->suivant;
		ind++;
	}
	if(cellule_courante==NULL){
		printf("La liste est vide nous ne pouvons rien supprimer");
		return;
	}else{
		cellule_courante->precedent->suivant=cellule_courante->suivant;
		cellule_courante->suivant->precedent=cellule_courante->precedent;
		liste=cellule_courante;

		printf("Votre point situé à l'indince %d a bien été supprimé \n",pl);
		free(cellule_courante);
	}

}
void Afficher(cellule *liste){
	cellule *cellule_courante=NULL;
	cellule_courante=liste;
	while(cellule_courante != NULL){
		printf("Votre point à pour valeur x: %d, y : %d\n",cellule_courante->pt.x,cellule_courante->pt.y);
		cellule_courante=cellule_courante->suivant;
	}
	printf("\n");
	return;
}

void menu(){
	puts("\t\t****************************Bienvenue dans le TP 11****************************");
	printf("Veuillez selectionner ce que vous voulez faire : \n Tappez 1 pour créer un point\n Tappez 2  pour supprimer une cellule\n Tappez 3 pour consulter la liste de cellule \n\n Tappez 0 pour quitter.\n\n");
	puts("\t\t*******************************************************************************");
}

int main(void) {
	int choix;
	cellule *liste =  NULL;

	do {

		menu();
		fflush(stdin);
		scanf("%d",&choix);
		switch(choix){
			case 1 :
			{
				cellule *cel=NULL;

				cel=NouvCel();

				if (cel==NULL ){
					printf("Erreur lors  de la création du point");
				}else if(liste==NULL) {
					liste=cel;
				}else{
					int placement;
					printf("Saisissez l'endroit ou vous souhaitez le placer");
					fflush(stdin);
					scanf("%d",&placement);
					InsererCellule(placement, cel, liste);
				}
				break;
			}
			case  2 :
			{
				int placement;
				printf("Veuillez saisir l'indice du point que vous voulez supprimer\n");
				fflush(stdin);
				placement=scanf("%d",&placement);
				SupprimerCellule(placement,liste);
				break;
			}
			case 3 :
			{
				Afficher(liste);
				break;
			}
		}
	}while(choix !=0);
	return EXIT_SUCCESS;
}
