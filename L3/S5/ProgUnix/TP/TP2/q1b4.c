#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main (int argc, char **argv) {
    if (argc < 2) {
        fprintf(stderr, "Usage %s commande\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    execvp(argv[1], &argv[1]);
    perror("Erreur execvp");
    exit(EXIT_FAILURE);
}
