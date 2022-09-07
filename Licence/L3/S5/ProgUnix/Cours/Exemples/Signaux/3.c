#include <stdio.h>
#include <signal.h>
#include <string.h>
#include <unistd.h>

unsigned int s;
void traiter_signal(int sig) {
    printf("Reception de : %s\n", strsignal(sig));
}

int main(int argc, char **argv) {
    signal(SIGUSR1, traiter_signal);
    signal(SIGUSR2, traiter_signal);
    s = sleep(50);
    printf("Reveil %d secondes avant la fin de ma sieste\n", s);
}
