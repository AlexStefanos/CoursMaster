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

void readMdj() {
  struct sembuf op;

  op.sem_num = MUTEX;
  op.sem_op = -1;
  op.sem_flg = 0;
  semop(semid, &op, 1);
  if(*(var = (int *)shmat(shmid, NULL, 0)) == -1) {
    fprintf(stderr, "Probleme shmat\n");
    exit(4);
  }
  start = var;
  redac = *var;
  var++;
  demandeRedac = *var;
  if(redac || demandeRedac) {
    var = start;
    var++;var++;
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
    op.sem_num = NBLECTEUR;
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
    var = start;
    var++;var++;
    (*var)--;
  }
  var = start;
  var++;var++;var++;
  (*var)++;
  var = start;
  if(shmdt(var) == -1) {
    fprintf(stderr, "Probleme shmdt\n");
    exit(5);
  }
  op.sem_num = MUTEX;
  op.sem_op = -1;
  op.sem_flg = 0;
  semop(semid, &op, 1);
  FILE *fic;
  if((fic = fopen("/tmp/motdj", "r")) == NULL) {
    char str[4096];
    while((fgets(str, 4096, fic) != (char *)NULL)) {
      puts(str);
      sleep(5);
    }
  }
  fclose(fic);
  op.sem_num = MUTEX;
  op.sem_op = -1;
  op.sem_flg = 0;
  semop(semid, &op, 1);
  if(*(var = (int *)shmat(shmid, NULL, 0)) == -1) {
    fprintf(stderr, "Probleme shmat\n");
    exit(5);
  }
  start = var;
  var = start;
  var++;var++;var++;
  (*var)--;
  lect = *var;
  var = start;
  var++;
  demandeRedac = *var;
  if(lect == 0 && demandeRedac) {
    op.sem_num = NBREDACTEUR;
    op.sem_op = 1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
  }
  var = start;
  op.sem_num = MUTEX;
  op.sem_op = 1;
  op.sem_flg = 0;
  semop(semid, &op, 1);
}

int main(int argc ,char **argv) {
  if((cle=ftok("/tmp/invite",'0')) == -1 ) {
    fprintf(stderr,"Probleme sur ftoks\n");
    exit(1);
  }  
  if((semid=semget(cle,2,0))==-1) {
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  } 
  if((shmid=shmget(cle,1024,0))==-1) {
    perror("shmget");
    fprintf(stderr,"Probleme sur shmget\n");
    exit(2);
  }
  readMdj();  
}