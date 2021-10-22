#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define NB 40

//visualisation des thread : ps -m -o pid -o tid -o bnd -o -c -o comm

char livre[NB];
int carac[26];

/*
	Incrémente la case du tableau associé au caratère c
*/
void compte(char c)
{
	carac[c-65]++;
}

/*
	Copie une instance du livre a un instant t,
	Compte le nombre d'itération,
	Affiche le livre
*/
void lire()
{
	int i=0;
	char tabChar[1000];
	
	//Parcours du livre (tant que c'est une lettre de l'alphabet)
	while(livre[i] >=65 && livre[i] < (65+26))
	{
		//tabChar = livre à un instant t
		tabChar[i] = livre[i];
		compte(tabChar[i]);

		i++;
	}
	tabChar[i] = '\0';

	i=0;
	printf("Lecteur : ");
	while(tabChar[i] != '\0')
	{
		printf("%c", tabChar[i]);
		i++;
	}

	printf("\n");
}

/*
	Ecrit dans le tableau le char associé au n° de l'écrivain
*/
void ecrire(void * ecrivain)
{	
	int i, tid = (int) ecrivain;
	
	printf("Execution de l'ecrivain %d\n", tid+1);

	while(1)
	{
		for(i=0; i<NB; i++)
		{
			livre[i] = 65+ecrivain;		//65 = A dans la table ASCII
		}
	}
	//pthread_exit(NULL);
}

/*
	Affiche plusieurs fois le livre
*/
void lirePlusieursFois(void * plusieurs)
{
	int i, n = (void *)plusieurs;
	
	for(i=0; i<n; i++)
	{
		lire();
	}
	
	pthread_exit(NULL);
}

/*
	Affiche le nombre de recurrence	de chaque lettre
*/
void afficheRecurrenceLettre(int nbEcrivain)
{
	int i;

	for (i = 0; i < nbEcrivain; ++i)
	{
		printf("%c : %d\n", i+65, carac[i]);
	}
}


/**********************
		MAIN
**********************/
int main(int argc, char ** argv)
{
	int nbEcrivain, plusieurs, r, i;
	pthread_t lecteur, threads[100];
	
	/*
		Vérification des arguments
	*/
	if(argc < 3)
	{
		perror("Error : 1 argument demande\n");
		exit(0);
	}
	else
	{
		nbEcrivain = atoi(argv[1]);
		if(nbEcrivain >26)
		{
			perror("Error : bad first argument (<26)");
			exit(0);
		}
		
		plusieurs = atoi(argv[2]);
		if(plusieurs <1)
		{
			perror("Error : bad second argument (>0)");
			exit(0);
		}
		
	}
	
	/*
		Création des n threads écrivains détachés
	*/
	for(i=0; i<nbEcrivain; i++)
	{
		printf("Creation du thread ecrivain %d/%d\n", i+1, nbEcrivain);
		
		r = pthread_create(&threads[i], NULL, ecrire, (void *)i);
		if(r)
		{
			perror("Error : pthread_create failed");
			exit(0);
		}
	}
	
	/*
		Création du lecteur
	*/
	printf("Creation du thread lecteur\n");
	
	//initialisation de attr
	pthread_attr_t attr;
	pthread_attr_init(&attr);
	pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);

	r = pthread_create(&lecteur, &attr, lirePlusieursFois, (void *)plusieurs);	
	if(r)
	{
		perror("Error : pthread_create failed");
		exit(0);
	}

	/*
		Attente du thread lecteur
	*/
	//sleep(1);
	void *status;
	r = pthread_join(lecteur, &status);

	if(r != 0)
	{
		perror("Error : pthread_join failed");
		exit(0);
	}

	//printf("lecteur = %d\n", lecteur);
	afficheRecurrenceLettre(nbEcrivain);
	

	//pthread_exit(NULL);
	//exit(0);
}
