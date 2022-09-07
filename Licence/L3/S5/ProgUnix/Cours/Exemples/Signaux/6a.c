#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <errno.h>

void traiter_signal(int signum) {
    printf("Début gestionnaire!\n");
    sleep(5);
    printf("Fin gestionnaire !\n");
}

int main(int argc, char **argv) {
    struct sigaction fonc;
    char buf[128];
    int r;
    fonc.sa_handler = traiter_signal;

    if(sigaction(SIGINT, &fonc, NULL) == -1)
        perror("Erreur sigaction !\n");
    else
        printf("Installation du gestionnaire pour SIGINT\n");

    printf("Saisir une valeur\n");
    r = read(STDIN_FILENO, buf, sizeof(buf));
    if(r < 0 && errno == EINTR)
        perror("Erreur read");
    else
        printf("Valeur lue : %s\n", buf);
    return(0);
}
