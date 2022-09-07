/*
 * TP1_Ex04.c
 *
 *  Created on: 24 sept. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int	main()
{
	int a;
	int b;
	int S;
	int P;
	int C1;
	int C2;
	scanf("%d", &a);
	scanf("%d", &b);
	S = a + b;
	P = a * b;
	C1 = a * a;
	C2 = b * b;
	printf("S = %d\n", S);
	printf("P = %d\n", P);
	printf("C1 = %d\n", C1);
	printf("C2 = %d\n", C2);
}
