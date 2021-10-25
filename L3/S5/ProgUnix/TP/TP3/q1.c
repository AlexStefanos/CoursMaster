#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>

int main(int argc, char **argv) {
    int fd, fc;

    if (argc != 2) {
        fprintf(stderr, "Usage : %s fichier\n", argv[0]);
        exit(1);
    }
/* O_RDWR ouvir pour lire et écrire (= ReaDingWRiting)
 * O_CREAT création du fichier s'il n'existe pas
 * O_CREAT|O_EXCL création du fichier s'il n'existe pas mais erreur s'il existe déjà*/
    fd = open(argv[1], O_RDWR|O_CREAT|O_EXCL, 0666);

    if (fd == -1) {
        /* b) Le code est EEXIST pathname existe déjà (cf. man 2 open)*/
        perror(argv[1]);
        exit(1);
    }
    printf("Fichier %s créé\n", argv[1]);
    return(0);
}
