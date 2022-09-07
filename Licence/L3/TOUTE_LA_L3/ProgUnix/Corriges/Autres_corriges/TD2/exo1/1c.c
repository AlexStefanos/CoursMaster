#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"
#include "sys/wait.h"


/*
Le path par défaut (dans notre variable d'environnement) est de ce style :
PATH=/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin:/opt/X11/bin
Il permet l'execution du ps sans avoir besoin d'écrire /bin/ps

Si on modifie sa variable d'environement pour avoir un path vide, l'execution du ps echouera

le cd est une commande interne du shell
*/
int main(int argc, char * argv[])
{
	int pid = fork();

	if(pid == 0)	//FILS
	{
		//Redéfinition du path
		char *env[] = { "PATH=","HOME=/usr/home", "LOGNAME=home", (char *)0 };

		printf("J'ai un environnement bidon avec un path vide qui ne me permet pas de trouver la commande ps :\n");
		execle("ps", "ps", NULL, env);
		
		//En cas d'echec de l'ececution du exec (c'est d'ailleur le cas)
		perror("ps");
	}
	else	//PERE
	{
		wait(1); //Afin d'éviter l'enchevetrement des 2 pss

		printf("\n\nJ'ai un super environnement avec un path qui me permet de trouver la commande ps :\n");
		execlp("ps", "ps", NULL);
		
		//En cas d'echec de l'ececution du exec
		perror("ps");
	}
}