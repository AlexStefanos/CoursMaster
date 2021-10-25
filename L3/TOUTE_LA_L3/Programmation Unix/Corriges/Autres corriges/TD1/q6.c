#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"

int main(int argc, char const *argv[])
{
	int pid = fork();

	if(pid == 0)
	{
		FILE * f = NULL;
		f = fopen("f1", "r+");
		fputs("Hello noodle", f);
		exit(0);	
	}
	else
	{
		FILE * f = NULL;
		f = fopen("f2", "r+");
		fputs("Hello noodle", f);
		_exit(0);	//Pas d'écriture	
	}

	return 0;
}