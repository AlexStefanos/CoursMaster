#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char **argv) {
    int h1, nread;
    char msg[16];

    if(argc != 2) {
        fprintf(stderr, "Usage : %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }
    h1 = open(argv[1], O_RDONLY);
    nread = read(h1, &msg, 16);
    msg[nread] = '\0';
    printf("%d : Je lis \"%s\" depuis le tube\n", getpid(), msg);
    close(h1);
    exit(EXIT_SUCCESS);
}