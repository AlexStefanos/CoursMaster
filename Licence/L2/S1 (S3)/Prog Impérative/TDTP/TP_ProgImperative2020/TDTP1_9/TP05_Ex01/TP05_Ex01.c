/*
 * TP05_Ex01.c
 *
 *  Created on: 20 oct. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int	Mult_2(int nb)
{
	if (nb % 2 == 0)
	{
		printf("Le nombre est paire.\n");
		return (1);
	}
	else
	{
		printf("Le nombre est impaire.\n");
		return (0);
	}
}

int	Mult_3(int nb)
{
	if (nb % 3 == 0)
	{
		printf("Le nombre saisi est un multiple de 3.\n");
		return (1);
	}
	else
	{
		printf("Le nombre saisi n'est pas un multiple de 3.\n");
		return (0);
	}
}

int	main()
{
	char buffer;
	int nb;

	scanf("%d", &nb);
	scanf("%c", &buffer);
	Mult_2(nb);
	Mult_3(nb);
	if (nb % 6 == 0)
		printf("Le nombre saisi est un multiple de 6.");
	else
		printf("Le nombre saisi n'est pas un multiple de 6.");
}
