/*
 * TP04_Ex03.c
 *
 *  Created on: 13 oct. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int main()
{
	int NbColonnes;
	int NbLignes;
	char buffer;
	int i;
	int j;

	i = 0;
	j = 0;
	scanf("%d", &NbColonnes);
	scanf("%c", &buffer);
	while ((NbColonnes <= 0) || (NbColonnes > 10))
	{
		printf("La valeur saisie pour NbColonnes ne correspond pas aux conditions de l'exercice. Veuillez saisir une nouvelle valeur : ");
		scanf("%d", &NbColonnes);
		scanf("%c", &buffer);
	}
	scanf("%d", &NbLignes);
	scanf("%c", &buffer);
	while ((NbLignes <= 0 ) || (NbLignes > 10))
	{
		printf("La valeur saisie pour NbLignes ne correspond pas aux conditions de l'exercice. Veuillez saisir une nouvelle valeur : ");
		scanf("%d", &NbLignes);
		scanf("%c", &buffer);
	}
	int matrix[NbLignes][NbColonnes];
	while (i < NbLignes)
	{
		while (j < NbColonnes)
		{
			printf("Entrez la valeur de la matrice[%d][%d] : \n", i, j);
			scanf("%d", &matrix[i][j]);
			scanf("%c", &buffer);
			j++;
		}
		j = 0;
		i++;
	}
	i = 0;
	printf("\n");
	printf("\n");
	while (i < NbLignes)
	{
		while (j < NbColonnes)
		{
			printf("%d	", matrix[i][j]);
			j++;
		}
		printf("\n");
		j = 0;
		i++;
	}
}
