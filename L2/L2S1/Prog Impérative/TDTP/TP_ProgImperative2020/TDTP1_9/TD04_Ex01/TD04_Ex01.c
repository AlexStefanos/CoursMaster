/*
 * TD04_Ex01.c
 *
 *  Created on: 11 oct. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int main()
{
	int nb;
	int i;
	char buffer;
	int tab[20];
	int val;

	i = 0;
	scanf("%d", &nb);
	scanf("%c", &buffer);
	while (i < nb)
	{
		scanf("%d", &val);
		scanf("%c", &buffer);
		tab[i] = val;
		i++;
	}
	i = 0;
	while (i < nb)
	{
		printf("%d,", tab[i]);
		i++;
	}
}
