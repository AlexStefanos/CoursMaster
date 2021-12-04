#include <unistd.h>
#include <signal.h>
#include <stdio.h>

#define NB_DONNEES 5
#define INIT_DELAI 2

struct gestion {
    int donnee;
    int nb_tentatives;
    int temps;
} tab[NB_DONNEES];

int delai, iDonnee;

void gestionSigalarm(int signal) {
    tab[iDonnee].nb_tentatives;
    tab[iDonnee].temps += delai;

    printf("Veuillez donner la donnée d'indice %d : \n", iDonnee);
    siglongjmp(env, 1);
}

int main(int argc, char **argv) {
    int save;
    action.sa_handler = interception_signal_sigalarm;
    sigemptyset(&action.sa_mask);
    for(iDonnee = 0; iDonnee < NB_DONNEES; iDonnee++) {
        tab[iDonnee].nb_tentatives = 1;
        tab[iDonnee].temps = 0;
        delai = INIT_DELAI;
        save = sigsetjmp(env, 1);
        printf("Veuillez donner la donnée d'indice %d : \n", iDonnee);
        alarm(delai);
        scanf("%d", &tab[iDonnee].donnee);
        tab[iDonnee].temps += (delai - alarm(0));
    }
    printf("Rang\tValeur\tTentatives\tTemps\n\n");
    for(iDonnee = 0; iDonnee < NB_DONNEES; iDonnee++)
        printf("%d\t%d\t%d\t\t%d\n", iDonnee, tab[iDonnee].donnee, tab[iDonnee].nb_tentatives, tab[iDonnee].temps);
}
