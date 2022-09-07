 #include <stdio.h>
 #include <stdlib.h>
 #include <unistd.h>

 int main(int argc, char **argv, char **arge) {
   char *env[3];

   //Affichage de l'environnement avant l'execle
   printf("--- Affichage de l'environnement avant l'execle ---\n");
   for(; *arge != NULL; *arge++)
     printf("%s\n", *arge);
  //Nouvel environnement pour le programme qui va être changé
  env[0] = "VARENV1=BIDON1";
  env[1] = "VARENV2=BIDON2";
  env[2] = NULL;
  printf("--- Affichage de l'environnement après l'execle ---\n");
  execle("./affich_env", "affich_env", NULL, env);
  perror("affich_env");
  exit(EXIT_FAILURE);
 }
