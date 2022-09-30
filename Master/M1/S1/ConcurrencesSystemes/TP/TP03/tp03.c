/* cat > /tmp/mdj           Ctrl+D
segment partagé
    <-  NbL = 0
semaph
mutex info
mutex NbL =
Lect
    P(&mutexNbL)
        NbL
        if(NbL == 1)      | fop
                          |  w
            P(mutexInfo)  |   fget
    V(&mutexNbL)           |   sleep(1)
-> cat -----------------> | fcl
    P(&mutexNbL)
    NbL--
    if(NbL == 0)
        V(mutexInfo)
    V(&mutexNbL)

init
    c = ftok("init", '0')
    semit somye(2, IPCREAT066, ) semget partagé
    shmget(c, SETALL, [1, 1])
    int *NbL = shmat(shmid, 0, 0)
    *NbL = 0
    shmdet(stmid)

Redact
    P(&mutexInfo)
    cat > ...
    V(&mutexInfo)
*/

#include <unistd.h>
#include <fcntl.h> 
#include <stdio.h>
#include <stdlib.h>
#include <sched.h> 
#include <sys/types.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/wait.h>

#define mutexNbL 0      //MUTEX 0
#define mutexInfo 1    //NBLECTEUR 1

int semid;

void lecteur(){
    int nbL;
    struct sembuf op;

    op.sem_num = mutexNbL;
    op.sem_op = -1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
    nbL++;
    if(nbL == 1) {
        op.sem_num = mutexInfo;
        op.sem_op = -1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
    }
    op.sem_num = mutexNbL;
    op.sem_op = 1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
    //cat...
    op.sem_num = mutexNbL;
    op.sem_op = -1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
    nbL--;
    if(nbL == 0) {
        op.sem_num = mutexInfo;
        op.sem_op = 1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
    }
    op.sem_num = mutexNbL;
    op.sem_op = 1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
}

void redacteur() {
    struct sembuf op;

    op.sem_num = mutexInfo;
    op.sem_op = -1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
    //cat
    op.sem_num = mutexInfo;
    op.sem_op = 1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
}

void initialisation() {
    
}

int main(int argc, char **argv) {
    FILE *fic;
    if((fic = fopen("/tmp/motdj", "r")) != NULL) {
        char str[4096];
        while((fgets(str, 4096, fic) != (char *)NULL)) {
            puts(str);
            sleep(5);
        }
    }
    fclose(fic);
    /* cette partie de vérification est dans chaque fichier 
    création du fichier s'il n'existe pas (system(touch...))
    if(cle=ftok("tmp/invite", '0') == -1)
    if(cle2 = ftok("tmp/invite", '1') == -1)
    
    création du

    demande shmget
    if(shmid = shmget(cle, 1024, IPC_CREAT|0777) == -1) {
        perror("shmget");
        fprintf(stderr, "Probleme sur shmget \n");
        exit(2);
    }

    initialisation de l'ensemble
    ushort init_sem[2] = {1, 1};
    if(semctl(semid, 2, SETALL, init_sem) == -1) {
        fprintf(stderr, "");
        exit(3);
    }

    int *val;
    if((val = (int*)shmat(shmid, NULL, 0))) {

    }
    libération du sémaphore
    shmctl(shmid, 0, IPC_RMID, 0)
    */
   
}