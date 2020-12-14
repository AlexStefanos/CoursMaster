/*
 * TP2_Ex03.c
 *
 *  Created on: 29 sept. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int main()
{
	float aire_carree;
	float aire_cercle;
	float result;
	int c;

	scanf("%d", &c);
	aire_carree = c * c;
	aire_cercle = (c / 2) * 3.14;
	result = aire_carree - aire_cercle;
	printf("%.2f", result);
}
