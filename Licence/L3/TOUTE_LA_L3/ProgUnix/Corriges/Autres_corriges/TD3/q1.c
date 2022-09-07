#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

// compilation avec les threads : gcc toto.c -pthread -o toto

void *dodo(void * arg)	//paramètre de type void * pour pouvoir passer tout type en paramètre
{
	int n = (int) arg;
	int i;
	
	sleep(n);
	for( i=0; i<n; i++)
		printf("\t");
		
	printf("Thread number : %d\n", n+1);
	pthread_exit(NULL);
}

int main(int argc, char ** argv)
{
	pthread_t tid[100];
	int i, t, n;
	
	if(argc < 2)
	{
		perror("Error : 1 argument demande\n");
		exit(0);
	}
	else
		n = atoi(argv[1]);
	
	for(i=0; i<n; i++)
	{
		t = pthread_create (&tid[i], NULL, dodo, (void *)i);
		
		if(t)
		{
			perror("Error : pthread_create n'a pas marché");
			exit(0);
		}
	}
	
	//exit(0);	//détruit les thread fils
	pthread_exit(NULL);	//devient zombie du coups les threads qu'il a créé continue de vivre (même si le thread main est créé détaché, ce qui est bizarre...)

	//Pourquoi le systeme le garde t-il en mode zombie ?
	//Ca lui permet de garder la valeur de retour du pss en cas de exit();
} 

