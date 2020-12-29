/*
 * main.c
 *
 *  Created on: 29 nov. 2020
 *      Author: denli
 */
#include "fichier.h"

int main(int argc,char *argv[]){
	char *Nom = argv[1];
	SauvegFichTxt(Nom);
	LectFichTxt(Nom);
	return EXIT_SUCCESS;
}


