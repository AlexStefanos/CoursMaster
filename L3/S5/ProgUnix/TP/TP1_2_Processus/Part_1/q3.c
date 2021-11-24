#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>


int main (int argc, char **argv) {
	int pid, iter, i, x;

	if (argc != 2) {
		fprintf(stderr, "Usage : %s iter\n", argv[0]);
		exit(1);
	}

	iter = atoi(argv[1]);
	x = 100;
	pid = fork();
	if (pid < 0) {
		perror("fork");
		exit(1);
	}
	else if (pid == 0) {
		for(i = 0; i < iter; i++) {
			printf("\t\t\tFils, valeur de %d\n", x);
			printf("\t\t\tFils, valeur de %p\n", &x);
		}
	}
	else {
		for (i = 0; i < iter; i++) {
			printf("Père, valeur de %d\n", x);
			printf("Père, valeur de %p\n", &x);
			x--;
			printf("Père, valeur de %d\n", x);
			printf("Père, valeur de %p\n", &x);
		}
	}
}
