#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"
#include "string.h"

#define MAX_NB_ARGS 1024
#define MAX_LONG_ARG 512

int main(int argc, char * argv[])
{	
	do{	//boucle infinie 

		int status;
		int bg;
		int i;
		char cmd[512];
		char ** argVs;


		printf("----->");

		//On récupère la chaine avec les espaces + \n
		fgets(cmd, sizeof cmd, stdin);

		//on remplace \n par \0
		i=0;
		while(cmd[i] != '\n')
			i++;

		cmd[i] = '\0';

		//on remplace & par \0 et on retient la présence du & dans variable bg
		if(cmd[i-1] == '&')
		{
			cmd[i-1] = '\0';
			bg = 1;
		}
		else
			bg = -1;

		//Initialisation tableau externe (tableau dynamique 2 dimensions)
		argVs = (char **)malloc(MAX_NB_ARGS * sizeof(char));
		
		//Initialisation tableaux internes
		for (i = 0; i < MAX_NB_ARGS; ++i)
		{
			argVs[i] = (char *)malloc(MAX_LONG_ARG * sizeof(char));
		}

		//Hashage de la chaine par mot délimité par un espace
		char * pch;
		i =0;

		pch = strtok (cmd," ");	//This function returns a pointer to the last token found in string. 
		argVs[i] = pch;			//A null pointer is returned if there are no tokens left to retrieve.
		
		i++;

		while (pch != NULL)
		{
			//printf ("%s\n",pch);
			pch = strtok (NULL, " ");
			argVs[i] = pch;

			i++;
		}

		argVs[i] = NULL;

		int pid = fork();
		if(pid == 0)	//Fils
		{
			execvp(argVs[0], argVs);	// = execvp(argVs[0], argVs[0], argVs[1], ... argVs[n])
			perror(argVs[0]);
		}

		//On attend le processus en fonction de la présence du &
		if (bg== -1)
		{
			wait(&status);	//wait si pas tache de fond
		}

	}while(1);

	return 0;
}