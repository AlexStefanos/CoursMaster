#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

void fin_A() {
    printf("Fonction A\n");
}

void fin_B() {
    printf("Fonction B\n");
}

int main(int argc, char **argv) {
    printf("Début\n");
    atexit(fin_A);
    atexit(fin_B); //c'est une pile

    printf("Programme terminé"); //pas de \n donc pas de vidage de stdout

    /* avec exit les buffers des fichiers ouverts sont vides et appelle
     * les fonctions dont les demandes d'exécution en cas de terminaisons
     * ont été formules avec atexit
     * _exit ne fait rien de tout ça, elle met simplement fin au processus*/
    exit(EXIT_SUCCESS);
    //_exit(EXIT_SUCCESS);
}
