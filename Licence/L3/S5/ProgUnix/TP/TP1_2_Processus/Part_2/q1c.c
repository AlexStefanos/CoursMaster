#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv, char **arge) {
  char **arge_sauv;

  arge_sauv = arge;
  printf("--- Affichage de l'environnement avant l'execle ---\n");
  for(;*arge != NULL; *arge++)
    printf("%s\n", *arge);
  printf("--- Affichage de l'environnement après l'execle ---\n");
  execle("./affich_env", "affich_env", NULL, arge_sauv);
  perror("affich_env");
  exit(EXIT_FAILURE);
}
