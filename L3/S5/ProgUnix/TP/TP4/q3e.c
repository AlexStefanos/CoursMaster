#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <signal.h>

int main(int argc, char **argv) {
    int nbSig, sig, pid;

    if(argc != 4) {
        fprintf(stderr, "Usage : %s : signal pid nbSignal\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    printf(">>> DEBUT <<<\n");
    sig = atoi(argv[1]);
    pid = atoi(argv[2]);
    for(nb_sig = atoi(argv[3]); nbSig > 0; nbSig--) {
        if(kill(pid, sig) < 0) {
            perror("kill");
            fprintf(stderr, "Erreur : %d signaux %d restant pour %d\n", nbSig, sig, pid);
            exit(EXIT_FAILURE);
        }
        else
            printf("Signal %d envoyé à %d\n", sig, pid);
    }
    printf(">>> FIN : %s signaux %d envoyés à %d<<<\n", argv[3], sig, pid);
}