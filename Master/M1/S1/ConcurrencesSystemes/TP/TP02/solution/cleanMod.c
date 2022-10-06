
#include<unistd.h> // appel systeme fork
#include<fcntl.h>  // appel system unix ES
#include<stdio.h> // librairie standard C
#include <sys/types.h>
#include <sys/sem.h> // semaphore IPC
#include <sys/ipc.h> // services IPC
#include <stdlib.h>


                       // sur le fichier d'invite en priorite sur un lecteur


int main ( int argc , char **argv ) {
 
   key_t cle;             // cle IPC d'identification des semaphores
   int semid;             // nom local de l'ensemble des semaphores
   int shmid;


  // creation d'une cle IPC en fonction du nom du fichier d'invite
  if ((cle=ftok("/tmp/invite",'0')) == -1 ) {
    fprintf(stderr,"Probleme sur ftoks\n");
    exit(1);
  }
  
  // demande un ensemble de semaphores (LECTEUR, MUTEX et PROTECT)
  if ((semid=semget(cle,2,0))==-1) {
    perror("semget");
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  }
 /* liberation du s\'emaphore */
   semctl(semid,0,IPC_RMID,0);

   

 // demande shmget 
   if ((shmid=shmget(cle,1024,0))==-1) {
     perror("shmget");
	fprintf(stderr,"Probleme sur shmget\n");
	exit(2);

   }

 /* liberation du s\'emaphore */
   shmctl(shmid,0,IPC_RMID,0);


  }
