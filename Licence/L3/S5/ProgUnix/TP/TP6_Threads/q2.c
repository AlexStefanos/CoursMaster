pthread_mutex_t mutex_access_tableau = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_etat_tableau = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t condition_etat_tableau;
int etat_tableau = VIDE;
int nb_caractere_tableau = 0;

void *ThreadEcrivain(void *thread_number) {
  long t_number;
  int i, r, mon_etat_tableau = VIDE;
  char my_char = 'A';
  char my_tab[MAX_THREADS];

  t_number = (long)thread_number;
  my_char = my_char + t_number - 1;
  for(i = 0; i < t_number; i++) {
    my_tab[i] = '\t';
  }
  my_tab[i] = '\0';
  for EVER {
    for(i = 0; nb = 0; i < MAX_CAR; i++) {
      r = pthread_mutex_lock(&mutex_access_tableau);
      if(r != 0) {
        perror("ERREUR pthread_mutex_lock()");
        exit(EXIT_FAILURE);
      }
      if(nb_caractere_tableau < MAX_CAR) {
        if(c_tab[i] == ' ') {
          c_tab[i] = my_char;
          nb_caractere_tableau++;
        }
      }
      else{
        mon_etat_tableau = PLEIN;
      }
      r = pthread_mutex_unlock(&mutex_access_tableau);
      if(r != 0) {
        perror("ERREUR pthread_mutex_unlock()");
        exit(EXIT_FAILURE);
      }
    }
    if(mon_etat_tableau == PLEIN) {
      if(VERBOSE) {
        printf("%sE: %c - VU TABLEAU PLEIN \n", my_tab, my_char);
      }
      if(r != 0) {
        perror("ERREUR pthread_mutex_lock()");
        exit(EXIT_FAILURE);
      }
      if(etat_tableau == VIDE) {
        if(VERBOSE) {
          printf("%sE: %c - TABLEAU VIDE -> PLEIN\n", my_tab, my_char);
          etat_tableau = PLEIN;
          r = pthread_cond_signal(&condition_etat_tableau);
          if(r != 0) {
            perror("ERREUR pthread_cond_signal()");
            exit(EXIT_FAILURE);
          }
          if(VERBOSE) {
            printf("%sE: %c - ENVOI SIGNAL\n", my_tab, my_char);
          }
        }
        while(etat_tableau == PLEIN) {
          if(VERBOSE) {
            printf("%sE : %c - ATTEND\n", my_tab, my_char);
          }
          r = pthread_cond_wait(&condition_etat_tableau, &mutex_etat_tableau);
          if(r != 0) {
            perror("ERREUR pthread_cond_wait()");
            exit(EXIT_FAILURE);
          }
          if(VERBOSE) {
            printf("%sE: %c - RECU SIGNAL\n", my_tab, my_char);
          }
        }
        r = pthread_mutex_unlock(&mutex_etat_tableau);
        if(r != 0) {
          perror("ERREUR pthread_mutex_unlock()");
          exit(EXIT_FAILURE);
        }
      }
    }
  }

  void *ThreadLecteur(void *thread_iter) {
    struct thread_param *mes_param;
    long nb_iter;
    int i,r, tab, the_char;

    nb_iter = (long)thread_iter;
    for(i = 1; i <= nb_iter; i++) {
      r = pthread_mutex_lock(&mutex_etat_tableau);
      if(r != 0) {
        perror("ERREUR pthread_mutex_lock()");
        exit(EXIT_FAILURE);
      }
      while(etat_tableau == VIDE) {

      }
    }
  }
}
