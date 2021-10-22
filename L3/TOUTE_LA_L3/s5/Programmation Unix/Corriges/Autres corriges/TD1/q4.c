#include "stdlib.h"
#include "stdio.h"
#include "unistd.h"

int main(int argc, char * argv[])
{
	int P = getpid();
	int F1 = fork();	//P

	if(F1 != 0)	//P
	{
		int F2 = fork();

		if(F2 != 0)	//P
		{

		}

		else	//F2
		{
			int F3 = fork();

			if(F3 != 0) //F2
			{
				printf("Je suis %d:F2, le frère de %d:F1\n", getpid(), F1);
			}

			else	//F3
			{
				printf("Je suis %d:F3, le petit fils de %d:P et le neveu de %d:F1\n", getpid(), P, F1);
			}
		}
	}

	else	//F1
	{

	}
	return 0;
}