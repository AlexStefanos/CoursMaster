#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

int num_process;

void gestionnaire(int signal);

int main(int argc, char **argv) {
    int i, j, k, kill_result;

    if(argc < 3) {
        fprintf(stderr, "Usage : %s n m\n", argv[0]);
        exit(1);
    }
    struct sigaction prepaSignal;
    prepaSignal.sa_handler = gestionnaire;
    prepaSignal.sa_flags = 0;
    sigemptyset(&prepaSignal.sa_mask);
    int sigaction_result = sigaction(SIGUSR1, &prepaSignal, NULL);

    if(sigaction_result < 0) {
        perror("Fonction sigaction() : ");
        exit(1);
    }
    int *fork_result_array = malloc((atoi(argv[1]) + 1) * sizeof(int));

    if(fork_result_array == NULL) {
        fprintf(stderr, "Erreur fonction malloc()");
        exit(1);
    }
    fork_result_array[1] = getpid();
    for(i = 2; i <= atoi(argv[1]); i++) {
        fork_result_array[i] = fork();
        if(fork_result_array[i] < 0) {
            perror("Fonction fork() : ");
            exit(1);
        }
        else {
            if(fork_result_array[i] == 0) {
                num_process = i;
                for(j = 1; j <= atoi(argv[2]); j++) {
                    printf("Attente du signal P%d \n", num_process);
                    pause();
                    kill_result = kill(fork_result_array[i-1], SIGUSR1);
                    if(kill_result < 0) {
                        perror("Fonction kill() : ");
                        exit(1);
                    }
                    printf("Envoi depuis P%d to P%d\n", num_process, (i-1));
                }
            }
        }
    }
    num_process = 1;
    for(k = 1; k <= atoi(argv[2]); k++) {
        kill_result = kill(fork_result_array[atoi(argv[1])], SIGUSR1);
        if(kill_result < 0) {
            perror("Fonction kill() : ");
            exit(1);
        }
        printf("Envoi depuis P%d à P%d\n", num_process, atoi(argv[1]));
        printf("Attente d'un signal - P%d\n", num_process);
        pause();
    }
}

void gestionnaire(int signal) {
    printf("P%d - réceptionne signal %d\n", num_process, signal);
}
