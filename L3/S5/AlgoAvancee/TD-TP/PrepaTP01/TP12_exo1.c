/*
 * TP12_exo1.c
 *
 *  Created on: 14 déc. 2020
 *      Author: denli
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct noeud{
	struct noeud *pere;
	char etiquette;
	int num;
	struct noeud *filsG, *filsD;
}noeud;

noeud *nouvNoeud(char c){
	noeud *p = NULL;
	static int numero; //var rémanente auto initialisée à 0
	p = (noeud*)malloc(sizeof(noeud));
	if (p==NULL){
		printf("Prob d'allocation dynamique du noeud\n");
		exit(1);
	}
	p->pere = NULL;
	p->etiquette = c;
	p->num = ++numero;
	p->filsG = NULL;
	p->filsD = NULL;
	return p;
}

noeud *rechercheNoeud(noeud *n,int numNoeud){
	noeud *tmpNoeud = NULL;
	if (n==NULL)
		return NULL;
	if (n->num==numNoeud)
		return n;
	tmpNoeud = rechercheNoeud(n->filsG,numNoeud);
	if(tmpNoeud!=NULL)
		return(tmpNoeud);
	return(rechercheNoeud(n->filsD,numNoeud));
}

void insererFG(noeud *ndInsert,noeud *a,int numN){
	noeud *plInsert = NULL;
	plInsert = rechercheNoeud(a,numN);
	if (plInsert==NULL){
		printf("Pas de noeud trouvé a la place %d\n",numN);
		return;
	}
	ndInsert->pere = plInsert;
	if (plInsert->filsG!=NULL){
		ndInsert->filsG = plInsert->filsG;
		plInsert->filsG->pere = ndInsert;
	}
	plInsert->filsG = ndInsert;

}

void insererFD(noeud *ndInsert,noeud *a,int numN){
	noeud *plInsert = NULL;
	plInsert = rechercheNoeud(a,numN);
	if (plInsert==NULL){
		printf("Pas de noeud trouvé a la place %d\n",numN);
		return;
	}
	ndInsert->pere = plInsert;
	if (plInsert->filsD!=NULL){
		ndInsert->filsD = plInsert->filsD;
		plInsert->filsD->pere = ndInsert;
	}
	plInsert->filsD = ndInsert;
}

void parcoursPrefixe(noeud *n){
	if (n==NULL)
		return;
	printf("%c(%d)\t",n->etiquette,n->num);
	parcoursPrefixe(n->filsG);
	parcoursPrefixe(n->filsD);
}

void supprimeArbre(noeud *n){
	if (n==NULL)
		return;
	supprimeArbre(n->filsG);
	supprimeArbre(n->filsD);
	if (n->pere!=NULL){
		if (n->pere->filsG==n)
			n->pere->filsG = NULL;
		else
			n->pere->filsD = NULL;
	}
	free(n);
	printf("suppression\t");
}

void choixMenu(void){
	fflush(stdin);
    printf("\n************ Gestion de l'arbre ************\n");
    printf("Insérer des noeuds :\t\tI\n");
    printf("Supprimer un noeud :\t\tS\n");
    printf("Afficher l'arbre :\t\tA\n");
    printf("Quitter :\t\t\tQ\n");
    printf("choix > ");
}

int main(){
	noeud *arbre = NULL;
	noeud *nd = NULL;
	char car;
	char choix,rep;
	int pos;
	int sortie = 0;
	printf("Etiquette de la racine : ");
	scanf("%c",&car);
	arbre = nouvNoeud(car);
	do{
		choixMenu();
		scanf("%c", &choix);
		switch(choix){
		case 'I': case 'i':	printf("\nInserer après quel noeud (on commence à 1) : ");
							scanf("%d",&pos);
							printf("Etiquette du nouvel noeud à inserer : ");
							fflush(stdin);
							scanf("%c",&car);
							nd = nouvNoeud(car);
							do{
								printf("\nInserer en fils gauche :\tG");
								printf("\nInserer en fils droit :\t\tD");
								printf("\nchoix > ");
								fflush(stdin);
								scanf("%c",&rep);
								switch(rep){
								case 'G': case 'g':	insererFG(nd,arbre,pos);
													sortie = 1;
													break;
								case 'D': case 'd':	insererFD(nd,arbre,pos);
													sortie = 1;
													break;
								default :			printf("\nChoix incorrect.\n");
								}
							}while(sortie!=1);
							break;
		case 'S': case 's':	printf("\nQuel est le numéro de création de l'arbre à supprimer : ");
							scanf("%d",&pos);
							nd = rechercheNoeud(arbre,pos);
							if (nd==arbre) //si on supprime racine, eviter d'afficher le tas liberé
								arbre=NULL;
							supprimeArbre(nd);
							printf("\n");
							break;
		case 'A': case 'a':	printf("\n***************** Affichage de l'arbre selon le format : EtiquetteNoeud(NumCreation) *****************\n");
							parcoursPrefixe(arbre);
							printf("\n");
							break;
		case 'Q': case 'q':	printf("\nAu revoir\n");
							break;
		default : printf("\nChoix incorrect.\n");
		}
	}while(choix != 'Q' && choix != 'q');
	free(arbre);
	free(nd);
	return EXIT_SUCCESS;
}

