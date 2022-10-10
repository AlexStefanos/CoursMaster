#include<unistd.h>
#include<fcntl.h>
#include<stdio.h>
#include <sys/types.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <stdlib.h>

int main ( int argc , char **argv ) {
  key_t cle;
  int semid;
  int shmid;

  if((cle=ftok("/tmp/invite",'0')) == -1 ) {
    fprintf(stderr,"Probleme sur ftoks\n");
    exit(1);
  }
  if ((semid=semget(cle,2,0))==-1) {
    perror("semget");
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  }
  semctl(semid,0,IPC_RMID,0); 
  if((shmid=shmget(cle,1024,0))==-1) {
    perror("shmget");
	  fprintf(stderr,"Probleme sur shmget\n");
	  exit(2);
  }
  shmctl(shmid,0,IPC_RMID,0);
}
