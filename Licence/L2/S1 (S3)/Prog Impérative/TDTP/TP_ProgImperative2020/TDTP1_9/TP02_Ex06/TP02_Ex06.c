/*
 * TP2_Ex06.c
 *
 *  Created on: 29 sept. 2020
 *      Author: alexandre
 */

#include <stdio.h>
#include <math.h>

int	main()
{
	char buffer;
	int a;
	int b;
	int c;
	int det;
	int x1;
	int x2;
	float x0;

	scanf("%d", &a);
	scanf("%c", &buffer);
	scanf("%d", &b);
	scanf("%c", &buffer);
	scanf("%d", &c);
	det = b*b - 4 * a * c;
	if (det > 0)
	{
		x1 = (b * -1) - sqrt(det);
		x2 = (b * -1) + sqrt(det);
		printf("x1 = %d\n", x1);
		printf("x2 = %d\n", x2);
	}
	else if (det == 0)
	{
		x0 = (b * -1) / (2 * a);
		printf("x0 = %.2f\n", x0);
	}
	else
		printf("Les valeurs trouvées ne sont pas des racines réelles");
}
