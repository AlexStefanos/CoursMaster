/*
 * TP04_Ex02.c
 *
 *  Created on: 13 oct. 2020
 *      Author: alexandre
 */

#include <stdio.h>
#include <stdlib.h>

int	main()
{
	int i;
	int j;
	int nb;
	int mid;
	int b;
	char buffer;
	scanf("%d", &nb);
	scanf("%c", &buffer);
	while ((nb <= 0) || (nb > 30))
	{
		printf("La valeur saisie pour nb ne correspond pas aux conditions de l'exercice. Veuillez saisir une nouvelle valeur : ");
		scanf("%d", &nb);
		scanf("%c", &buffer);
	}
	int *tab = (int *)malloc(sizeof(nb));

	i = 0;
	while (i < nb)
	{
		scanf("%d", &tab[i]);
		i++;
	}
	i = 0;
	printf("Tab initiale : ");
	while (i < nb)
		printf("%d	", tab[i++]);
	printf("\n");
	j = nb - 1;
	i = 0;
	if (nb % 2 != 0)
		mid = (nb+1) / 2;
	else
		mid = nb / 2;
	while (i < mid)
	{
		b = tab[i];
		tab[i] = tab[j];
		tab[j] = b;
		i++;
		j--;
	}
	i = 0;
	printf("Tab inverse : ");
	while (i < nb)
		printf("%d	", tab[i++]);
	printf("\n");
}
