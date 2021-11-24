#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int p[2], p1, lu, ecrit;
    char buff[25];

    if(argc < 2) {
        fprintf(stderr, "Usage : %s \n", argv[1]);
        exit(EXIT_FAILURE);
    }
    pipe(p);
    p1 = fork();
    if(p1 == 0) {
        close p[1];
        lu = read(p[0]);
        if(lu < 0) {
            perror("read");
            exit(EXIT_FAILURE);
        }
        buff[lu] = '\0':;
        printf("Fils : %s\n", buff);
        close(p[0]);
        exit(EXIT_SUCCESS);
    }
    close(p[0]);
    ecrit = write(p[1], argv[1], strlen(argv[1]));
    if(ecrit < 0) {
        perror("write");
        exit(EXIT_FAILURE);
    }
    close(p[1]);
    exit(EXIT_SUCCESS);
}