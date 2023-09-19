
 
#include<stdio.h> // librairie standard C
#include <sys/types.h>
#include <sys/sem.h> // semaphore IPC
#include <sys/ipc.h> // services IPC
#include <sys/shm.h> // segment partage IPC
#include <fcntl.h>
 
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>



#define MUTEX 0        // semaphore pour le redacteur
#define NBLECTEUR 1      // semaphore pour garantir l'acces d'un redacteur
                       // sur le fichier d'invite en priorite sur un lecteur


int main ( int argc , char **argv ) {
 
   key_t cle;             // cle IPC d'identification des semaphores
   int semid;             // nom local de l'ensemble des semaphores
   int shmid;


   // cree le fichier si  il n existe pas deja
   mknod("/tmp/invite",S_IFREG|0666,0);

   // ou
   // system("touch /tmp/invite");
	
  // creation d'une cle IPC en fonction du nom du fichier d'invite

   
  if ((cle=ftok("/tmp/invite",'0')) == -1 ) {
    fprintf(stderr,"Probleme sur ftoks\n");
    exit(1);
  }
  
  
  printf("Initialisation  des semaphores \n");
  // demande un ensemble de semaphores (LECTEUR, MUTEX et PROTECT)
  if ((semid=semget(cle,2,IPC_CREAT|0777))==-1) {
    perror("semget");
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  }

 // demande shmget 
 printf("Creation du segment partagé \n");
   if ((shmid=shmget(cle,1024,IPC_CREAT|0777))==-1) {
     perror("shmget");
	fprintf(stderr,"Probleme sur shmget\n");
	exit(2);

   }
   {
     ushort init_sem [2]={1,1};
     // initialise l'enseble
     if (semctl(semid,2,SETALL,init_sem)==-1) {
       fprintf(stderr,"Probleme sur semctl SETALL\n");
       exit(3);
     }
   }



   {
     int * val; // lien vers variable partagee
     // attache le segment avec la memoire du processus
     if ((val=(int*)shmat(shmid,NULL,0))==(int *)-1) {
       perror("shmat ");
       fprintf(stderr,"Probleme sur shmat\n");
       exit(3);
     }
     
      printf("Initialisation  du segment partagé \n");
     
     *val =0;

     shmdt(val);
   }

   printf("Init mot du jour\n");

  }
