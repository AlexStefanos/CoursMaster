#include "stdlib.h"
#include "stdio.h"
#include "unistd.h"
#include "sys/wait.h"
#include "sys/types.h"

int main(int argc, char const *argv[])
{
	int n, status, tabPID[5];

	if(argc == 2)
		if(atoi(argv[1]) < 5)
			n=atoi(argv[1]);
		else
			n=5;
	else
		n=5;

	for (int i = 0; i < n; ++i)
	{
		tabPID[i] = fork();

		if(tabPID[i] == 0) //Fils
		{
			sleep(1);
			exit(i+1);
		}
	}

	//Père
	for (int i = 0; i < n; ++i)
	{
		wait(&status);
		printf("Le processus %d s'est arrêté avec la valeur %d", tabPID[WEXITSTATUS(status) - 1], WEXITSTATUS(status));

		if(WIFSIGNALED(status) != 0)	//tué à cause d'un signal
			printf(" à cause du signal %d.\n", WTERMSIG(status));
		else
			printf(".\n");
	}

	return 0;
}