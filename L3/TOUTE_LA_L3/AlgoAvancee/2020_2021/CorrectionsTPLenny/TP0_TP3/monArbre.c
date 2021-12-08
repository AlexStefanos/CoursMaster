/*
 * monArbre.c
 * Auteur : Leonard Namolaru
 * Projet :  TP numéro 12, UE Programmation Impérative, Licence informatique - Université de Paris 
 */
#include <stdio.h>
#include <stdlib.h>
#include "arbre.h"

int main(){
	int num_noeud;
	char monChoix[2];
	noeud* racine_arbre = NULL;
	noeud* noeud_a_inserer = NULL;
	char carac[2];
	
	/* Créer l'arbre */
	printf("**** La racine de l'arbre ****\n");
	printf("Caractère = ? ");
	fflush(stdout);
	scanf("%s", carac);
	
	racine_arbre = nouvNoeud(carac[0]); // Créer le noeud numero 0 = la racine.
	
	do{
		printf("\n****** Menu ******\n");
		printf("G - Insérer fils gauche  \n");
		printf("D - Insérer fils droit  \n");
		printf("P - Parcours prefixe  \n");
		printf("Q - Quitter  \n");
		printf("Votre choix ? ");		
		fflush(stdout);
		scanf("%s", monChoix);
		
		switch(monChoix[0]){
			case 'G' :fflush(stdin);
					  printf("** Inserer un nouveau noeud APRES la noeud N (fils gauche) **\n");
					  printf("N (N > 0) = ? ");
					  fflush(stdout);
					  scanf("%d", &num_noeud);
					  
					  printf("Caractère = ? ");
					  fflush(stdout);
					  scanf("%s", carac);
					  
					  noeud_a_inserer = nouvNoeud(carac[0]);
					  insererFG(noeud_a_inserer, racine_arbre, num_noeud);
					  break;
					  
			case 'D' :fflush(stdin);
					  printf("** Inserer un nouveau noeud APRES la noeud N (fils droit) **\n");
					  printf("N (N > 0) = ? ");
					  fflush(stdout);
					  scanf("%d", &num_noeud);
					  
					  printf("Caractère = ? ");
					  fflush(stdout);
					  scanf("%s", carac);
					  
					  noeud_a_inserer = nouvNoeud(carac[0]);
					  insererFD(noeud_a_inserer, racine_arbre, num_noeud);
					  break;
					  
			case 'P' :parcoursPrefixe(racine_arbre);
		}
	}while(monChoix[0] != 'Q');
	
	return EXIT_SUCCESS;
}