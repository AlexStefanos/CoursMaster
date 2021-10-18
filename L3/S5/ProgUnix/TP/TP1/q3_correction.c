#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
  int x = 1, pid;
  pid = fork();
  if(pid < 0) {
    perror("pb fork");
    exit(1);
  }
  else if(pid == 0) {
    printf("\t\tFILS - Adresse de x : %p\n", &x);
    printf("\t\tFILS - Valeur de x après forl : %d\n", x);
    x++;
    printf("\t\tFILS - Valeur de x++ : %d\n", x);
    printf("\t\tFILS - Adresse de x : %p\n", &x);
  }
  else {
    printf("PERE - Adresse de x : %p\n", &x);
    printf("PERE - Valeur de x après fork : %d\n", x);
    x = x + 2;
    printf("PERE - Valeur de x = x + 2 : %d\n", x);
    printf("PERE - Adresse de x : %p\n", &x);
  }
}
