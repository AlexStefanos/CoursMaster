/*
 * TP04_Ex01.c
 *
 *  Created on: 13 oct. 2020
 *      Author: alexandre
 */

#include <stdio.h>
#include <stdlib.h>

int	main()
{
	int nb;
	int nb_effectif;
	int i;
	int j;
	int l;
	char buffer;
	scanf("%d", &nb);
	scanf("%c", &buffer);
	while ((nb <= 0) || (nb > 50))
	{
		printf("La valeur saisie pour nb ne correspond pas aux conditions de l'exercice. Veuillez saisir une nouvelle valeur : ");
		scanf("%d", &nb);
		scanf("%c", &buffer);
	}
	int *tab = (int *)malloc(sizeof(nb));
	int *t1 = (int *)malloc(sizeof(nb));
	int *effectif = (int *)malloc(sizeof(nb));

	i = 0;
	j = 1;
	nb_effectif = 0;
	scanf("%d", &tab[i]);
	scanf("%c", &buffer);
	while (j < nb)
	{
		scanf("%d", &tab[j]);
		scanf("%c", &buffer);
		if (tab[j] < tab[i])
		{
			printf("La valeur saisie est inferieure a la precedente. Veuillez saisir une nouvelle valeur : ");
			i -= 1;
			j -= 1;
		}
		j++;
		i++;
	}
	i = 0;
	j = 1;
	l = 0;
	while (i < nb)
	{
		if (tab[j] != tab[i])
		{
			t1[l] = tab[i];
			l++;
		}
		i++;
		if (j <= (nb - 1))
			j++;
	}
	i = 1;
	j = 0;
	while (i < nb)
	{
		if (t1[i] != t1[i - 1])
			nb_effectif += 1;
		i++;
	}
	//initialiser effectif[0] et regarder 1 par 1
	i = 0;
	printf("t1 	: ");
	while (i < nb)
		printf("%d	", tab[i++]);
	i = 0;
	j = 1;
	int buff;
	buff = t1[0];
	printf("\n");
	printf("%d\n ", nb_effectif);
	printf("effectif : ");
	while (i < nb_effectif)
	{
		if (t1[i])
		while (j < nb)
		{
			if ()
			j++;
		}
		j = 1;
		i++;
	}
}
