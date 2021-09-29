#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main (int argc, char **argv) {
    //char *cde;

/*    if (argc != 2) {
        fprintf(stderr, "Usage %s commande\n", argv[0]);
        exit(1);
    } à refaire en fonction des commandes que l'on veut exécuter. Par exemple, pour ps on veut un 3 argument*/

    execlp(argv[1], argv[1], NULL); //il ne prend pas en compte les param des commandes données en argument
    perror(argv[1]);
}
