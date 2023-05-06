#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <time.h>
#include <sys/resource.h>

int main(int argc, char **argv) {
    int pid, pid2, compteur;
    unsigned cpP;

    pid = fork();
    if(pid == 0) {
        pid_t sid = setsid();
        setpriority(PRIO_PROCESS, sid, 10);
        while(1) {
            if(pid == 0) {
                pid2 = fork();
                if(pid2 == 0) {
                    float j = 1;
                    for(int i = 0; i < 100000; i++)
                        j /= 3;
                    usleep(1000);
                }
                else if(pid2 > 0) {}
                else {
                    printf("Erreur fork");
                    exit(-1);
                }
            }
            else if(pid > 0) {}
            else {
                printf("Erreur fork");
                exit(-1);
            }
        }
    }
    else if(pid > 0) {}
    else{
        printf("Erreur fork");
        exit(-1);
    }
    getchar();
    kill(pid, -9);  
}