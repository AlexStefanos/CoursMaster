#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define MAX 512
#define FIFO_FILE "tubeCommun"

char tube[MAX];
char date[MAX];

void getDate(char *date) {
    int f2;

    if((f2 = open(tube, O_RDONLY)) < 0) {
        printf("Erreur ouverture tube");
        exit(2);
    }
    read(f2, date, MAX);
    close(f2);
}

int main(int argc, char **argv) {
    mode_t mode = S_IRUSR|S_IWUSR|S_IRGRP;
    char buff[MAX];
    int pid, f1;

    pid = getpid();
    sprintf(tube, "/tmp/tube_%d", pid);
    umask(0);
    mkfifo(tube, mode);
    if((f1 = open(FIFO_FILE, O_WRONLY|O_TRUNC)) < 0) {
        printf("Erreur ouverture tube serveur commun\n");
        exit(1);
    }
    write(f1, &pid, sizeof(int));
    close(f1);
    getDate(date);
    printf("%s\n", date);
    remove(tube);
}