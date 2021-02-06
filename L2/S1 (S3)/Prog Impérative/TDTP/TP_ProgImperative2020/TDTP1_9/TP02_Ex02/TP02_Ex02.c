/*
 * TP2_Ex02.c
 *
 *  Created on: 29 sept. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int	main()
{
	char buffer;
	int quotient;
	int reste;
	float quo_ra;
	int a;
	int b;

	scanf("%d", &a);
	scanf("%c", &buffer);
	scanf("%d", &b);
	scanf("%c", &buffer);
	quotient = a / b;
	reste = a % b;
	quo_ra = a / b;
	printf("%d\n", quotient);
	printf("%d\n", reste);
	printf("%.2f\n", quo_ra);
}
