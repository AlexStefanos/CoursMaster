#include <stdio.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    struct stat info;
    char *buffer;
    int nb_read, nb_write, fd_source, fd_destination, taille_buffer, total_copie, total_ES;
    if(argc != 4) {
        fprintf(stderr, "Usage : %s fichier_source fichier_destination taille_buffer\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    if(stat(argv[1], &info) != 0) {
        perror("Echec stat : ");
        exit(EXIT_FAILURE);
    }
    if(!S_ISREG(info.st_mode)) {
        fprintf(stderr, "Usage : %s - %s n'est pas un fichier régulier\n", argv[0], argv[1]);
        exit(EXIT_FAILURE);
    }

    fd_source = open(argv[1], O_RDONLY);
    if(fd_source == -1) {
        perror("Echec open fichier source : ");
        exit(EXIT_FAILURE);
    }

    fd_destination = open(argv[2], O_WRONLY|O_CREAT|O_EXCL, 0644);
    if(fd_destination == -1) {
        perror(argv[2]);
        exit(EXIT_FAILURE);
    }

    taille_buffer = atoi(argv[3]);
    buffer = malloc(sizeof(char) * taille_buffer);
    if(buffer == NULL) {
        perror("Malloc");
        exit(EXIT_FAILURE);
    }

    for(nb_read = read(fd_source, buffer, taille_buffer), total_copie = 0, total_ES = 0; (nb_read != 0)&&(nb_read != -1); nb_read = read(fd_source, buffer, taille_buffer)) {
        if(nb_read > 0)
            nb_write = write(fd_destination, buffer, nb_read);
        if(nb_write == -1) {
            perror(argv[2]);
            exit(EXIT_FAILURE);
        }
        total_copie = total_copie + nb_write;
        total_ES++;
    }
    if(nb_read == -1) {
        perror(argv[1]);
        exit(EXIT_FAILURE);
    }
    printf("Copié : \t\t%d octets\n", total_copie);
    printf("Buffer : \t\t%d octets\n", taille_buffer);
    printf("Nombre d'E/S : \t%d\n", total_ES);
    exit(EXIT_SUCCESS);
}
