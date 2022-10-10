#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdlib.h>

#define MUTEX 0
#define NBLECTEUR 1

int * nbLecteur; 

int main ( int argc , char **argv ) {
  key_t cle;
  int semid;
  int shmid;

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
  if((nbLecteur=(int*)shmat(shmid,NULL,0))==(int *)-1) {
    perror("shmat ");
    fprintf(stderr,"Probleme sur shmat\n");
    exit(3);
  }   
  struct sembuf op;
  op.sem_num=NBLECTEUR;op.sem_op=-1;op.sem_flg=0;
  semop(semid,&op,1);
  (*nbLecteur)++;
  if((*nbLecteur) == 1) {  
    op.sem_num=MUTEX;op.sem_op=-1;op.sem_flg=0;
    semop(semid,&op,1);
  }
  op.sem_num=NBLECTEUR;op.sem_op=1;op.sem_flg=0;
  semop(semid,&op,1);
  printf("Lecteur du texte \n");
  {
    FILE *fic;
    if ((fic=fopen("/tmp/invite","r"))!=NULL) {
      char str[4096];
      while((fgets(str,4096,fic) != (char*)NULL)) {
			  printf("%s",str);
			  sleep(5);
		  }
		fclose(fic);   
	  }
  }
  op.sem_num=NBLECTEUR;op.sem_op=-1;op.sem_flg=0;
  semop(semid,&op,1);
  (*nbLecteur)--;   
  if ((*nbLecteur)==0) {
    op.sem_num=MUTEX;op.sem_op=1;op.sem_flg=0;
    semop(semid,&op,1);   
  }
  shmdt(nbLecteur);
  op.sem_num=NBLECTEUR;op.sem_op=1;op.sem_flg=0;
  semop(semid,&op,1);
  printf("Fin Lecteur\n");     
} 

