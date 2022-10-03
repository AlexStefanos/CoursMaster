#include <unistd.h> 
#include <fcntl.h>  
#include <stdio.h> 
#include <stdlib.h>
#include <sched.h>
#include <sys/types.h>
#include <string.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/wait.h>

key_t cle;

int semid;
int tab[10] = {0};

int fic2tab(char *pathname, int *tab, int size) {
    int cible;

    if((cible = open(pathname, O_RDONLY) < 0)) {
        fprintf(stderr, "Erreur lors de l'ouverture du fichier\n");
        return(-1);
    }
    if(((read(cible, tab, (size+1) * sizeof(int))) != ((size + 1) * sizeof(int)))) {
        fprintf(stderr, "Erreur lors de la lecture du fichier\n");
        return(-1);
    }
    close(cible);
    return(0);
}

int tab2fic(char *pathname, int *tab, int size) {
    int cible;

    if((cible = open(pathname, O_WRONLY|O_CREAT|O_TRUNC, 0666)) < 0) {
        fprintf(stderr, "Erreur lors de l'ouverture du fichier\n");
        return(-1);
    }
    if(write(cible, tab, (size + 1) * sizeof(int)) != (size + 1) * sizeof(int)) {
        fprintf(stderr, "Erreur lors de l'écriture du fichier\n");
        return(-1);
    }
    close(cible);
    return(0);
}

int main(int argc, char **argv) {
    int pid, conso, i = 0;
    ushort init_sem[3] = {1};

    cle=ftok(argv[1], '0');
    if(cle == -1) {
        fprintf(stderr, "Erreur fotk\n");
        exit(1);
    }
    semid = semget(cle, 3, IPC_CREAT|IPC_EXCL|0666);
    if(semid == -1) {
        fprintf(stderr, "Erreur semget\n");
        exit(2);
    }
    if(semctl(semid, 3, SETALL, init_sem) == -1) {
        fprintf(stderr, "Erreur semctl\n");
        exit(3);
    }
    while(1) {
        struct sembuf op;

        op.sem_num = 0;
        op.sem_op = -1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        op.sem_num = 1;
        op.sem_op = -1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        fic2tab(argv[1], tab, 10);
        if(tab[0] > 0) {
            conso = tab[1];
            tab[0]--;
            memmove(tab + 1, tab + 2, tab[0]*sizeof(int));
            tab2fic(argv[1], tab, 10);
        }
        op.sem_num = 1;
        op.sem_op = -1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        op.sem_num = 0;
        op.sem_op = -1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        if(conso == 49) {
            return(0);
        }
    }
}