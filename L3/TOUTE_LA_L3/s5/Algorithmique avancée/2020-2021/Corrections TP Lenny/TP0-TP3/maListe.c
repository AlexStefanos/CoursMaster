/*
 * maListe.c
 * Auteur : Leonard Namolaru
 * Projet :  TP numéro 11, UE Programmation Impérative, Licence informatique - Université de Paris 
 */
#include <stdio.h>
#include <stdlib.h>
#include "liste.h"

int main(){
	int pl;
	char monChoix[2];
	cellule* liste = NULL;
	cellule* cel = NULL;
	pointFormePolygonale coordonneesPoint;
	
	/* Créer la liste */
	printf("**** Position 0 de la liste ****\n");
	printf("x = ? ");
	fflush(stdout);
	scanf("%d", &coordonneesPoint.pointX);
	printf("y = ? ");
	fflush(stdout);
	scanf("%d", &coordonneesPoint.pointY);
	
	liste = NouvCel(coordonneesPoint); // Créer la cellule à la position 0.

	
	do{
		printf("\n****** Menu ******\n");
		printf("I - Insérer  \n");
		printf("S - Supprimer  \n");
		printf("A - Afficher  \n");
		printf("Q - Quitter  \n");
		printf("Votre choix ? ");		
		fflush(stdout);
		scanf("%s", monChoix);
		
		switch(monChoix[0]){
			case 'I' :fflush(stdin);
					  printf("** Inserer une nouvelle cellule APRES la position N **\n");
					  printf("N (N >= 0) = ? ");
					  fflush(stdout);
					  scanf("%d", &pl);
					  
					  printf("x = ? ");
					  fflush(stdout);
					  scanf("%d", &coordonneesPoint.pointX);
	                  printf("y = ? ");
	                  fflush(stdout);
	                  scanf("%d", &coordonneesPoint.pointY);			  					  
					  
					  cel = NouvCel(coordonneesPoint);
					  InsererCellule(pl, cel , liste);
					  break;
					  
			case 'S' :fflush(stdin);
					  printf("Supprimer la cellule de la position N \n");
					  printf("N (N > 0) = ? ");
					  fflush(stdout);
					  scanf("%d", &pl);
					  SupprimeCellule(pl, liste);
					  break;
					  
			case 'A' :Afficher(liste);
		}
	}while(monChoix[0] != 'Q');
	
	return EXIT_SUCCESS;
}