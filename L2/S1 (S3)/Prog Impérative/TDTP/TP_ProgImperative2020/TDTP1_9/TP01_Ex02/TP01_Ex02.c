/*
 * TP01_Ex02.c
 *
 *  Created on: 24 sept. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int main()
{
	printf("La somme de 10 et 5 est 15\n");
	printf("La multplication de 10 par 5 est 50\n");
	printf("La division de 20 par 6 est 3.333\n");
	printf("10+5 = 15		10*5 = 50		20/6 = 3.333\n\n");
	int a = 10;
	int b = 5;
	int c = 20;
	int d = 6;
	int result1 = a + b;
	int result2 = a * b;
	float result3 = 3.333;
	printf("La somme de %d et %d est %d\n", a, b, result1);
	printf("La multplication de %d par %d est %d\n", a, b, result2);
	printf("La division de %d par %d est %.3f\n", c, d, result3);
	printf("%d+%d = %d		%d*%d = %d		%d/%d = %.3f", a, b, result1, a, b, result2, c, d, result3);
}
