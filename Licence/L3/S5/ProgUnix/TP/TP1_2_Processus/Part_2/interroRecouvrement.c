#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int n = atoi(argv[1]);
    if(n > 0) {
        printf("%s : Nb recouvrements restants %d", argv[0], n);
        char nb[10];
        sprintf(nb, "%d", n-1); //génère le tableau de int à partir de la chaîne de caractère nb
        execl(argv[0], argv[0], nb, NULL); //il écrase le code et se recharge avec lui même (à mieux comprendre)
    }
    printf("%s : terminé\n", argv[0]);
    return(EXIT_SUCCESS);
}
