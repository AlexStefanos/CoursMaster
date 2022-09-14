#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <sched.h>
#include <fcntl.h>

int gestionFamille(int nbProcessus, int somme, int compteurArgv, char **argv) {
    int pid, nb;
    if(nbProcessus != 0) {
        nbProcessus--;
        pid = fork();
        if(pid == 0) {
            nb = atoi(argv[compteurArgv]);
            compteurArgv--;
            gestionFamille(nbProcessus, somme, compteurArgv, argv);
            exit(nbProcessus);
        }
    }
    else{
        for(int i = len(argv); i > 0; i--) {
            wait(&i);
            somme += nb;
        }
    }
}

int main(int argc, char **argv) {
    int i, nbProcessus, nb, compteurArgc, somme, pid, pid2;

    if(argc <= 2)
        printf("Erreur arg");
    else{
        compteurArgc = 1;
        nb = atoi(argv[compteurArgc]);
        pid = fork();
        if(pid == 0) {
            for(nbProcessus = 0; nbProcessus < argc - 1; nbProcessus++, compteurArgc++) {
                pid2 = fork();
                if(pid2 == 0) {
                    nb = atoi(argv[compteurArgc]);
                    printf("nb : %d, pid = %d\n", nb, pid);
                    somme += nb;
                    printf("Somme : %d\n", somme);
                    exit(1);
                }
            }
            exit(0);
        }
        printf("Somme : %d\n", somme);
    }
}
