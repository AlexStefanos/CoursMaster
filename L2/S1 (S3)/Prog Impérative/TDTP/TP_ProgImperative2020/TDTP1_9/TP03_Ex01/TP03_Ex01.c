/*
 * TP3_Ex01.c
 *
 *  Created on: 7 oct. 2020
 *      Author: alexandre
 */

#include <stdlib.h>
#include <stdio.h>

int	main()
{
	int x = 15;
	int y = 100;
	int *pInt = &x;
	int *ptrInt = (int *) malloc(1);
	if (ptrInt = NULL)
	{
		printf("T'as rate bouffon");
		return (0);
	}
	x = 2;
	printf("%d\n", *pInt);
	printf("%d\n", pInt);
	printf("%d\n", *ptrInt);
	printf("%d\n", ptrInt);
	printf("T'as reussi bg");
}
