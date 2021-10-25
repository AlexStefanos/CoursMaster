#include "stdio.h"
#include "stdlib.h"
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/uio.h>
#include <unistd.h>
#include <sys/uio.h>

#define BUFFER_SIZE 8

//Fichier pas régulier aura comme préfix c, d (pour directory), les tubes, les drivers etc...
int main(int argc, char const *argv[])
{
	char buffer[BUFFER_SIZE];
	char path_src[] = "f_src";
	char path_dst[] = "f_dst";
	int fd_src = open(path_src, O_RDONLY | O_EXCL);
	int fd_dst = open(path_dst, O_WRONLY | O_EXCL);

	if(fd_src == -1 && fd_dst == -1)
	{
		perror("Erreur lors du open");
		exit(0);
	}
	else
	{
		struct stat fileStat;
		if(stat(path_src, &fileStat) < 0)
		{
			perror("Erreur lors du stat");
			exit(0);
		}
		else
		{
			if(S_ISREG(fileStat.st_mode) == 0) //then NOT a regular file
			{
				printf("File is not a regular file.\n");
				exit(0);
			}
			else
			{
				/*
					Tant qu'il n'y a pas d'erreur de lecteur et
					jusqu'à ce qu'on a lu une taille < au buffer (dernier morceau)
				*/
				int r;
				int w;
				while((r = read(fd_src, buffer, BUFFER_SIZE)) != 0)	//3eme argement doit être < au buffer (on a un seau de 3L mais on est pas obliger de prendre forcément 3L à chaque fois)
				{
					if(r == -1)
					{
						perror("Erreur lors du read");
						exit(0);
					}
					else
					{
						w = write(fd_dst, buffer, r);	//on écrit autant qu'on a lu
						
						if(w == -1)
						{
							perror("Erreur lors du write");
							exit(0);
						}
						else
						{
							printf("r = %d, w = %d\n", r, w);
							printf("%s\n", buffer);
						}
					}
				}
			}
		}
	}

	return 0;
}