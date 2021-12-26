#include <signal.h>
#include <setjmp.h>
#include <stdlib.h>
#include <stdio.h>

sigjmp_buf env;
struct sigaction action;

void signal_FPE(int signal) {
    printf("Réception de SIGFPE\n");
    siglongjmp(env, 1);
}

int fonctionC(int a, int b) {
    return(a/b);
}

int fonctionB(int a, int b) {
    int r = fonctionC(a, b);
    printf("FonctionC retourne %d\n", r);
    return(r);
}

int fonctionA(int a, int b) {
    int r = fonctionB(a, b);
    printf("FonctionB retourne %d\n", r);
    return(r);
}

int main(int argc, char **argv) {
    int s, resultat;

    action.sa_handler = signal_FPE;
    sigaction(SIGFPE, &action, NULL);
    s = sigsetjmp(env, 1);
    if(s == 0) {
        printf("Retour direct de sigsetjmp\n");
        resultat = fonctionA(atoi(argv[1]), atoi(argv[2]));
        printf("fonctionA retourne %d\n", resultat);
    }
    else {
        printf("Retour de sigsetjmp par siglongjmp\n");
        printf("Division par 0 dans la fonction C\n");
    }
}
