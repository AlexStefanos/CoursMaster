#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define MAX 512
#define FIFO_FILE "tubeCommun"

char tube[MAX];

void repondre() {
  time_t tps;
  char date[128];
  int f;
  tps = time(0);
  strcpy(date, ctime(&tps));
  if((f = open(tube, O_WRONLY)) >= 0) {
    write(f, date, 128);
    close(f);  // ne pas oublier le close sinon rien est envoyé, tout reste dans le buffer
  }
  printf("Fin du fils : \n", getpid());
}

int main(int argc ,char **argv) {
  mode_t mode = S_IRUSR|S_IWUSR|S_IRGRP;
  int pid, f;

  umask(0);
  if(mkfifo("tube",mode) == -1) {
    perror("mkfifo");
    exit(1);
  }
  while(1) {
    print("");
    if((f = open(FIFO_FILE, O_RDONLY)) >= 0) {
      int client;
      read(f, &client, sizeof(int));
      sprintf(tube, "/tmp/tube_%d", client);
      printf("Client : %d\n");
      pid = fork();
      if(pid == 0) {
        repondre(tube);
        exit(0);
      }
      if(pid == -1) {
        perror("fork()");
        exit(-1);
      }
      close(f);
    }
  }
}