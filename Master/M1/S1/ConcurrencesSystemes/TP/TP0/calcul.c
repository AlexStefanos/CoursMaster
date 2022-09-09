#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <sched.h>
#include <fcntl.h>

int main(int argc, char **argv) {
    int i, nbProcessus, nb, compteurArgc, somme, pid;

    if(argc <= 2)
        printf("Erreur arg");
    else{
        compteurArgc = 1;
        somme = 0;
        for(nbProcessus = 0; nbProcessus < argc - 1; nbProcessus++, compteurArgc++) {
            pid = fork();
            if(pid == 0) {
                nb = atoi(argv[compteurArgc]);
                printf("nb : %d, pid = %d\n", nb, pid);
                exit(nbProcessus);
            }
        }
        wait(&nbProcessus);
        somme = somme + nb;
        printf("somme : %d, pid = %d\n", somme, pid);
    }

    for(i = 0; i < nbProcessus; i++) {
        wait(NULL);
    }
    printf("Somme : %d\n", somme);
}
