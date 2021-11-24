#include "stdlib.h"
#include "stdio.h"

int main(int argc, char const *argv[])
{
	int i;

	printf("Je suis le programme de test ! Vous avez appelé : \n");

	for(i=1; i<argc; i++)
	{
		printf("%s\n", argv[i]);
	}

	return 1;
}