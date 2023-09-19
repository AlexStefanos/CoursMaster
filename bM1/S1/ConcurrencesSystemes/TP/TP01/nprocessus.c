#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <stdio.h>
#include <time.h>
#include <sys/resource.h>

int unsigned getRealClock() {
    return((long unsigned) time(0));
}

int unsigned getClockCpu() {
    return(clock()/CLOCKS_PER_SEC);
}

int calcul() {
    int x;

    for(int i = 0; i < 1000; i++) {
        for(int j = 1; j < 10000000; j++) {
            x = (i*i)/(j*j);
        }
    }
}

int main(int argc, char **argv) {
    int n, pid, tmp;
    int unsigned heureDebut, heureDebutCPU, heureFin, heureFinCPU, tempsTotal, tempsTotalCPU;

    if(argc < 2) {
        printf("Erreur args");
        return(-1);
    }
    else {
        n = atoi(argv[1]);
        heureDebut = getRealClock();
        heureDebutCPU = getClockCpu();
        for(int i = 0; i < n; i++) {
            if((i % 2) == 0) {
                pid = fork();
                if(pid == 0) {
                    setpriority(PRIO_PROCESS, getpid(), 10);
                    printf("pid : %d, priority : %d\n", getpid(), getpriority(PRIO_PROCESS, getpid()));
                    printf("%d\n", calcul());
                    exit(0);
                }
                else if(pid > 0) {}
                else {
                    printf("Erreur Fork");
                    exit(-1);
                }
            }
            else {
                pid = fork();
                if(pid == 0) {
                    printf("pid : %d, priority : %d\n", getpid(), getpriority(PRIO_PROCESS, getpid()));
                    printf("%d\n", calcul());
                    exit(0);
                }
                else if(pid > 0) {}
                else {
                    printf("Erreur Fork");
                    exit(-1);
                }
            }
        }
        heureFin = getRealClock();
        heureFinCPU = getRealClock();
        tempsTotal = heureFin - heureDebut;
        tempsTotalCPU = heureFinCPU - heureDebutCPU;
        printf("Temps total : %d, Temps total CPU : %d\n", tempsTotal, tempsTotalCPU);

        for(int i = 0; i < n; i++) {
            wait(NULL);
        }
    }
}