// lireMdj.c
//


#include<unistd.h> // appel systeme fork
#include<fcntl.h>  // appel system unix ES
#include<stdio.h> // librairie standard C
#include <sys/types.h>
#include <sys/sem.h> // semaphore IPC
#include <sys/ipc.h> // services IPC
#include <sys/shm.h> // semaphore IPC
#include <stdlib.h>





#define MUTEX 0        // semaphore pour le redacteur
#define NBLECTEUR 1      // semaphore pour garantir l'acces d'un redacteur
                       // sur le fichier d'invite en priorite sur un lecteur

int * nbLecteur; 

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
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  }
   // demande shmget 
   if ((shmid=shmget(cle,1024,0))==-1) {
     perror("shmget");
	fprintf(stderr,"Probleme sur shmget\n");
	exit(2);

   }

     
     // attache le segment avec la memoire du processus
     if ((nbLecteur=(int*)shmat(shmid,NULL,0))==(int *)-1) {
       perror("shmat ");
       fprintf(stderr,"Probleme sur shmat\n");
       exit(3);
     }
     
  
     struct sembuf op ;  // operation sur un semaphore


     // on verifie l'exclusion mutuelle
     /*P(NBLECTEUR) */

     op.sem_num=NBLECTEUR;op.sem_op=-1;op.sem_flg=0;
     semop(semid,&op,1);

     (*nbLecteur)++;

     if ( (*nbLecteur) == 1) { // nouveau premier lecteur

       // on verifie l'exclusion mutuelle
       /*P(MUTEX) */
       
       op.sem_num=MUTEX;op.sem_op=-1;op.sem_flg=0;
       semop(semid,&op,1);
     }


     // on verifie l'exclusion mutuelle
     /*V(NBLECTEUR) */

     op.sem_num=NBLECTEUR;op.sem_op=1;op.sem_flg=0;
     semop(semid,&op,1);



     printf("Lecteur du texte \n");
     
     
     {
	 FILE *fic;
	if ((fic=fopen("/tmp/invite","r"))!=NULL){
		char str[4096];
		while  ( (fgets(str,4096,fic) != (char*) NULL)){
			printf("%s",str);
			sleep(5);
		}
		fclose(fic);   
	}
	  
     }
     
     /*
     system("cat  /tmp/invite");
      
     printf("attente 30 secondes \n");
     sleep(30);
*/

      // on verifie l'exclusion mutuelle
     /*P(NBLECTEUR) */

     op.sem_num=NBLECTEUR;op.sem_op=-1;op.sem_flg=0;
     semop(semid,&op,1);

     (*nbLecteur)--;
     
     if ((*nbLecteur)==0) {// dernier lecteur
       
       /*V(MUTEX) */
       op.sem_num=MUTEX;op.sem_op=1;op.sem_flg=0;
       semop(semid,&op,1);
       
     }
     

     shmdt(nbLecteur);
     // on verifie l'exclusion mutuelle
     /*V(NBLECTEUR) */
     
     op.sem_num=NBLECTEUR;op.sem_op=1;op.sem_flg=0;
     semop(semid,&op,1);
     
     
     printf("Fin Lecteur\n");
       
} 

