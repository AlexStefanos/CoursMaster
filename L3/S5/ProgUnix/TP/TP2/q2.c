#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>

int main(int argc, char **argv) {
    char cmd[250], *arg[40], *bg, *s;
    int i, pid, codeRetour;
    for(;;) {
        printf("shell> ");
        fgets(cmd, 250, stdin);
        cmd[strlen(cmd) - 1] = '\0';
        if(bg = strrchr(cmd, '&'))
            *bg = '\0';
    
        for(i = 0; s = strtok(cmd, " "), s != NULL, s = strtok(NULL, " "); i++) {
            arg[i] = (char *)malloc(strlen(s) + 1);
            strcpy(arg[i], s);
        }
        arg[i] = NULL;
        if(i > 0) {
            pid = fork();
            if(pid < 0) {}
            else if(pid == 0) {
                printf("\n");
                execvp(arg[0], arg);
                /*Si on est là c'est qu'il y a un problème, 
                affichage d'un message d'erreur*/
                perror("execvp");
            }
            else {
                if(bg != NULL) {
                    //Commande exécutée en arrière-plan : inutile d'attendre
                }
                else
                    pid = wait(&codeRetour);
            }
        }
    }
}
