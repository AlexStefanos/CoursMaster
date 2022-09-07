#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv) {
  pid_t mon_groupe, mon_leader, pid;

  pid = fork();
  if(pid < 0) {
    perror("Erreur du fork");
    exit(EXIT_FAILURE);
  }
  else if(pid == 0) {
    mon_leader = getpgrp();
    if(mon_leader == -1) {
      perror("Erreur getpgrp fils");
      exit(EXIT_FAILURE);
    }
    printf("FILS, mon PID est %d\n", getpid());
    printf("FILS, le processus leader de mon groupe est : %d\n\n", mon_leader);
  }
  else {
    mon_groupe = getpgrp();
    if(mon_groupe == -1) {
      perror("Erreur getpgrp père");
      exit(2);
    }
    printf("PERE, mon pid est : %d\n", getpid());
    printf("PERE, le processus leader de mon groupe est : %d\n", mon_groupe);
  }
}
