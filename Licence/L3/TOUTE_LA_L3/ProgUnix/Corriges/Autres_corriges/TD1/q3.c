#include "stdlib.h"
#include "stdio.h"
#include "unistd.h"

int main(int argc, const char * argv[])
{
	int x;

	if(argc == 2)
		x = atoi(argv[1]);
	else
		x = 10;

	int pid = fork();

	while(x > 0)
	{
		if(pid == 0)
			printf("FILS : %d : %p\n", x, &x);
		else
			printf("PERE : %d : %p\n", x, &x);
		x--;
	}
	return 0;
}