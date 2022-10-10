#include<unistd.h>
#include<fcntl.h>
#include<stdio.h>
#include <sys/types.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <stdlib.h>

#define MUTEX 0
#define NBLECTEUR 1

int main ( int argc , char **argv ) {  
  key_t cle;
  int semid;

  if((cle=ftok("/tmp/invite",'0')) == -1 ) {
    fprintf(stderr,"Probleme sur ftoks\n");
    exit(1);
  }
  if((semid=semget(cle,2,0))==-1) {
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  }
  {
    struct sembuf op;
    op.sem_num=MUTEX;op.sem_op=-1;op.sem_flg=0;
    semop(semid,&op,1);
    printf("Saisie du texte : ctrl D\n"); 
    system("cat > /tmp/invite");
    op.sem_num=MUTEX;op.sem_op=1;op.sem_flg=0;
    semop(semid,&op,1);
  }
  printf("Fin Redacteur\n");  
} 

