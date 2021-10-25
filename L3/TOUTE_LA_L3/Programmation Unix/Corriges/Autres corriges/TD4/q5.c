#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"
#include "fcntl.h"
#include <sys/file.h>
#include "string.h"

int main(int argc, char const *argv[])
{
	if(argc != 5)
	{
		perror("4 arguments necessaires : file, mode (s/x), debut, longueur\n");
		exit(0);
	}

	struct flock verrou;
	int fd;
	int v_fcntl;

	//exlusif (donc plutot ecriture)
	if(*argv[2] == 'x')		//équivalent : if(!strcmp(argv[2], "x"))
	{
		fd = open(argv[1], O_WRONLY);	//O_RDWR possible
		verrou.l_type = F_WRLCK;

		printf("fd = %d\n", fd);
	}
	else if(*argv[2] == 's')
	{
		fd = open(argv[1], O_RDONLY);	//O_RDWR possible
		verrou.l_type = F_RDLCK;

		printf("fd = %d\n", fd);
	}
	else
	{
		perror("Bad 2nd arg (mode)");
		exit(0);
	}

	verrou.l_whence = SEEK_SET;
	verrou.l_start = atoi(argv[3]);
	verrou.l_len = atoi(argv[4]);

	v_fcntl = fcntl(fd, F_GETLK, &verrou);	//get les verrous deja existant

	if(verrou.l_type != F_UNLCK)	//il existe un verrou
		printf("VERROU\n");

	printf("GET : verrou.l_type : %d\n", verrou.l_type);
	printf("GET : verrou.l_len : %lld\n", verrou.l_len);
	printf("GET : verrou.l_start : %lld\n", verrou.l_start);

	v_fcntl = fcntl(fd, F_SETLKW, &verrou);		//F_SETLK : non bloquant

	//EAGAIN OU EACCES sont les erreurs lorsqu'on veut poser un verrou sur un verrou déjà posé

	printf("v_fcntl : %d\n", v_fcntl);


	while(1)
		sleep(10);

	return 0;
}