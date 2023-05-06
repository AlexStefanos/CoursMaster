#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <signal.h>

#define MUTEX 0

int semid, shmid, nb = 0, fin = 0;
int* val;  // tableau des valeurs de rouleaux
static void stop (int sig) {
  fin = 1;
}

void rouleau(int roul) {
  struct sembuf op; // operation sur un semaphore
  //prépare un ensemble de signaux 	  
  sigset_t setUSR1;
  sigemptyset(&setUSR1);
  sigaddset(&setUSR1,SIGUSR1);
  while(!fin) {
    // diffère les USR1
    sigprocmask (SIG_BLOCK, &setUSR1,(sigset_t *) NULL);
    // modifie le rouleau
    /*P(&mutex); demande l'acces la sortie standard */
    op.sem_num = MUTEX;
    op.sem_op = -1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
    // modifie la valeur du rouleau
    val[roul] = (val[roul]+1) %10;
    // affiche tous les rouleaux
    for(int i = 0; i < nb; i++) {
      printf("%3d", val[i]);
      printf("\r");
      fflush(stdout);
    }
    op.sem_num = MUTEX;
    op.sem_op = 1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
    // autorise  les USR1
    sigprocmask (SIG_UNBLOCK, &setUSR1,(sigset_t *) NULL);
    usleep(((random() % 100) + 100) * 1000);
    }
  exit(0);
}

int main(int argc, char **argv) {
  int pid[512]; // numero des fils
  // CREE ET INITALISE MUTEX  
  if(argc == 1) {
    fprintf(stderr,"usage : %s  nbRlouleaux \n",argv[0]);
    exit(0);
  }
  // mise en place du gestionnaire de signaux sur USR1
  // père et fils
  nb = atoi(argv[1]);  
  {
    struct sigaction actions;
    
    sigemptyset(&actions.sa_mask);
    actions.sa_flags = 0;
    actions.sa_handler = stop; // fonction a lancer 
    sigaction(SIGUSR1, &actions, NULL);
  }
  // demande un ensemble de semaphore (ici un seul mutex)
  if((semid=semget(IPC_PRIVATE, 1, IPC_CREAT|0666)) == -1) {
    fprintf(stderr,"Probleme semget\n");
    exit(2);
  }  
  // initialise l'ensemble
  {   
    ushort init_sem[1]={1}; //structure par initialise le semaphore mutex
    if (semctl(semid, 1, SETALL, init_sem) == -1) { 
      fprintf(stderr,"Probleme SETALL\n");
      exit(3);
    }
  }
  // demande shmget 
  if((shmid = shmget(IPC_PRIVATE, sizeof(int)*nb, IPC_CREAT|0777)) == -1) {
    fprintf(stderr,"Probleme sur shmget\n");
    exit(2);
  }
  {
    // attache le segment avec la memoire du processus
    if ((val=(int *)shmat(shmid, NULL, 0)) == (int*)-1) {
      fprintf(stderr,"Probleme sur shmat\n");
      exit(3);
    } 
    int init;
    
    init= time(NULL);
    srandom(init);
    // initilialise les rouleaux avec des chiffres aleatoires
    for (int i = 0; i < nb; i++) 
      val[i]=random()%10;
  }  
  {
    //nb = atoi(argv[1]);  
    for (int i = 0 ;i < nb; i++) { // boucle sur les argument
        if((pid[i] = fork()) == 0) { // creation d'un processus fils
          // processus fils
          rouleau(i);
          exit(0);  
        }
    }
  /* 
  // arret d'un rouleau  puis toutes les secondes
    getchar();
    kill(pid[0],SIGUSR1);
    for (i=1 ;i < nb; i++){
      sleep(1);
      kill(pid[i],SIGUSR1);
      
    }
  */
  // arret  de tous les rouleaux
    for (int i = 0 ;i < nb; i++) {
      getchar();
      kill(pid[i],SIGUSR1);
    }
    // attend la terminaison des fils
    for (int i = 0 ;i < nb; i++) {
      wait(NULL);
    }  
    printf("\n\n Resultat :  ");
    for(int i = 0; i < nb; i++) {
      printf("%3d", val[i]);
      printf("\n");
    }
    /* liberation du semaphore */
    semctl(semid,0,IPC_RMID,0);
    /* liberation du segment partage */
    shmctl(shmid,IPC_RMID,0);
  }
  return(0);
}
