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
#define NBLECTEUR 1
#define NBREDACTEUR 2

key_t cle;
int semid, shmid, redac, lect, demandeRedac, demandeLect;
int *var, *start;

void creatMdj() {
  struct sembuf op;

  op.sem_num = MUTEX;
  op.sem_op = -1;
  op.sem_flg = 0;
  semop(semid, &op, 1);
  if(*(var = (int *)shmat(shmid, NULL, 0)) == -1) {
    fprintf(stderr, "Probleme shmat\n");
    exit(5);
  }
  start = var;
  redac = *var;
  var++;
  demandeRedac = *var;
  var = start;
  var++;var++;var++;
  lect = *var;
  if(lect || redac || demandeRedac) {
    var = start;
    var++;
    (*var)++;
    var = start;
    if(shmdt(var) == -1) {
      fprintf(stderr, "Probleme shmdt\n");
      exit(5);
    }
    op.sem_num = MUTEX;
    op.sem_op = 1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
    op.sem_num = NBREDACTEUR;
    op.sem_op = -1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
    op.sem_num = MUTEX;
    op.sem_op = -1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
    if(*(var = (int *)shmat(shmid, NULL, 0)) == -1) {
      fprintf(stderr, "Probleme shmat\n");
      exit(5);
    }
    start = var;
    var++;
    (*var)--;
  }
  var = start;
  (*var)++;
  if(shmdt(var) == -1) {
    fprintf(stderr, "Probleme shmdt\n");
    exit(5);
  }
  op.sem_num = MUTEX;
  op.sem_op = -1;
  op.sem_flg = 0;
  semop(semid, &op, 1);
  system("cat > /tmp/motdj");
  op.sem_num = MUTEX;
  op.sem_op = -1;
  op.sem_flg = 0;
  semop(semid, &op, 1);
  if(*(var = (int *)shmat(shmid, NULL, 0)) == -1) {
    fprintf(stderr, "Probleme shmat\n");
    exit(5);
  }
  start = var;
  (*var)--;
  var = start;
  var++;
  if(*var) {
    op.sem_num = NBREDACTEUR;
    op.sem_op = 1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
  }
  else {
    var = start + 2;
    if(*var) {
      for(int nb; nb < *var; nb++) {
        op.sem_num = NBLECTEUR;
        op.sem_op = 1;
        op.sem_flg = 0;
        semop(semid, &op, 1);
      }
    }
  }
  var = start;
  if(shmdt(var) == -1) {
    fprintf(stderr, "Probleme shmdt\n");
    exit(5);
  }
  op.sem_num = MUTEX;
  op.sem_op = 1;
  op.sem_flg = 0;
  semop(semid, &op, 1);
}

int main(int argc ,char **argv) {
  struct sembuf op;

  if((cle = ftok("/tmp/invite",'0')) == -1 ) {
    fprintf(stderr, "Probleme sur ftoks\n");
    exit(1);
  }
  if((semid = semget(cle,2,0)) == -1) {
    fprintf(stderr, "Probleme sur semget\n");
    exit(2);
  }
  if((shmid = shmget(cle, 3, 0) == -1)) {
    fprintf(stderr, "Probleme shmget\n");
    exit(3);
  }
  creatMdj();
} 

