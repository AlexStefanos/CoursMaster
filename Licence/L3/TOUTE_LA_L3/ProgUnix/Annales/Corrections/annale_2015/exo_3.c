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

	if(argc!=4)
	{
		fprintf(stderr, "Usage : %s nom_programme nb_exec_effectue nb_exec_a_effectuer\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	
	char *nom_programme = argv[1];
	int nb_exec_effectue = atoi(argv[2]);
	int nb_exec_a_effectuer = atoi(argv[3]);
	
	if(nb_exec_a_effectuer==0)
	{
		exit(EXIT_SUCCESS);
	}

	printf("%s : recouvrement n°%d\n", nom_programme, nb_exec_effectue+1);
	nb_exec_effectue++;
	nb_exec_a_effectuer--;
	printf("%s terminé\n", nom_programme);

	char ch_exec_effectue[2];
	char ch_exec_a_effectuer[2];
	sprintf(ch_exec_effectue,"%d", nb_exec_effectue);
	sprintf(ch_exec_a_effectuer, "%d", nb_exec_a_effectuer);

	execl(argv[0], nom_programme, nom_programme, ch_exec_effectue, ch_exec_a_effectuer, NULL);
	perror("pbm exec");
}