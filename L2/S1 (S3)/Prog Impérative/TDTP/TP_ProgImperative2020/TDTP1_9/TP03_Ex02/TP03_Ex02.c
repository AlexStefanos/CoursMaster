/*
 * TP3_Ex02.c
 *
 *  Created on: 10 oct. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int	main()
{
	int i;
	char buffer;
	int nb1;
	int nb2;
	int nb_valeurs;
	int nb_max;
	int nb_min;
	int *ptrMax;
	int *ptrMin;

	i = 1;
	scanf("%d", &nb_valeurs);
	scanf("%c", &buffer);
	scanf("%d", &nb1);
	scanf("%c", &buffer);
	scanf("%d", &nb2);
	scanf("%c", &buffer);
	if (nb1 >= nb2)
	{
		nb_min = nb2;
		nb_max = nb1;
	}
	else
	{
		nb_min = nb1;
		nb_max = nb2;
	}
	nb_valeurs -= 2;
	while (i <= nb_valeurs)
	{
		scanf("%d", &nb1);
		scanf("%c", &buffer);
		if (nb1 > nb_max)
		{
			nb_max = nb1;
			*ptrMax = &nb1;
		}
		if (nb1 < nb_min)
		{
			nb_min = nb1;
			*ptrMin = &nb1;
		}
		i++;
	}
	printf("%d	%d\n", nb_min, ptrMin);
}
