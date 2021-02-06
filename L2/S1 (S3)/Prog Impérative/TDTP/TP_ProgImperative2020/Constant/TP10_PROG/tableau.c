/*
 * tableau.c
 *
 *  Created on: 29 nov. 2020
 *      Author: denli
 */

#define TABLEAU
#include "tableau.h"

void SaisieNotes(int t[],int nb){
	for (int i=0;i<nb;i++){
		do{
		printf("Entrez la note n°%d (comprise entre 0 et 20) : ",i+1);
		scanf("%d",&t[i]);
		}while((t[i]<0)||t[i]>20);
	}
}

void AfficheNotes(int t[],int nb){
	printf("notes :\t");
	for (int i=0;i<nb;i++)
		printf("%d\t",t[i]);
	printf("\n");
}
