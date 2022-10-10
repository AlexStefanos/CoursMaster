#include<stdio.h>
#include <sys/types.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>

#define MUTEX 0
#define NBLECTEUR 1

int main ( int argc , char **argv ) {
  key_t cle, c;
  int semid;
  int shmid;
  
  mknod("/tmp/invite",S_IFREG|0666,0);
  if((cle=ftok("/tmp/invite",'0')) == -1 ) {
    fprintf(stderr,"Probleme sur ftoks\n");
    exit(1);
  }
  printf("Initialisation  des semaphores \n");
  if((semid=semget(cle,2,IPC_CREAT|0777))==-1) {
    perror("semget");
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  }
  printf("Creation du segment partagé \n");
  if ((shmid=shmget(cle,1024,IPC_CREAT|0777))==-1) {
    perror("shmget");
    fprintf(stderr,"Probleme sur shmget\n");
    exit(2);
  }
  {
    ushort init_sem [2]={1,1};
    if (semctl(semid,2,SETALL,init_sem)==-1) {
      fprintf(stderr,"Probleme sur semctl SETALL\n");
      exit(3);
    }
  }
  {
    int *val;
    if((val=(int*)shmat(shmid,NULL,0))==(int *)-1) {
      perror("shmat ");
      fprintf(stderr,"Probleme sur shmat\n");
      exit(3);
    } 
    printf("Initialisation  du segment partagé \n"); 
    *val =0;
    shmdt(val);
    c = ftok("init", '0');
    somye(2, IPC_CREAT|0666, 0);
    shmget(c, SETALL, (1, 1));
    int *NbL = shmat(shmid, 0, 0);
    shmdet(semid);
  }
  printf("Init mot du jour\n");
}
