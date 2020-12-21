/*
 * TP2_Ex05.c
 *
 *  Created on: 29 sept. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int main()
{
	char buffer;
	int x;
	int p;
	int n;
	int debut;

	scanf("%d", &x);
	scanf("%c", &buffer);
	scanf("%d", &p);
	scanf("%c", &buffer);
	scanf("%d", &n);
	printf("x : %d\n", x);
	printf("p : %d\n", p);
	printf("n : %d\n", n);
	debut = 1;
	while (p != 1)
	{
		debut *= 2;
		p--;
	}
	printf("debut : %d\n", debut);
	while (n != 0)
	{
		if (x % debut == 0)
		{
			if (n % 2 == 0)
				x += debut;
			else
				x -= debut;
		}
		else
		{
			if (n % 2 == 0)
				x -= debut;
			else
				x += debut;
		}
		debut *= 2;
		n--;
	}
	printf("%d\n", x);
}
