/*
 * fichier.c
 *
 *  Created on: 29 nov. 2020
 *      Author: denli
 */

#define FICHIER
#include "fichier.h"
#include "tableau.h"
#define NBMAX 40



void LectFichTxt(char *nom){
	FILE *Fic;
	int i,p;
	int lect[NBMAX];
	char code;
	//ouverture du fichier
	Fic = fopen(nom,"r");
	if (Fic == NULL){
		printf("\nErreur à l'ouverture du fichier %s\n",nom);
		exit(1);
	}
	printf("\nLe fichier %s ouvert en mode lecture\n",nom);
	//traitement
	printf("notes :\t");
	fscanf(Fic,"%d\n",&p);
	for (i=0;i<p;i++){
		fscanf(Fic,"%d\t",&lect[i]);
		printf("%d\t",lect[i]);
	}
	//fermeture du fichier
	code = fclose(Fic);
	if (code==EOF)
		printf("\nLe fichier %s n'a pas été correctement fermé\n\n",nom);
	else
		printf("\nLe fichier %s fermé\n\n",nom);
}
void SauvegFichTxt(char *nom){
	FILE *Fic;
	char code;
	int i,n;
	int notes[NBMAX];
	//ouverture du fichier
	Fic = fopen(nom,"w");
	if (Fic == NULL){
		printf("\nErreur à l'ouverture du fichier %s\n",nom);
		exit(1);
	}
	printf("\nLe fichier %s ouvert en mode écriture\n",nom);
	//traitement
	do{
		printf("Entrez le nombre de notes à saisir (>=0 et <%d) : ",NBMAX);
		scanf("%d",&n);
	}while((n<0)||(n>NBMAX));
	SaisieNotes(notes,n);
	fprintf(Fic,"%d\n",n);
	for (i=0;i<n;i++)
		fprintf(Fic,"%d\t",notes[i]);
	//fermeture du fichier
	code = fclose(Fic);
	if (code==EOF)
		printf("Le fichier %s n'a pas été correctement fermé\n",nom);
	else
		printf("Le fichier %s fermé\n",nom);
}
void SauvegFichBin(char *nombre,char *nom2){
	FILE *Fic2;
	FILE *Fic3;
	char code;
	int n;
	int notes[NBMAX];
	//ouverture du fichier
	Fic2 = fopen(nombre,"w");
	if (Fic2 == NULL){
		printf("\nErreur à l'ouverture du fichier %s\n",nombre);
		exit(1);
	}
	printf("\nLe fichier %s ouvert en mode écriture\n",nombre);
	//traitement
	do{
		printf("Entrez le nombre de notes à saisir (>=0 et <%d) : ",NBMAX);
		scanf("%d",&n);
	}while((n<0)||(n>NBMAX));
	fputc(n,Fic2);
	//fermeture du fichier
	code = fclose(Fic2);
	if (code==EOF)
		printf("Le fichier %s n'a pas été correctement fermé\n",nombre);
	else
		printf("Le fichier %s fermé\n",nombre);


	/* Ecrire les notes dans un fichier binaire */
	//ouverture du fichier
	Fic3 = fopen(nom2,"wb");
	if (Fic3 == NULL){
		printf("\nErreur à l'ouverture du fichier %s\n",nom2);
		exit(1);
	}
	printf("\nLe fichier %s ouvert en mode écriture binaire\n",nom2);
	//traitement
	SaisieNotes(notes,n);
	fwrite(notes,sizeof(int),n,Fic3);
	//fermeture du fichier
	code = fclose(Fic3);
	if (code==EOF)
		printf("Le fichier %s n'a pas été correctement fermé\n",nom2);
	else
		printf("Le fichier %s fermé\n",nom2);
}

void LectFichBin(char *nombre,char* nom2){
	FILE *Fic2 = NULL;
	FILE *Fic3 = NULL;
	char code;
	int n;
	int tab[NBMAX];

	/* Recuperer le nombre de notes */
	Fic2 = fopen(nombre,"r");
	if (Fic2 == NULL){
		printf("\nErreur à l'ouverture du fichier %s\n",nombre);
		exit(1);
	}
	fscanf(Fic2,"%d",&n);
	fclose(Fic2);

	/*Lire les notes dans le fichier binaire */
	//ouverture du fichier
	Fic3 = fopen(nom2,"rb");
	if (Fic3 == NULL){
		printf("\nErreur à l'ouverture du fichier %s\n",nom2);
		exit(1);
	}
	printf("\nLe fichier %s ouvert en mode lecture binaire\n",nom2);
	//traitement
	fread(tab,sizeof(int),n,Fic3);
	AfficheNotes(tab,n);
	//fermeture du fichier
	code = fclose(Fic3);
	if (code==EOF)
		printf("Le fichier %s n'a pas été correctement fermé\n\n",nom2);
	else
		printf("Le fichier %s fermé\n\n",nom2);
}
