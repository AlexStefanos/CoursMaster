#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main (int argc, char **argv) {
    //char *cde;

    if (argc < 2) {
        fprintf(stderr, "Usage %s commande\n", argv[0]);
        exit(1);
    }

    execvp(argv[1], &argv[1]);
    perror("Erreur execvp");
}
