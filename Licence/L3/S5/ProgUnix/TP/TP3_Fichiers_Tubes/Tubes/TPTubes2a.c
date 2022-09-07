#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char **argv) {
    int h1;

    if(argc != 3) {
        fprintf(stderr, "Usage : %s\n");
        exit(EXIT_FAILURE);
    }
    h1 = open(argv[1], O_WRONLY);
    printf("%d : J'écris \"%s\" dans le tube\n", getpid(), argv[2]);
    write(h1, argv[2], strlen(argv[2]));
    close(h1);
    exit(EXIT_FAILURE);
}