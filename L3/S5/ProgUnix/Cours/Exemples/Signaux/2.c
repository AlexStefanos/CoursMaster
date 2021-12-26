#include <stdio.h>
#include <signal.h>
#include <unistd.h>

int compteur, captation = 0;

void traiter_signal(int sig) {
    printf("Gestionnaire Compteur : %d\n", compteur);
    captation = 1;
}

int main(int argc, char **argv) {
    for(;;) {
        compteur++;
        if(captation) {
            printf("Main : Compteur après captation %d\n", compteur);
            captation = 0;
        }
    }
}
