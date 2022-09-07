#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main (int argc, char **argv) {
    char *cde;

    if (argc < 2) {
        fprintf(stderr, "Usage %s chemin commande\n", argv[0]);
        exit(EXIT_FAILURE);
    } //à refaire en fonction des commandes que l'on veut exécuter. Par exemple, pour ps on veut un 3 argument

    execv(argv[1], &argv[1]); //execv(argv[1], argv[1], NULL) donné en cours
    perror(argv[1]);
    exit(EXIT_FAILURE);
}
