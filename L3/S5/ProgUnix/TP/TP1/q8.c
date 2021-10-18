#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv) {
  pid_t mon_groupe, mon_leader;

  mon_leader = getpgrp();
  if(mon_leader == -1) {
    perror("Pb times");
    exit(EXIT_FAILURE);
  }
  printf("Mon PID est : %d\n", getpid());
  printf("Le processus leader de mon groupe est : %d\n", mon_leader);
}
