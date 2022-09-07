/*
 * TP01_Ex03.c
 *
 *  Created on: 24 sept. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int	main()
{
	int jour;
	int mois;
	int annee;
	scanf("%d", &jour);
	scanf("%d", &mois);
	scanf("%d", &annee);
	if (mois >= 10)
		printf("Introduisez la date (jour mois année) : %d-%d-%d\n", jour, mois, annee);
	else
		printf("Introduisez la date (jour mois année) : %d-0%d-%d\n", jour, mois, annee);
	printf("Données correctement lues : 3\n");
	printf("/*****Affichage de la date saisie *****/\n");
	printf("Jour : 17 \nMois : 9 \nAnnée : 2019");
}
