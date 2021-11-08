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

int main(int argc, char *argv[])
{

	if(argc!=2)
	{
		fprintf(stderr, "Usage : %s nb_ps\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	int nb_ps = atoi(argv[1]);
	int i;
	int pid[nb_ps];
	for(i=0; i<nb_ps; i++)
	{	
		sleep(0.5);
		if((pid[i]=fork())<0)
		{
			perror("Erreur lors du fork");
			exit(EXIT_FAILURE);
		}

		else if(pid[i]==0)
		{
			execlp("ps", "ps", NULL);
			perror("pbm execlp");
		}
	}
	while(wait(NULL)>0);
	printf("FIN DES %d EXECUTION DE PS\n", nb_ps);
	return 0;	
}