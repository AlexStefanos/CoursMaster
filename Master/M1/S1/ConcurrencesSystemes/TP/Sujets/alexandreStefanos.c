#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <time.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <signal.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/ipc.h>

#define MUTEX 0
#define MAXLINE 128
#define PATHMAX 4096

void scanDir(char *dir) {
  DIR *nomDir;
  struct dirent *fichier ;
  if(nomDir = opendir(dir)){
    fichier = readdir(nomDir) ;
    while(fichier != NULL) {
      char fic[PATHMAX];
      struct stat etat ;
      sprintf(fic,"%s/%s",dir,fichier->d_name);
      printf("%s\n",fic);
      while(stat(fic, &etat) != -1) {
        while((etat.st_mode & S_IFMT) == S_IFDIR) {
            strcmp(fichier->d_name, ".");
            strcmp(fichier->d_name, "..");
        }
      }
      fichier = readdir(nomDir);
    }
    closedir(nomDir);
  }
}

key_t cle;
int shmid, semid;
struct sembuf op;
struct buffer {
    int nb;
    char values[MAXLINE][PATHMAX];
};
struct buffer *buf;

int main(int argc, char **argv) {
    char *motif, *repertoireDeDepart;
    int pid;
    
    if (argc < 4) {
        printf("usage : findS motif repertoireDeDepart\n");
        exit(-1);
    }
    if((cle = ftok(argv[0],'0')) == -1 ) {
        fprintf(stderr, "Probleme ftoks\n");
        exit(1);
    }
    if((semid = semget(cle, 1, IPC_CREAT|0666)) == -1) {
        fprintf(stderr, "Probleme semget\n");
        exit(2);
    }
    if(semctl(semid, 3, SETALL, *buf) == -1) {
        fprintf(stderr, "Probleme semctl\n");
        exit(3);
    }
    if((shmid = shmget(cle, sizeof(struct buffer), IPC_CREAT|0666) == -1)) {
        fprintf(stderr, "Probleme shmget\n");
        exit(4);
    }
    if((buf = shmat(shmid, NULL, 0)) == (struct buffer *) -1) {
        fprintf(stderr, "Probleme shmat\n");
        exit(5);
    }
    motif = argv[2];
    repertoireDeDepart = argv[3];
    buf->nb = 0;
    strcpy(buf->values[0], repertoireDeDepart);
    buf->nb++;
    for (int i = 0; i < 4; i++) {
        pid = fork();
        if(pid == 0) {
            char fic[PATHMAX];
            while(1) {
                DIR *dir;
                struct dirent *fichier;
                op.sem_num = MUTEX;
                op.sem_op = -1;
                op.sem_flg = 0;
                semop(semid, &op, 1);
                if(buf->nb == 0) {
                    op.sem_num = MUTEX;
                    op.sem_op = 1;
                    op.sem_flg = 0;
                    semop(semid, &op, 1);
                    exit(0);
                }
                scanDir(repertoireDeDepart);
                strcpy(fic, buf->values[buf->nb - 1]);
                buf->nb--;
                op.sem_num = MUTEX;
                op.sem_op = 1;
                op.sem_flg = 0;
                semop(semid, &op, 1);
                if((dir = opendir(fic)) == NULL) {
                    fprintf(stderr, "Probleme opendir\n");
                    exit(6);
                }
                while((fichier = readdir(dir)) != NULL) {
                    if(strcmp(fichier->d_name, motif) == 0) {
                        op.sem_num = MUTEX;
                        op.sem_op = -1;
                        op.sem_flg = 0;
                        semop(semid, &op, 1);
                        printf("%s/%s \n", fic, fichier->d_name);
                        op.sem_num = MUTEX;
                        op.sem_op = 1;
                        op.sem_flg = 0;
                        semop(semid, &op, 1);
                    }
                    if(fichier->d_type == DT_DIR && strcmp(fichier->d_name, ".") != 0 && strcmp(fichier->d_name, "..") != 0) {
                        op.sem_num = MUTEX;
                        op.sem_op = -1;
                        op.sem_flg = 0;
                        semop(semid, &op, 1);
                        strcpy(buf->values[buf->nb], fic);
                        strcat(buf->values[buf->nb], "/");
                        strcat(buf->values[buf->nb], fichier->d_name);
                        buf->nb++;
                        op.sem_num = MUTEX;
                        op.sem_op = 1;
                        op.sem_flg = 0;
                        semop(semid, &op, 1);
                    }
                }
                closedir(dir);
            }
        }
        else {
        }
    }
    for (int i = 0; i < 4; i++)
        wait(NULL);
    return(0);
}