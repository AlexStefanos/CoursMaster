#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int pid,n = atoi(argv[1]);

    while(n > 0) {
        pid = fork();
        if(pid < 0) {
            perror("fork");
            exit(n);
        }
        else if (pid == 0) {
            printf("Voici le fils n°%d de valeur : %d\n", n, getpid());
            exit(n);
        }
        else {
            exit(n);
        }
        n--;
    }
}
