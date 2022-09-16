#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <sched.h>
#include <fcntl.h>

int main(int argc, char **argv) {
    int nbTache, nbSecondes, fic, nbProcessus, n, i;
    char c;

    nbProcessus = 0;
    while(scanf("%c%u%u\n", &c, &nbTache, &nbSecondes) == 3) {
        printf("%c %u %u\n", c, nbTache, nbSecondes);
        nbProcessus++;
        if(fork() == 0) {
            for(n = nbTache; n > 0; n--) {
                printf("%c\n", c);
                fflush(stdout);
                sleep(nbSecondes);
            }
            exit(0);
        }
    }
    for(i = 0; i < nbProcessus; i++) {
        wait(NULL);
    }
}
