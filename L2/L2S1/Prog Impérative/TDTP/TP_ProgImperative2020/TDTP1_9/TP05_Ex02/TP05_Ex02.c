/*
 * TP05_Ex02.c
 *
 *  Created on: 20 oct. 2020
 *      Author: alexandre
 */

#include <stdio.h>

void	Swap(int *x, int *y)
{
	int z;

	z = *x;
	*x = *y;
	*y = z;
	printf("%d\n%d\n", x, y);
}

int	main()
{
	int *param1;
	int *param2;
	char buffer;

	scanf("%d", &param1);
	scanf("%c", &buffer);
	scanf("%d", &param2);
	scanf("%c", &buffer);
	printf("%d\n%d\n", param1, param2);
	Swap(&param1, &param2);
	printf("%d\n%d\n", param1, param2);
}
