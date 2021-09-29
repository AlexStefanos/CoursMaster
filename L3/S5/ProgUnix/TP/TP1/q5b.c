#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

int main(int argc, char **argv) {
    int pid, i, code_retour, nb_fils, dors_fils;

    if (argc != 3) {
        fprintf(stderr, "Usage %s nombre_de_fils duree_sommeil_fils\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    nb_fils = atoi(argv[1]);
    dors_fils = atoi(argv[2]);

    for(i = 1; i <= nb_fils; i++) {
        pid = fork();
        if (pid < 0) {
            perror("fork");
            exit(EXIT_FAILURE);
        }
        else if (pid == 0) {
            printf("\nFils : mon PID est %d, je m'endors pour %d s\n", getpid(), dors_fils);
            sleep(dors_fils);
            exit(i);
        }
        else {
        }

    }

    for(i = nb_fils; i >= 1; i--) {
        pid = wait(&code_retour);
        if (pid == -1) {
            perror("wait");
            exit(EXIT_FAILURE);
        }
        printf("\nPère : mon fils %d s'est terminé.\n", pid);
        if (WIFEXITED(code_retour))
            printf("Le fils %d s'est terminé normalement et m'a retourné %d\n", getpid(),WEXITSTATUS(code_retour));
        else {
            printf("Le fils %d ne s'est pas terminé normalement et m'a retourné %d\n", getpid(),WEXITSTATUS(code_retour));
            if (WIFSIGNALED(code_retour)) {
                printf("\tIl a reçu le signal %d\n", WTERMSIG(code_retour));
            }
        }
        printf("\t: sa valeur de retour est %d\n", code_retour);
        printf("Encore %d fils\n", i-1);
    }
}
