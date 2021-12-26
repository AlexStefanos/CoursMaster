#include <stdio.h>
#include <unistd.h>
#define DELAI 5

int main(int argc, char **argv) {
    int valeur;
    alarm(DELAI);
    printf("Vous avez %d s pour saisir une valeur : ", DELAI);
    scanf("%d", &valeur);
    alarm(0);
    printf("Vous avez saisi : %d\n", valeur);
    return(0);
}
