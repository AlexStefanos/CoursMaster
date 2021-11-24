#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv) {
  if(argc < 2) {
    fprintf(stderr, "Usage : %s chemin\n", argv[0]);
    exit(EXIT_FAILURE);
  }
  execl(argv[1], argv[1], NULL);
  perror(argv[1]);
  exit(EXIT_FAILURE);
}
