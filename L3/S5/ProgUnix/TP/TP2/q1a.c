#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main (int argc, char **argv) {
    //char *cde;

/*    if (argc != 2) {
        fprintf(stderr, "Usage %s commande\n", argv[0]);
        exit(1);
    } à refaire en fonction des commandes que l'on veut exécuter. Par exemple, pour ps on veut un 3 argument*/

   // execl(argv[1], argv[1], NULL);
    //perror(argv[1]);
    fork();
    fork();
    fork();

    printf("Bonjour\n");
    exit(0);
}
