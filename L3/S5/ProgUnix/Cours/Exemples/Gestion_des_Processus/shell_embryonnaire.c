#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#define NB_ARGUMENTS 16

int main(int argc, char **argv) {
	char *argv_exec[NB_ARGUMENTS];
	int indice;

	if (argc < 2) {
		printf("Usage : programme commande liste_arguments\n");
		exit(1);
	}

	for (indice = 0; indice < argc; indice++)
		argv_exec[indice] = argv[indice + 1];
	argv_exec[indice] = NULL;

	execvp(argv_exec[0], argv_exec);
	perror(argv_exec[0]);
	exit(2);
}
