#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#define MAX_THREADS 26
#define MAX_TAB 75

char caractere_tab[MAX_TAB];
int frequence_tab[MAX_THREADS + 1];

void *ThreadEcrivain(void *thread_number) {
  long t_number;
  int i;
  char my_char = 'A';

  t_number = (long)thread_number;
  my_char = my_char + t_number -1;
  for EVER {
    for(i = 0; i < MAX_TAB; i++) {
      caractere_tab[i] = my_char;
    }
  }
}

void *ThreadLecteur(void *thread_iter) {
  struc thread_param *mes_param;
  long nb_iter;
  int i, i_car, the_char;

  for(i = 0;i <= nb_iter; i++) {
    for(i_car = 0; i_car < MAX_TAB; i_car++) {
      if(caractere_tab[i_car] > 'A'-1) {
        frequence_tab[caractere_tab[i_car] -'A' + 1]++;
        printf("%c", caractere_tab[i_car]);
      }
    }
    printf("\n");
  }
  pthread_exit((void *)NULL);
}

int main(int argc, char **argv) {
  int r, nb_thread;
  long i, nb_iter, total = 0;
  pthread_t tid;

  if(argc != 3) {
    fprintf(stderr, "Utilisation : %s nb_thread nb_iter\n", argv[0]);
    exit(EXIT_FAILURE);
  }

  nb_thread = atoi(argv[1]);
  nb_iter = atoi(argv[2]);
  printf("Debut main\n");
  for(i = 1; i < MAX_THREADS+1; i++) {
    frequence_tab[i] = 0;
  }
  pthread_attr_init(&attr);
  pthread_attr_setdetachstate(&attr, PTREAD_CREATE_DETACHED);
  for(i = 1;i <= nb_thread; i++) {
    r = pthread_create(&tid, &attr, ThreadEcrivain, (void *) i);
    if(r != 0) {
      printf("ERREUR : pthread_create() a retourné %d\n", r);
      exit(-1);
    }
  }
  pthread_attr_init(&attr);
  pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);
  
  if(r != 0) {
    printf("Erreur : pthread_create() a retourné %d\n", r);
    exit(-1);
  }

  r = pthread_join(tid, NULL);
  if(r != 0) {
    printf("ERREUR : pthread_join() a retourné %d\n");
    exit(-1);
  }

  for(i = 1; i <= nb_thread; i++) {
    printf("%c: %d\n", 64+i, frequence_tab[i]);
    total+=frequence_tab[i];
  }
}
