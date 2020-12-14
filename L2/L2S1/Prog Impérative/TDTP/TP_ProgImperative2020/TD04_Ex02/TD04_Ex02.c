/*
 * TD04_Ex02.c
 *
 *  Created on: 11 oct. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int main()
{
	int matrix[10][10];
	int nbLignes;
	char buffer;
	int nbColonnes;
	int nb;
	int i;
	int j;

	i = 0;
	j = 0;
	scanf("%d", &nbLignes);
	scanf("%c", &buffer);
	scanf("%d", &nbColonnes);
	scanf("%c", &buffer);
	while (i < nbLignes)
	{
		while (j < nbColonnes)
		{
			scanf("%d", &nb);
			scanf("%c", &buffer);
			matrix[i][j] = nb;
			j++;
		}
		j = 0;
		i++;
	}
	i = 0;
	j = 0;
	while (i < nbLignes)
	{
		while (j < nbColonnes)
		{
			printf("%d,", matrix[i][j]);
			j++;
		}
		printf("\n");
		j = 0;
		i++;
	}
}
