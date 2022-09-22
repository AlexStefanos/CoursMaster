#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <time.h>
#include <sys/resource.h>

double cpustat() {
    long int user, nice, syst, idle1, idle2, total, total2;
    double cpu;
    FILE *file;
    file = fopen("/prod/stat", "r");
    fscanf(file, "cpu %ld %ld %ld %ld", &user, &nice, &syst, &idle1);
    fclose(file);
    total = user + nice + syst;
    usleep(1000000);
    file = fopen("/proc/stat", "r");
    fscanf(file, "cpu %ld %ld %ld %ld", &user, &nice, &syst, &idle2);
    fclose(file);
    total2 = user + nice + syst;
    cpu = (total2-total) / (double)((idle2 - idle1) + (total2 - total));
    cpu *= 100;
    printf("cpu = %lf \n", cpu);
    return(cpu);
}

int main(int argc, char **argv) {
    int i, nb;
    int pid[] = {};
    double charge, demande, duree, step;

    for(i = 0; i < nb; i++) {
        charge = cpustat();
    }

    if(charge <= demande*0.95 || charge >= demande*1.05) {
        for(i = 0; i < nb; i++)kill(pid[i], 9);
        duree += charge <= demande * 0.95 ? -step: step;
        for(i = 0; i < nb; i++) pid[i] = codeFils(duree);
    }

    sleep(1);
}