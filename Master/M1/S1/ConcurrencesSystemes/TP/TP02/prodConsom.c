#include <unistd.h>
#include <fcntl.h> 
#include <stdio.h>
#include <stdlib.h>
#include <sched.h> 
#include <sys/types.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/wait.h>

#define MUTEX 0
#define PLACE 1
#define ARTICLE 2
#define MAX 128

key_t cle;
int semid;

/*
création d'un ensemble de 3 sémaphores : semid = semget(IPC_PRIVATE, 3, IPC_CREAT|0666) == -1)
initialisaition des 3 sémaphores
ushort init sem[3] = (1, MAX, 0)
if(semctl(semid, 3 , sETALL? init sem == -1)
    ...
void consommateur() {}
destruction de l'ensemble des semaphores semctl()               + finir de supprimer les restants à la main : ipcs (afficher)         ipcrm x
P(mutex) :
    op.sem_num = PLACE; op.sem_op = -1; op.sem_flg = 0;
    cr = semop(semid, &op, 1);
    if(cr != 0) perror ("P MUTEX");
op.sem_op et op.sem_flg toujours les mêmes valeurs dans son prog, op.semnum = PLACE ou op.semnum = MUTEX ou op.semnum = ARTICLE
le fichier doit s'arrêter avec un CTRL+C

initProdCons.c : crée l'ensemble de sémaphores (prod et conso)
producteur.c : dépose les objets 

RENDU : consommateur.c, initProdCons.c, prod-cons-fic2tab.c, producteur.c (+ clean.c peut-être utile)
    des 2 côtés il faut faire des semget(.., 0,...) pour pas qu'il ne reproduise des semaphores mais qu'il se serve des autres
*/

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

void deposer(char *str, int *val) {
    int f, nb;
    char buf[MAX];

    f = open(str, O_RDONLY);
    if(f >= 0) {
        while((nb = read(f, buf, MAX)) > 0) {
            struct sembuf op;
            op.sem_num = 0;
            op.sem_op = -1;
            op.sem_flg = 0;
            semop(semid, &op, 1);
            write(1, buf, nb);
        }
        close(f);
    }
}

void descripFic(char *str) {
        int f, nb;
        char buf[MAX];

        f = open(str, O_RDONLY);
        if(f >= 0) {
            while((nb = read(f, buf, MAX)) > 0) {
                struct sembuf op;
                op.sem_num = 0;
                op.sem_op = 1;
                op.sem_flg = 0;
                semop(semid, &op, 1);
                write(1, buf, nb);
                op.sem_num = 0;
                op.sem_op = 1;
                op.sem_flg = 0;
                semop(semid, &op, 1);
            }
            close(f);
        }
}

void prod() {
    int val = 0;

    while(1) {
        /*P(PLACE)
        op.sem_num = PLACE;
        op.sem_op = 1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        P(MUTEX);
        op.sem_num = MUTEX;
        op.sem_op = 1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
        deposer("data", &val);
        V(MUTEX);
        V(ARTICLE);
        val++;*/
    }
} 

int main(int argc, char **argv) {
    int pid;
    ushort init_sem[1] = {1};

    cle=ftok(argv[0], '0');
    if(cle == -1) {
        fprintf(stderr, "Erreur fotk()\n");
        exit(1);
    }
    semid = semget(cle, 1, IPC_CREAT|IPC_EXCL|0666);
    if(semid == -1) {
        fprintf(stderr, "Erreur semget()\n");
        exit(2);
    }
    if(semctl(semid, 1, SETALL, init_sem) == -1) {
        fprintf(stderr, "Erreur semctl");
        exit(3);
    }

}