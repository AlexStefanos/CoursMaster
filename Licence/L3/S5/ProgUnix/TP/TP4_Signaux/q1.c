#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char **argv) {
    int nb_sig, sig, pid;

    if(argc != 4) {
        fprintf(stderr, "Usage %s : signal pid nb_signal\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    printf(">> DEBUT <<<\n");
    sig = atoi(argv[1]);
    pid = atoi(argv[2]);
    for(nb sig = atoi(argv[3]); nb_sig > 0; nb_sig--) {
        if(kill(pid, sig) < 0)
            perror("kill");
            fprintf(stderr, "Erreur : %d signaux %d restant pour %d\n", nb_sig, sig, pid);
            exit(EXIT_FAILURE);
        else 
            printf("Signal %d envoyé à %d\n", sig, pid);
    }
    printf(">>> FIN : %s signaux %d envoyés à %d <<<\n", argv[3], sig, pid);
}