/*
 * TP05_Ex03.c
 *
 *  Created on: 20 oct. 2020
 *      Author: alexandre
 */

void	Min_Max(int a, int b, int c)
{
	int *min;
	int *max;

	if (a >= b && a >= c)
	{
		*max = a;
		if (b >= c)
			*min = c;
		else
			*min = b;
	}
	//faire la meme chose pour les autres cas
}
