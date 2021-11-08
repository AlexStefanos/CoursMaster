#include "stdlib.h"
#include "stdio.h"
#include "unistd.h"

int main(int argc, char *argv[])
{
	//execl("ps", "ps", NULL);	//devrait marquer un msg d'erreur
	execlp("ps", "ps", NULL); //The initial argument for these functions is the pathname of a file which is to be executed.
}