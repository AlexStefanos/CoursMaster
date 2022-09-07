#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(int argc, char **argv, char **arge) {
    char *env[3];

    printf("--------------------------------------------------Affichage de l'environnement avant l'execle--------------------------------------------------\n");
    for(;arge != NULL; *arge++) {
        printf("%s\n", *arge);
    }

    //Nouvel environnement pour le programme qui va être chargé
    env[0] = "VARENV1=MENFOU1";
    env[1] = "VARENV2=MENFOU2";
    env[2] = NULL;

    printf("--------------------------------------------------Affichage de l'environnement après l'execle--------------------------------------------------\n");
    execle("./q1c2", "q1c2", NULL, env);

    perror("q1c2");
    exit(EXIT_FAILURE);
}
