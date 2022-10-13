#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <stdlib.h>
#include <sched.h>
#include <sys/resource.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MUTEX 0
#define ESCLAVE 1
#define ELEMENTTAB 2
#define NBELEMENT 10

key_t cle;
int nbEsclave, semid, shmid;
int *tab;
struct sembuf op;

void affichage(char letter, int rep, int delai) {
    for(int i = 0; i < rep; i++) {
        write(1, &letter, 1);
        printf("\n");
        sleep(delai);
    }
}

void maitre() {
    char c;
    int rep, delai;
    while(scanf("%c%u%u", &c, &rep, &delai) == 3) {
        op.sem_num = MUTEX;
        op.sem_op = -1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        op.sem_num = ELEMENTTAB;
        op.sem_op = -1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        if(*(tab = (int *)shmat(shmid, NULL, 0)) == -1) {
            fprintf(stderr, "Probleme shmat\n");
            exit(4);
        }
        int pos = tab[0] * 3 + 2;
        tab[0] = (tab[0] + 1) % NBELEMENT;
        tab[pos] = (int)c;
        tab[pos + 1] = rep;
        tab[pos + 2] = delai;
        if(shmdt(tab) == -1) {
            fprintf(stderr, "Probleme shmdt\n");
            exit(5);
        }
        op.sem_num = ELEMENTTAB;
        op.sem_op = 1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        op.sem_num = MUTEX;
        op.sem_op = 1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
    }
}

void esclave() {
    while(1) {
        op.sem_num = ESCLAVE;
        op.sem_op = -1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        op.sem_num = MUTEX;
        op.sem_op = -1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        if(*(tab = (int *)shmat(shmid, NULL, 0)) == -1) {
            fprintf(stderr, "Probleme shmat\n");
            exit(5);
        }
        int posLect = tab[1] * 3 + 2;
        tab[1] = (tab[1] + 1) % NBELEMENT;
        char letter = tab[posLect];
        int rep = tab[posLect + 1], delai = tab[posLect + 2];
        if(shmdt(tab) == -1) {
            fprintf(stderr, "Probleme sur shmdt\n");
            exit(5);
        }
        op.sem_num = MUTEX;
        op.sem_op = 1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        op.sem_num = ESCLAVE;
        op.sem_op = 1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        affichage(letter, rep, delai);
    }
}

int main(int argc, char **argv) {
    if(argc != 2) {
        fprintf(stderr, "Probleme args\n");
        exit(-1);
    }
    nbEsclave = atoi(argv[1]);
    ushort init_sem[3] = {1, 0, NBELEMENT};
    if((cle = ftok(argv[0], '0')) == -1) {
        fprintf(stderr, "Probleme ftok\n");
        exit(1);
    }
    if((semid = semget(cle, 3, IPC_CREAT|0666)) == -1) {
        fprintf(stderr, "Probleme semget\n");
        exit(2);
    }
    if(semctl(semid, 3, SETALL, init_sem) == -1) {
        fprintf(stderr, "Probleme semctl\n");
        exit(3);
    }
    if((shmid = shmget(cle, sizeof(int)*2, IPC_CREAT|0666)) == -1) {
        fprintf(stderr, "Probleme shmget\n");
        exit(4);
    }
    if(*(tab = (int *)shmat(shmid, NULL, 0)) == -1) {
        fprintf(stderr, "Probleme shmat\n");
        exit(5);
    }
    int x;
    for(x = 0; x < (NBELEMENT * 3 + 2); x++) {
        tab[x] = 0;
    }
    if(shmdt(tab) == -1) {
        fprintf(stderr, "Probleme shmdt\n");
        exit(6);
    }
    int pid, pid2, i = 0;

    pid = fork();
    if(pid == 0) {
        while(i < nbEsclave) {
            pid2 = fork();
            if(pid2 == 0) {
                esclave();
                exit(0);
            }
            i++;
        }
    } else {
        maitre();
    }
    for(int nb = 0; nb < nbEsclave; nb++)
        wait(NULL);
    return(0);
}