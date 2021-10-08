#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>

int main(int argc, char **argv, char **arge) {
    printf("--------------------------------------------------Avant exec--------------------------------------------------\n");
    printf("Mon pid:\t\t%d\n", getpid());
    printf("Mon UID réel :\t\t%d\n", getuid());
    printf("Mon UID effectif :\t\t%d\n", geteuid());
    printf("Mon GID réel :\t\t%d\n", getgid());
    printf("Mon GID effectif :\t\t%d\n", getegid());
    mode = umask(mode);
    printf("Mon masque(octal) :\t%o\n", mode);
    mode = umask(mode);
    d = getcwd(dir, 200);
    if(d != NULL)
        printf("Mon répertoire courant :\t%s\n", dir);
    else
        perror(dir);
    printf("Ouverture d'un fichier avec le flag FD_CL0EXEC\n");
    fd = open("Ouverture d'un fichier sans le flag FD_CLO0EXEC\n")
    if (fd < 0)
      perror("Open");
    execl("./" "./", NULL);
    perror("...");
}
