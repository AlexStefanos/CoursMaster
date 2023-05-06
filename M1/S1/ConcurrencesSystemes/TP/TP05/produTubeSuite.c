#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int main(int argc ,char **argv) {
  int t;
  char c;
  int nb;

  printf("open(\"tube\",O_WRONLY) \n");
  if((t = open("tube",O_WRONLY))< 0) {
     fprintf(stderr,"Probleme d'ouverture du tube en ecriture\n");
     exit(1);
  }
  printf("Apres open(\"tube\",O_WRONLY) \n");
  printf("Lecture sur l'entrée standard \n");
  c='.';
  nb=0;
  while(1) {
    nb++;
    if(nb %1024 == 0)
      printf("%d %x envoyés \n",nb,nb);
    write(t,&c,1);
  }
  printf("close tube \n");
  close(t);
}