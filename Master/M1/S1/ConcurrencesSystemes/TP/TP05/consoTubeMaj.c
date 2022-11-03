#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int main ( int argc , char **argv ) {
  int t;
  char c;
  int nb;
 
  printf("open(\"tubeMaj\",O_RDONLY) \n");
  if ((t=open("tubeMaj",O_RDONLY))< 0) {
     fprintf(stderr,"Probleme d'ouverture du tube en lecture\n");
     exit(1);
  }    
  printf("après open(\"tubeMaj\",O_RDONLY) \n");
  printf("getchar pour le test\n");
  getchar();
  printf("Lecture sur le tubeMaj \n");
  while((nb=read(t,&c,1)) >0) {
    write(1,(&c),1);
    //faire une fonction upperCase
    write(1,(&c),1);
  }
  printf("Echec du read sur la lecture du tubeMaj\n");
  close(t);
  
}
