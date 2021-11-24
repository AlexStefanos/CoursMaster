#include <stdio.h>
#include <stdlib.h>
#include <unsitd.h>
#include <signal.h>

void afficheMasque(char *msg, sigset_t *mask) {
    int i;
    sigset_t set_signaux;
    if(mask == NULL) {
        if(sigprocmask(0, NULL, &set_signaux) < 0) {
            perror("Sigpromask");
            exit(EXIT_FAILURE);
        }
    }
    else
        set_signaux = *mask;
    printf("%s{", msg);
    for(i = 1; i < NSIG; i++) {
        if(sigismember(&set_signaux, i)) {
            printf("%s ", strsignal[i]);
            printf("%d", i);
        }
    }
    printf("}\n");
}

void reception_signal(int nbSignal) {
    int expediteur;

    if(num == nbProcessus)
        expediteur = 1;
    else
        expediteur = num+1;
    printf("%sP%d : Recu signal de P%d", tab, num, expediteur);
    afficheMasque("- Masque -", NULL);
    nbSignal++;
}
