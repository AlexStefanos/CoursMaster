#include <unistd.h>
#include <stdio.h>

int main(int argc, char **argv) {
	pid_t pid;
	pid = fork();
	if (pid == 0) 
		printf("Bonjour, je suis le fils : %d\n", getpid());
	else
		printf("Bonjour, je suis le père : %d\n", getpid());
	printf("FIN : %d\n", getpid());
}
