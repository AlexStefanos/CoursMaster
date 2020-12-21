/*
 * TP2_Ex04.c
 *
 *  Created on: 29 sept. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int	main()
{
	char buffer;
	int sum;
	int b5;
	int p2;
	int p1;

	scanf("%d", &sum);
	b5 = sum / 5;
	p2 = (sum % 5) / 2;
	p1 = ((sum % 5) % 2) / 1;
	printf("%d\n", b5);
	printf("%d\n", p2);
	printf("%d\n", p1);
}
