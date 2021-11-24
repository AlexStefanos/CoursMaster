#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int main(int argc, char **argv, char **arge) {
    int pid;

    if (argc != 3) {
        fprintf(stderr, "Usage %s commande\n", argv[0]);
        exit(1);
    }
    pid = fork();
    if (pid < 0) {
        perror("fork");
        exit(1);
    }
    else if(pid == 0) {
        printf("--------------------------------------------------Environnement du Fils--------------------------------------------------\n");
        for(;*arge != NULL; *arge++)
            printf("%s\n", *arge);
    }
    else {
        printf("--------------------------------------------------Environnement du Père--------------------------------------------------\n");
        for(;*arge != NULL; *arge++)
            printf("%s\n", *arge);
    }
}
