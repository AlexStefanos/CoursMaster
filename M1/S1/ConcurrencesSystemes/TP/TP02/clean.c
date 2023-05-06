#include <unistd.h>
#include <fcntl.h> 
#include <stdio.h>
#include <stdlib.h>
#include <sched.h> 
#include <sys/types.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/wait.h>

key_t cle;
int semid;

int main(int argc, char **argv) {
    int pid, conso, i = 0;
    ushort init_sem[3] = {1};

    cle=ftok(argv[1], '0');
    if(cle == -1) {
        fprintf(stderr, "Erreur fotk()\n");
        exit(1);
    }
    semid = semget(cle, 3, IPC_CREAT|IPC_EXCL|0666);
    if(semid == -1) {
        fprintf(stderr, "Erreur semget()\n");
        exit(2);
    }
    semctl(semid, 0, IPC_RMID, 0);
    if(semctl(semid, 3, SETALL, init_sem) == -1) {
        fprintf(stderr, "Erreur semctl");
        exit(3);
    }
    shmctl(semid, 0, IPC_RMID, 0);
}