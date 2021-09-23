#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char **argv) {
	int w, x, y, p, pid;

	p = getpid();
	x = fork();
	if (x < 0) {
		perror("fork");
		exit(EXIT_FAILURE);
	}

	else if (x == 0) {
		sleep(40); /*les sleep() ne sont pas nécessaires mais ils permettent d'avoir le 
			   temps de pouvoir vérifier les résultats après l'exécution du prog à l'aide de la commande : ps aux | grep alexand*/
	}

	else {
		pid = fork();
		if (pid < 0) {
			perror("fork");
			exit(EXIT_FAILURE);
		}

		else if (pid == 0) {
			printf("Je suis %d : F2, le frère de %d, F1\n", w, x);
			pid = fork();
			if (pid < 0) {
				perror("fork");
				exit(EXIT_FAILURE);
			}
			else if (pid == 0) {
				y = getpid();
				printf("Je suis %d : F3, le petit fils de %d : P et le neveu de %d : F1\n", y, p, x);
				sleep(40);
				_exit(EXIT_SUCCESS);
			}
			else {
			}
			sleep(40);
		}
		else {
		}
	}
}
