#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <signal.h>
#include <string.h>
#include <time.h>
#include <sys/types.h>
#include <fcntl.h>

int main (int argc, char *argv[], char **arge)
{
	int i;
	printf("affiche env : \n");
	for(;*arge!=NULL;arge++)
	{
		printf("%s\n", *arge);
	}
}