#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <errno.h>

int a;

void traiter_signal(int signum) {
    int appel;
    appel = ++a;
    printf("Début gestionnaire ! (%d)\n", appel);
    sleep(5);
    printf("Fin gestionnaire ! (%d)\n", appel);
}

int main(int argc, char **argv) {
    struct sigaction fonc;

    fonc.sa_handler = traiter_signal;
    fonc.sa_flags = SA_NODEFER;
    if(sigaction(SIGINT, &fonc, NULL) == -1)
        perror("Erreur sigaction !\n");
    else
        printf("Installation du gestionnaire pour SIGINT\n");
    while(1) {
        a = 0;
        printf("Avant le \"pause\" !\n");
        pause();
        printf("Après\n");
    }
    return(0);
}
