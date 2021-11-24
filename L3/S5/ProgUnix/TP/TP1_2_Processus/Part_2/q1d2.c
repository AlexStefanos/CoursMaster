#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/stat.h>

int main(int argc, char **argv) {
    char dir[200];
    char *d;
    int r;
    mode_t mode;
    
    printf("\n------------------------------ Après exec------------------------------");
    printf("Mon pid : \t\t%d\n", getpid());
    printf("Le pid de mon père : \t%d\n", getppid());
    printf("Mon UID réel : \t\t%d\n", getuid());
    printf("Mon UID effectif : \t\t%d\n", geteuid());
    printf("Mon GID réel :\t\t%d\n", getgid());
    printf("Mon GID effectif :\t%d\n", getegid());
    printf("Mon masque(octal) :\t%o\n)", umask(mode));
    d = getcwd(dir, 200);
    if(d != NULL)
        printf("Mon répertoire courant :\t%s\n", dir);
    else
        perror(dir);
    exit(EXIT_SUCCESS);
}