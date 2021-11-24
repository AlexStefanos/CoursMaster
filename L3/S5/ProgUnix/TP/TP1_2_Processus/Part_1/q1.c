#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv) {
	int pid, i, iter_pere, iter_fils;

	if (argc != 3) {
		fprintf(stderr, "Usage : %s iteration_pere iteration_fils\n", argv[0]);
		exit(1);
	}

	iter_pere = atoi(argv[1]);
	iter_fils = atoi(argv[2]);
	pid = fork();
	if (pid < 0) {
		perror("fork");
		exit(1);
	}
	else if (pid == 0) {
		for (i = 1; i <= iter_fils; i++) {
			printf("\t\t\tFils, mon pid est %d\n", getpid());
			printf("\t\t\tFils, le pid de mon père est %d\n", getppid());
			fflush(stdout);
		}
	}
	else {
		for (i = 1; i <= iter_pere; i++) {
			printf("Père, mon pid est %d\n", getpid());
			printf("Père, le pid de mon père est %d\n", getppid());
			printf("Père, le pid de mon fils est %d\n", pid);
		}
	}
}
