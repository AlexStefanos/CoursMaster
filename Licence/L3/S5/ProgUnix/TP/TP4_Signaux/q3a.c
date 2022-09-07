#include <stdio.h>
#include <signal.h>
#include <sys/types.h>

int nbSigusr1 = 0, nbSigusr2 = 0;

void sigusr1(int sig) {
    nbSigusr1++;
    printf("Nombre SIGUSR1 : %d\nSignal : %d (SIGUSR1)\n", nbSigusr1, sig);
}

void sigusr2(int sig) {
    nbSigusr2++;
    printf("Nombre SIGUSR2 : %d\nSignal : %d(SIGUSR2)\n", nbSigusr2, sig);
}

int main(int argc, char **argv) {
    signal(SIGUSR1, sigusr1);
    signal(SIGUSR2, sigusr2);
    for(;;)
        pause();
}