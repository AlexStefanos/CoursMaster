#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

int position = 0;
int taille_phrase;

void init_verrou(struct flock verrou)
{
	verrou.l_type=F_WRLCK;
	verrou.l_whence=SEEK_SET;
	verrou.l_start=0;
	verrou.l_len=taille_phrase;
	verrou.l_pid=getpid();
}


int main(int argc, char const *argv[])
{
	
	if (argc!=3)
	{
		fprintf(stderr, "Usage:%s file phrase\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	int fd;
	if((fd = open(argv[1], O_RDWR|O_CREAT|O_TRUNC , 0644))< 0)
	{
		perror("File");
		exit(EXIT_FAILURE);
	}

	char *phrase;
	phrase=strdup(argv[2]);
	taille_phrase=strlen(phrase);
	char buffer[1];

	int verif_verrou;
	
	
	//printf("%s\n", c);
	
	int pid;

	if((pid =fork()) <0)
	{
		perror("Fork :");
		exit(EXIT_FAILURE);
	}

	
	if(pid!=0) //PERE
	{
		struct flock verrou;

		init_verrou(verrou);
		while(position<taille_phrase)
		{
			//for(int i=0; i<strlen(argv[2]); i++)
			//{
			verrou.l_type=F_WRLCK;
			if(position%2==0)
			{
				if(verif_verrou=fcntl(fd, F_SETLK, &verrou)<0)
				{
					perror("P1 pose du verrou");
				}
				else
				{
					buffer[0]=phrase[position];
					printf("buffer = %s\n",buffer);
					if(write(fd, buffer, sizeof(char))<0)
						perror("write : ");
					position++;	
				}
				verrou.l_type=F_UNLCK;
				if(verif_verrou=fcntl(fd, F_SETLK, &verrou)<0)
				{
					perror("P2 pose du verrou");
				}
			}
		}	

		
	}
	else if(pid==0) //FILS
	{
		struct flock verrou;

		init_verrou(verrou);
		while(position<taille_phrase)
		{
			if(position%2!=0)
				{
					if(verif_verrou=fcntl(fd, F_SETLK, &verrou)<0)
					{
						perror("P2 pose du verrou");
					}
					else
					{
						buffer[0]=phrase[position];
						printf("buffer = %s\n",buffer);
						if(write(fd, buffer, sizeof(char))<0)
							perror("write : ");
						position++;
					}
				}
		}
	}
	close(fd);

	exit(EXIT_SUCCESS);






	return 0;
}