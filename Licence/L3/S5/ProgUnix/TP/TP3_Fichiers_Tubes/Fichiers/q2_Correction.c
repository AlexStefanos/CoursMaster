#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/stat.h>

char *typeFichier(mode_t *st_mode) {
    char *ptr;

    if(S_ISREG(*st_mode))
        ptr = "regular";
    else if(S_ISDIR(*st_mode))
        ptr = "directory";
    else if(S_ISCHR(*st_mode))
        ptr = "character special";
    else if(S_ISBLK(*st_mode))
        ptr = "bloc special";
    else if(S_ISFIFO(*st_mode))
        ptr = "fifo";
    else if(S_ISLNK(*st_mode))
        ptr = "symbolic link";
    else if(S_ISSOCK(*st_mode))
        ptr = "socket";
    else
        ptr = "** unknown mode **";
    return(ptr);
}

int main(int argc, char **argv) {
    struct stat info;
    char *ptr;

    if(argc != 2) {
        fprintf(stderr, "Usage %s fichier\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    if(stat(argv[1], &info) != 0) {
        perror("Echec stat : ");
        exit(EXIT_FAILURE);
    }
    printf("-Type\t\t\t%s\n", typeFichier(&info.st_mode));
    printf("-Inode\t\t\t%ld\n",info.st_ino);
    printf("-Protection\t\t%lo (octal)\n",(unsigned long) info.st_mode);
    printf("-Lien(s)\t\t%ld\n",info.st_nlink);
    printf("-Uid\t\t\t%d\n",info.st_uid);
    printf("-Gid\t\t\t%d\n",info.st_gid);
    printf("-Taille\t\t%ld octets\n",info.st_size);
    printf("-Bloc E/S\t\t%ld octets\n",info.st_blksize);
    printf("-Bloc(s)\t\t%ld (X 512 octets)\n",info.st_blocks);
    printf("-Acces\t\t\t%s\n",ctime(&(info.st_atime)));
    printf("-Modification\t\t%s\n",ctime(&(info.st_mtime)));
    printf("-Etat\t\t\t%s\n",ctime(&(info.st_ctime)));
    exit(EXIT_FAILURE);
}
