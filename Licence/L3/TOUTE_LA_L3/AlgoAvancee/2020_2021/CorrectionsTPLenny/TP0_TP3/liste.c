/*
 * liste.c
 * Auteur : Leonard Namolaru
 * Projet :  TP numéro 11, UE Programmation Impérative, Licence informatique - Université de Paris 
 */
#define LISTE 
#include <stdio.h> // fprintf(), printf()
#include <stdlib.h> // exit(), EXIT_FAILURE, malloc(), free()
#include "liste.h"

/* cellule* NouvCel(pointFormePolygonale p)
 * Alloue l'espace mémoire pour une cellule, remplit les champs de la structure cellule.
 * Retourne un pointeur sur cette cellule
 */
cellule *NouvCel(pointFormePolygonale p){
	cellule* pNouvCel = NULL;
	pNouvCel = (cellule*) malloc( sizeof(cellule) );
	
	if(!pNouvCel){
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}
		
	pNouvCel -> celPrecedente = NULL; // Pointeur vers la cellule précédente de la liste.
	pNouvCel -> celSuivante = NULL; // Pointeur vers la cellule suivante de la liste.
	pNouvCel -> pointXY =  p; // Les coordonnées X et Y du point.
	
	return pNouvCel;
} //NouvCel()

/* void InsererCellule(int pl, cellule* cel, cellule* liste)
 * Permet d'insérer la cellule cel après la place pl dans la liste liste.
 */
void InsererCellule(int pl, cellule* cel, cellule* liste){
	cellule* celluleActuelle = liste;
	cellule* positionSuivante;		
	int positionActuelle = 0; // Notre position actuelle lors de la recherche de la position pl dans la liste
	int positionTrouvee = 0; // La position pl dans la liste a-t-elle été trouvée ?
	
	/* La recherche dans la liste se poursuit tant que l'emplacement pl n'est pas trouvé 
	   ET tant qu'il y a des cellules dans la liste  */
	while( (celluleActuelle != NULL) && (positionTrouvee == 0) ){
		positionSuivante = celluleActuelle -> celSuivante; // Pointeur vers la cellule suivante de la liste.
		
		// L'emplacement pl que nous recherchions est trouvé. 
		// La nouvelle cellule doit être ajoutée après la cellule pl
		if(positionActuelle == pl){
			cel -> celPrecedente = celluleActuelle; // La nouvelle cellule doit pointer vers la cellule pl = sa cellule précédente.
			celluleActuelle -> celSuivante = cel; // La cellule pl doit pointer vers la nouvelle cellule = sa cellule suivante
			
			// Y a-t-il une autre cellule après la position pl dans la liste d'origine ?
			if(positionSuivante != NULL){
				positionSuivante -> celPrecedente = cel;
				cel -> celSuivante = positionSuivante;
			}
			
			positionTrouvee = 1; // La position pl dans la liste a-t-elle été trouvée ? OUI
		}
		
		positionActuelle++;
		celluleActuelle = positionSuivante;
	} // while
	
	if(!positionTrouvee)
		fprintf(stderr, "L'emplacement %d n'existe pas dans la liste. \n", pl);
	
} // InsererCellule()

/* void SupprimeCellule(int pl, cellule* liste)
 * Permet de supprimer la cellule à la position pl dans la liste.
 */
void SupprimeCellule(int pl, cellule* liste){
	cellule* celluleActuelle = liste;
	cellule* positionPrecedente;
	cellule* positionSuivante;		
	int positionActuelle = 0; // Notre position actuelle lors de la recherche de la position pl dans la liste
	int positionTrouvee = 0; // La position pl dans la liste a-t-elle été trouvée ?
	
	if(pl == 0){
		fprintf(stderr, "La cellule qui est en position 0 ne peut pas être supprimée. \n");	
		return;
	}

	/* La recherche dans la liste se poursuit tant que l'emplacement pl n'est pas trouvé 
	   ET tant qu'il y a des cellules dans la liste  */	
	while( (celluleActuelle != NULL) && (positionTrouvee == 0) ){
		positionSuivante = celluleActuelle -> celSuivante; // Pointeur vers la cellule suivante de la liste.
		
		// L'emplacement pl que nous recherchions est trouvé. 
		if(positionActuelle == pl){
			positionPrecedente = celluleActuelle -> celPrecedente;
			positionPrecedente -> celSuivante = positionSuivante;
			
			// Y a-t-il une autre cellule après la position pl dans la liste d'origine ?
			if(positionSuivante != NULL)
				positionSuivante -> celPrecedente = positionPrecedente;
			
			free(celluleActuelle);
			positionTrouvee = 1;
		}// if
		
		positionActuelle++;
		celluleActuelle = positionSuivante;
	} // while

	if(!positionTrouvee)
		fprintf(stderr, "L'emplacement %d n'existe pas dans la liste. \n", pl);	
	
} // SupprimeCellule

/* void Afficher(cellule* liste)
 * Affiche la liste liste.
 */
void Afficher(cellule* liste){
	cellule* celluleActuelle = liste;
	cellule* positionSuivante;
	pointFormePolygonale pointCel;
	
	printf("La liste : \n");	
	while(celluleActuelle != NULL){
		pointCel = celluleActuelle -> pointXY;
		printf(" (%d , %d) ", pointCel.pointX, pointCel.pointY);
		
		positionSuivante = celluleActuelle -> celSuivante;
		celluleActuelle = positionSuivante;
	}
	
	printf("\n");
} //Afficher()