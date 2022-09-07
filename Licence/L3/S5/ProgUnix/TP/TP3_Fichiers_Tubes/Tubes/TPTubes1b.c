#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int t1[2], t2[2], p1, lu, ecrit;
    char buff[25];

    if(argc != 2) {
        fprintf(stderr, "Usage : %s\n");
        exit(EXIT_FAILURE);
    }
    pipe(t1);
    pipe(t2);
    p1 = fork();
    if(p1 == 0) {
        close(t1[0]);
        close(t2[0]);

        lu = read(t1[0], buff, 25);
        if(lu < 0) {
            perror("read");
            exit(EXIT_FAILURE);
        }
        buff[lu] = '\0';
        printf("Fils, je reçois : %s\n");
        ecrit = write(t2[1], argv[1], strlen(argv[1]));
        if(ecrit < 0) {
            perror("write");
            exit(EXIT_FAILURE);
        }
        close(t1[0]);
        exit(EXIT_SUCCESS);
    }
    close(t1[0]);
    close(t2[0]);
    ecrit = write(t1[1], argv[1], strlen(argv[1]));
    if(ecrit < 0) {
        perror("write");
        exit(EXIT_FAILURE);
    }
    lu = read(t2[0], buff, MAX);
    if(lu < 0) {
        perror("read");
        exit(EXIT_FAILURE);
    }
    buff[lu] = '\0';
    printf("Père, je reçois : %s\n");
    close(t1[0]);
    close(t2[0]);
}