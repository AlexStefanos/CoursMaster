int p[2], n, ecrit;

void killMe() {
  printf("Le tue est plein - %d ! Je suis bloqué\n", ecrit);
  exit(EXIT_SUCCESS);
}

int main(int argc, char **argv) {
  if(pipe(p) != -1) {
    signal(SIGALRM, killMe);
    ecrit = 0;
    for(;;) {
      alarm(1);
      n = write(p[1], "xy", 1);
      ecrit += n;
    }
  }
}
/*Problématiques : L'exercice repose sur le fait de ne pas pouvoir écrire dans un tube plein. L'action est bloquante. Comment contourner cette
difficulté ?*/
