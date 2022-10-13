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
int semid, shmid;
int *var, *start;

int main ( int argc , char **argv ) {
  system("touch /tmp/motdj");
  ushort init_sem[3] = {1, 1, 1};
  if((cle = ftok("/tmp/motdj", '0')) == -1) {
    fprintf(stderr,"Probleme ftoks\n");
    exit(1);
  }
  printf("Initialisation  des semaphores\n");
  if((semid = semget(cle, 3, IPC_CREAT|0777))==-1) {
    fprintf(stderr,"Probleme semget\n");
    exit(2);
  }
  printf("Creation du segment partagé\n");
  if((shmid = shmget(cle, 4*sizeof(int), IPC_CREAT|0666)) == -1) {
    fprintf(stderr,"Probleme shmget\n");
    exit(3);
  }
  if(*(var = (int *)shmat(shmid, NULL, 0)) == -1) {
    fprintf(stderr, "Probleme shmat\n");
    exit(4);
  }
  start = var;
  *var = 0;var++;
  *var = 0;var++;
  *var = 0;var++;
  *var = 0;
  if(shmdt(start) == -1) {
    fprintf(stderr, "Probleme shmat\n");
    exit(4);
  }
}
