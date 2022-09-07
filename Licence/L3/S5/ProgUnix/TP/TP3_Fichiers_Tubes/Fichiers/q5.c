#include <stdio.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int fd, mode, ret_fcntl;
    char mode_voulu[10], mode_existant[10];

    if(argc != 5) {
        fprintf(stderr, "Usage : %s fichier");
        exit(EXIT_FAILURE);
    }

    fd = open(argv[1], O_RDWR);
    if(fd < 0) {
        perror(argv[1]);
        exit(EXIT_FAILURE);
    }

    switch(*argv[2]) {
        case 's' :
            mode = F_RDLCK;
            strcpy(mode_voulu, "partage");
            break;
        case 'x' :
            mode = F_WRLCK;
            strcpy(mode_voulu, "exclusif");
            break;
        default :
            fprintf(stderr, "%s mode invalide\n", argv[2]);
            exit(EXIT_FAILURE);
    }

    zone_lock.l_type = mode;
    zone_lock.l_whence = SEEK_SET;
    zone_lock.l_start = atoi(argv[3]);
    zone_lock.l_len = atoi(argv[4]);
    ret_fcntl = fcntl(fd, F_GETLK, &zone_lock);
    if(ret_fcntl < 0) {
        perror("Erreur lors du test du verrou");
        exit(EXIT_FAILURE);
    }
    if(zone_lock.l_type != F_UNLCK) {
        switch(zone_lock.l_type) {
            case F_RDLCK :
                strcpy(mode_existant, "Partage");
                break;
            case F_WRLCK :
                strcpy(mode_existant, "Exclusif");
                break;
        }
        printf("\n%d:\t Il existe déjà un verrou %s\n", getpid(), mode_existant);
        printf("\tPosé par %d sur l'intervalle [%d,%d[\n", zone_lock.l_pid, zone_lock.l_start, zone_lock.l_start+zone_lock.l_len);
    }
    zone_lock.l_type = mode;
    zone_lock.l_whence = SEEK_SET;
    zone_lock.l_start = atoi(argv[3]);
    zone_lock.l_len = atoi(argv[4]);
    for(ret_fcntl = fcntl(fd, F_SETLK, &zone_lock); ret_fcntl < 0; ret_fcntl = fcntl(fd, F_SETLK, &zone_lock)) {
        sleep(5);
        continue;
        perror("J'attends\n");
    }
    if(ret_fcntl < 0) {
        perror("Pose du verrou");
        exit(EXIT_FAILURE);
    }
    printf("\n%d:\tPose du verrou %s effectuée\n", getpid(), mode_voulu);
    for(;;)
        sleep(10);
    return(EXIT_SUCCESS);
}
