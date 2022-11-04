#include<stdio.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<stdlib.h>

int main(int argc ,char **argv) {
    mode_t mode = S_IRUSR|S_IWUSR|S_IRGRP;
    int pid;

  if(mkfifo("tube",mode) == -1) {
    perror("mkfifo");
    exit(1);
  }
  else {
    printf("Création du tube nommé :  \"tube\"\n");
    //read
    pid = fork();
    if(pid == 0) {
        /*open(ct0)
        write(h1)
        close(ct0)*/
    }
    else if(pid > 0) {

    }
    else {

    }
    /* serv : read(4) -> pid -So1> open() ct0 write(h1) close
    conso : cp1 pid so1     cp2 pid so2
    tube conso -> serv
    "tube prive s01" <- ct0
    */
  }
}