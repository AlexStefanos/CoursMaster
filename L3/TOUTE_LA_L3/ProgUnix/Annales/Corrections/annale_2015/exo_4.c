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

int main (int argc, char *argv[])
{
	if (argc<3)
	{
		fprintf(stderr, "Usage : %s nom_programme NOM1=VALEUR1 ...\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	char *tab_val[argc-2+1];

	int i=0;
	for (i = 0; i < argc-2; ++i)
	{
		tab_val[i]=argv[2+i];
	}

	tab_val[i]=NULL;

	execle(argv[1], argv[1], NULL, tab_val);
	perror("pbm execle");
}