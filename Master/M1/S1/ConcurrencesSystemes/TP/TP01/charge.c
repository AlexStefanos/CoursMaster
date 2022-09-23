#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <time.h>
#include <sys/resource.h>

void openFile(double user, double nice, double sys, double tmp) {
    FILE *fichier;

    fichier = fopen("/proc/stat", "r");
    fscanf(fichier, "cpu %lf %lf %lf %lf", &user, &nice, &sys, &tmp);
    fclose(fichier);
    sleep(1);
}

double getStats() {
    double cpu, user, nice, sys, tmp, tmp2, idle, idle2;

    openFile(user, nice, sys, tmp);
    idle = user + nice + sys;
    openFile(user, nice, sys, tmp);
    idle2 = user + nice + sys;
    cpu = (tmp2-tmp) / (tmp2 - tmp + tmp2 - idle);
    printf("CPU = %lf \n", cpu);
    return(cpu);
}

int main(int argc, char **argv) {
    int i, nb;
    int pid[] = {};
    double charge, demande, duree, decalage;

    for(i = 0; i < nb; i++) {
        pid[i] = fork();
    }
    while(1) {
        charge = getStats();
        if((charge <= demande * 0.90) || (charge >= demande * 1.10)) {
            for(i = 0; i < nb; i++) {
                kill(pid[i], 9);
            }
            duree = duree + charge;
            if(duree <= (demande * 0.90))
                decalage = -decalage;
            else
                decalage = decalage;
            for(i = 0; i < nb; i++) {
                pid[i] = fork();
            }
        }
        for(i = 0; i < nb; i++) {
                kill(pid[i], 9);
        }
    }
}