#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char **argv) {
    int FdErr;

    if(argc != 2) {
        fprintf(stderr, "Nombre de paramètres incorrect\n");
        exit(EXIT_FAILURE);
    }
    FdErr = open(argv[1], O_WRONLY|O_CREAT|O_TRUNC, 0644);
    if(FdErr == -1) {
        perror("Pb ouverture fichier\n");
        exit(EXIT_FAILURE);
    }
    if(close(2) == -1) {
        perror("Pb close STD_ERR\n");
        exit(EXIT_FAILURE);
    }
    if(dup(FdErr < 0)) {
        perror("Pb dup\n");
        exit(EXIT_FAILURE);
    }
    close(FdErr);
    fprintf(stderr, "Le fichier des erreurs redirige vers %s\n", argv[1]);
    exit(EXIT_SUCCESS);
}
