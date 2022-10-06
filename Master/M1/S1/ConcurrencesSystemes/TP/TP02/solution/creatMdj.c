// creatMdj.c
//


#include<unistd.h> // appel systeme fork
#include<fcntl.h>  // appel system unix ES
#include<stdio.h> // librairie standard C
#include <sys/types.h>
#include <sys/sem.h> // semaphore IPC
#include <sys/ipc.h> // services IPC
#include <stdlib.h>





#define MUTEX 0        // semaphore pour le redacteur
#define NBLECTEUR 1      // semaphore pour garantir l'acces d'un redacteur
                       // sur le fichier d'invite en priorite sur un lecteur



int main ( int argc , char **argv ) {
  

  key_t cle;             // cle IPC d'identification des semaphores
  int semid;             // nom local de l'ensemble des semaphores

  // creation d'une cle IPC en fonction du nom du fichier d'invite
  if ((cle=ftok("/tmp/invite",'0')) == -1 ) {
    fprintf(stderr,"Probleme sur ftoks\n");
    exit(1);
  }
  
  // demande un ensemble de semaphores (LECTEUR, MUTEX et PROTECT)
  if ((semid=semget(cle,2,0))==-1) {
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  }


   {
  
     struct sembuf op ;  // operation sur un semaphore


     // on verifie l'exclusion mutuelle
     /*P(MUTEX) */
     op.sem_num=MUTEX;op.sem_op=-1;op.sem_flg=0;
     semop(semid,&op,1);

     printf("Saisie du texte : ctrl D\n");
     

     system("cat > /tmp/invite");
      

     
     /*V(MUTEX) */
     op.sem_num=MUTEX;op.sem_op=1;op.sem_flg=0;
     semop(semid,&op,1);
   }

   printf("Fin Redacteur\n");
     
} 

