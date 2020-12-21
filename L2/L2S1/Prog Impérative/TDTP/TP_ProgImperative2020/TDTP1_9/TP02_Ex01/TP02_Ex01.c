/*
 * TP2_Ex01.c
 *
 *  Created on: 29 sept. 2020
 *      Author: alexandre
 */

#include <stdio.h>

int main()
{
	char buffer;
	int result;
	int a;
	int b;
	int c;
	int d;

	scanf("%d", &a);
	scanf("%c", &buffer);
	scanf("%d", &b);
	scanf("%c", &buffer);
	scanf("%d", &c);
	scanf("%c", &buffer);
	scanf("%d", &d);
	scanf("%c", &buffer);
	result = a + b + c + d;
	printf("%d\n", result);
	// b)
	scanf("%d", &a);
	scanf("%c", &buffer);
	result = a;
	scanf("%d", &a);
	scanf("%c", &buffer);
	result += a;
	scanf("%d", &a);
	scanf("%c", &buffer);
	result += a;
	scanf("%d", &a);
	scanf("%c", &buffer);
	result += a;
	printf("%d", result);
}
