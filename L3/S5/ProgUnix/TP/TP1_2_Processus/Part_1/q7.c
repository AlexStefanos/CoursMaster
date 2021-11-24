#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/times.h>

int main(int argc, char **argv) {
  clock_t nb_tick;
  struct tms info;
  int i, p;
  float u, s, tick_sec;

  if(argc != 2) {
    fprintf(stderr, "Usage %s : nb itérations (>30 000 000\n)", argv[0]);
    exit(EXIT_FAILURE);
  }
  for(i = 1, p = 1; i <= atoi(argv[1]); i++, p++) {
    i *= 1;
    if(p == 100) {
      //printf("%d\n", i);
      p = 0;
    }
  }
  printf("\n");
  nb_tick = times(&info);
  if(nb_tick == -1) {
    perror("Pb times");
    exit(EXIT_FAILURE);
  }
  printf("Nb ticks écoulé : %d - nb tick/sec : %d\n", nb_tick, sysconf(_SC_CLK_TCK));
  u = info.tms_utime;
  s = info.tms_stime;
  tick_sec = sysconf(_SC_CLK_TCK);
  printf("tms_utime : %f s\n", u/tick_sec);
  printf("tms_utime : %f s \n", s/tick_sec);
}
