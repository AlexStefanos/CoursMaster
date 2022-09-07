#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main (int argc, char **argv) {
	pid_t pid;
	int status;
	pid = fork();
	if (pid == 0) {
		printf("Bonjour, je suis le fils : %d\n", getpid());
		sleep(20);
		exit (EXIT_SUCCESS);
	}
	printf("Bonjour, je suis le père : %d\n", getpid());
	printf("J'attends la fin de mon fils : %d\n", pid);
	pid = wait(&status);
	printf("Mon fils %d s'est terminé avec la valeur %d\n", pid, status);
}
