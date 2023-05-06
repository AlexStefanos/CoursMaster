#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

void calcul(int i, int argc, char **argv){
  if(i == argc-1)
    exit(atoi(argv[i]));
  else {
    int pid = fork();

    if(pid == 0)
      calcul(++i,argc,argv);
    else if(pid > 0) {
      int status;
      wait(&status);
      exit(atoi(argv[i])+WEXITSTATUS(status));
    }
    else {
      printf("fork error");
      exit(0);
    }
  }
}

int main(int argc, char **argv)
{
  int pid = fork();

  if(pid == 0)
    calcul(1,argc,argv);
  else if(pid > 0) {
    int status;
    wait(&status);
    printf("Somme : %d\n", WEXITSTATUS(status));
  }
  else{
    printf("Erreur Fork");
    exit(0);
  }
}