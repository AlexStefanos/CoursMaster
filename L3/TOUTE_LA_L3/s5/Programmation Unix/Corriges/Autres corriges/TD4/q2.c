#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"
#include "fcntl.h"
#include <sys/types.h> 
#include <sys/stat.h>

void open_stat(char * fpath)
{
	struct stat fileStat;

	if(stat(fpath, &fileStat) < 0)
	{
		perror("stat");
		exit(0);
	}
	else
	{
		printf("File name :\t\t%s\n", fpath);
		printf("i-node :\t\t%d\n", (int)fileStat.st_ino);
		printf("Protection :\t\t%d\n", (int)fileStat.st_mode);
		printf("Nombre liens physique :\t%d\n", (int)fileStat.st_nlink);
		printf("ID Prop :\t\t%d\n", (int)fileStat.st_uid);
		printf("ID Group :\t\t%d\n", (int)fileStat.st_gid);
		printf("Taille fichier :\t%d\n", (int)fileStat.st_size);
		printf("Taille bloc :\t\t%d\n", (int)fileStat.st_blksize);
		printf("Nombre bloc :\t\t%d\n", (int)fileStat.st_blocks);
		//printf("Heure dernier accès :\t%s\n", fileStat.);
	}
}

void open_fstat(int file_descriptor)
{
	struct stat fileStat;

	if(fstat(file_descriptor, &fileStat) < 0)
	{
		perror("stat");
		exit(0);
	}
	else
	{
		//printf("File name :\t\t%s\n", fpath);
		printf("i-node :\t\t%d\n", (int)fileStat.st_ino);
		printf("Protection :\t\t%d\n", (int)fileStat.st_mode);
		printf("Nombre liens physique :\t%d\n", (int)fileStat.st_nlink);
		printf("ID Prop :\t\t%d\n", (int)fileStat.st_uid);
		printf("ID Group :\t\t%d\n", (int)fileStat.st_gid);
		printf("Taille fichier :\t%d\n", (int)fileStat.st_size);
		printf("Taille bloc :\t\t%d\n", (int)fileStat.st_blksize);
		printf("Nombre bloc :\t\t%d\n", (int)fileStat.st_blocks);
		//printf("Heure dernier accès :\t%s\n", fileStat.);
	}
}

void open_ltat(char * fpath)
{
	struct stat fileStat;

	if(lstat(fpath, &fileStat) < 0)
	{
		perror("stat");
		exit(0);
	}
	else
	{
		printf("File name :\t\t%s\n", fpath);
		printf("i-node :\t\t%d\n", (int)fileStat.st_ino);
		printf("Protection :\t\t%d\n", (int)fileStat.st_mode);
		printf("Nombre liens physique :\t%d\n", (int)fileStat.st_nlink);
		printf("ID Prop :\t\t%d\n", (int)fileStat.st_uid);
		printf("ID Group :\t\t%d\n", (int)fileStat.st_gid);
		printf("Taille fichier :\t%d\n", (int)fileStat.st_size);
		printf("Taille bloc :\t\t%d\n", (int)fileStat.st_blksize);
		printf("Nombre bloc :\t\t%d\n", (int)fileStat.st_blocks);
		//printf("Heure dernier accès :\t%s\n", fileStat.);
	}
}

//stat() stats the file pointed to by path and fills in buf.
//fstat() is identical to stat(), except that the file to be stat-ed is specified by the file descriptor fd. Avec le descripteur de fichier.
//lstat() is identical to stat(), except that if path is a symbolic link, then the link itself is stat-ed, not the file that it refers to. Pratique pour savoir les infos du fichier lien, pas du fichier cible.

int main(int argc, char const *argv[])
{
	char fpath[] = "f1";
	int file_descriptor = open(fpath, O_RDONLY);

	char fpath_s[] = "f1s";
	int file_descriptor_s = open(fpath_s, O_RDONLY);

	if(file_descriptor==-1 || file_descriptor_s ==-1)
	{
		perror("open");	//affiche joliement le message associé à la valeur erno
		fprintf(stderr, "Erreur lors du open() : %d\n", file_descriptor);
		exit(0);
	}
	else
	{
		open_stat(fpath);
		printf("-------------------\n");
		open_fstat(file_descriptor);
		printf("-------------------\n");
		open_stat(fpath_s);
	}

	return 0;
}