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

key_t cle;
int semid;
int shmid;

int main ( int argc , char **argv ) {
  if((cle = ftok("/tmp/invite", '0')) == -1 ) {
    fprintf(stderr,"Probleme sur ftoks\n");
    exit(1);
  }
  if ((semid = semget(cle, 2, 0)) == -1) {
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  }
  semctl(semid, 0, IPC_RMID, 0); 
  if((shmid = semget(cle, 3, 0)) == -1) {
	  fprintf(stderr,"Probleme sur shmget\n");
	  exit(3);
  }
  semctl(shmid, 0, IPC_RMID, 0);
}
