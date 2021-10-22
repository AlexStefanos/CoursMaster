#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"

int main(int argc, char const *argv[])
{
	if (argc < 2)
	{
		printf("1 argument minimum\n");
	}
	else
	{
		//EXECL
		int pid = fork();
		
		if (pid == 0) //fils
		{
			execl(argv[1], "Arnaud", "Catalina", "Pierre", "Paul", "Jacques");
			perror("Problème lors du execl");
		}
		else if(pid == -1)
			perror("Problème lors du 1er fork");
		

		//EXECV
		pid = fork();	//père

		if(pid == 0)	//fils
		{
			char * argv_exec[32];
			int i;

			for(i=0; i<argc; i++)	//remplis argv_exec des arguments à executer de argv (mais pas le argv[1])
				argv_exec[i] = argv[i+1];
			argv_exec[i] = NULL;	//on ne doit pas sortir

			execv(argv[1], argv_exec);
			perror("Problème lors du execl");
		}
		else if(pid == -1)
			perror("Problème lors du 2eme fork");
	}
}