#include "stdio.h"
#include "stdlib.h"
#include "sys/uio.h"
#include "unistd.h"
#include <fcntl.h>

int main(int argc, char const *argv[])
{
	int fdError = open("fError", O_WRONLY | O_CREAT, 0755);
	printf("%d\n", fdError);
	dup2(fdError, 2); 	// duplique à l'entrée 2 de l'u_ofile ce que pointe fdError
	//ecrire dans 2 ou ecrire dans fdError revient au même

	write(2, "Big error de fou\n", 18);

	return 0;
}