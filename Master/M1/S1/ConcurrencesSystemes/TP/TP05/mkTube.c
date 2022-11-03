#include<stdio.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<stdlib.h>

int main(int argc ,char **argv) {
  mode_t mode=S_IRUSR|S_IWUSR|S_IRGRP;

  if(mkfifo("tube",mode) == -1) {
    perror("mkfifo");
    exit(1);
  }
  else
    printf("Création du tube nommé :  \"tube\"\n");   
}
