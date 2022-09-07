#include <stdio.h>
#include <signal.h>
#include <unistd.h>

int main(int argc, char **argv) {
    printf("Avant le \"pause\" !\n");
    pause();
    printf("Après le \"pause\" !\n");
    return(0);
}
