#include <stdio.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>

void traiter_signal(int signum) {
    printf("Signal %s reçu !\n",strsignal(signum));
}

int main(int argc, char **argv) {
    signal(SIGINT, traiter_signal);
    while(1) {
        printf("Avant le \"pause\" !\n");
        pause();
        printf("Après\n");
    }
    return(0);
}
