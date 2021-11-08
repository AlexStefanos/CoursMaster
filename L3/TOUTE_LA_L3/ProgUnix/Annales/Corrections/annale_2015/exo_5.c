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
	if (argc!=4)
	{
		fprintf(stderr, "Usage : %s m n i \n", argv[0]);
		exit(EXIT_FAILURE);
	}

	int j , k;
	int m = atoi(argv[1]);
	int n = atoi(argv[2]);
	int i = atoi(argv[3]);
	int pid_pere = getppid();
	int pid_famille = getpid();
	int pid_fils[m];
	int pid_petit_fils[n];
	printf("Je suis famille mon pid est %d mon pere est %d\n", pid_famille, pid_pere);
	
	for(j=0;j<m;j++)
	{
		if((pid_fils[j]=fork())<0)
		{
			perror("Erreur lors du fork pour le fils");
			exit (EXIT_FAILURE);
		}

		else if(pid_fils[j]==0)
		{
			printf("\tJe suis F%d mon pid est %d mon pere est %d et mon grand pere est %d\n", j+1, getpid(), pid_famille, pid_pere);
			if(j==i)
			{
				for(k=0;k<n;k++)
				{
					if((pid_petit_fils[k]=fork())<0)
					{
						perror("erreur lors du fork pour le petit fils");
						exit(EXIT_FAILURE);
					}

					else if (pid_petit_fils[k]==0)
					{
						printf("\t\tJe suis F%d.%d mon pid est %d et mon pere est %d grand pere est %d\n", j+1, k+1, getpid(), getppid(), pid_famille);
						exit(EXIT_SUCCESS);
					}
				}
			}
			exit(EXIT_SUCCESS);
		}
	}

	while(wait(NULL)>0);
	//sleep(1);
	printf("FIN FAMILLE\n");

	exit(EXIT_SUCCESS);
}
