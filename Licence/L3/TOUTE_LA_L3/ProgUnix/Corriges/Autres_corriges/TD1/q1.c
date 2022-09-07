#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"

int main(int argc, char const *argv[])
{
	int countF, countP;
	int pid;

	if (argc > 2)
	{
		countP = atoi(argv[1]);
		countF = atoi(argv[2]);
	}
	else
	{
		countP = 20;
		countF = 10;
	}

	switch(pid = fork())
	{
		case(-1):
			perror("Erreur lors de l'execution du fork");
			break;

		case(0):	//fils
			for (int i = 0; i < countF; ++i)
			{
				printf("**********Je suis le fils de pid %d\n", getpid());
				printf("**********Mon père est de pid %d\n", getppid());
			}
			exit(0);
			break;

		default:	//pere
			for (int i = 0; i < countP; ++i)
			{
				printf("Je suis le père de pid %d\n", getpid());
				printf("Je suis le père, mon fils est de pid %d\n", pid);
			}
			break;
	}
	return 0;
}