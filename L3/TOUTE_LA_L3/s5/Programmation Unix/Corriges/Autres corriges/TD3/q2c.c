#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define NB 50
/*
INDICATIONS :
Utiliser l'exclusion mutuel (mutex) pour avoir le droit d'écrire dans le tabl
Utiliser les variables de condition
Visualisation des threads : ps -m -o pid -o tid -o bnd -o -c -o comm

*/

char livre[NB];
int carac[26];
int nbSpace;
pthread_mutex_t mutex_ecrivain;
pthread_mutex_t mutex_space;
pthread_cond_t condition;

//Incrémente la case du tableau associé au caratère c
void compte(char c)
{
	carac[c-65]++;	//ex C = 67 donc j'incremente 67-65 -> carac[2]++
}

//Retourne 0 s'il y a au moins un espace, 1 sinon
int fullArray()
{
	int i;
	for (i = 0; i < NB; ++i)
	{
		if(livre[i] == ' ')
			return 0;
	}
	return 1;
}

//Compte le nombre d'itération, Affiche le livre
void lire()
{
	int i;

	pthread_mutex_lock(&mutex_space);

	while(nbSpace < NB)
	{
		pthread_cond_wait(&condition, &nbSpace);
		printf("Lecteur : ");

		for(i=0; i< NB; i++)
		{
			if(livre[i] != ' ')
				compte(livre[i]);
			
			printf("%c", livre[i]);
			livre[i] = ' ';
			nbSpace--;
		}

		printf("\n");
	}
	pthread_cond_signal(&condition);
	pthread_mutex_unlock(&mutex_space);
}

/*
	Ecrit dans le tableau le char associé au n° de l'écrivain
*/
void ecrire(void * ecrivain)
{	
	int i, r, tid = (int) ecrivain;
	
	printf("Execution de l'ecrivain %d\n", tid+1);

	while(1)
	{
		for(i=0; i<NB; i++)
		{
			if(nbSpace < NB )
			{
				pthread_cond_wait(&condition, &nbSpace);
				if(livre[i] == ' ')
				{
					//Entrée dans la zone critique
					r = pthread_mutex_lock(&mutex_ecrivain);
					if(r) { perror("Error mutex lock"); exit(0); }

					livre[i] = 65+tid;		//65 = A dans la table ASCII
					nbSpace++;

					//Sortie de la zone critique
					r = pthread_mutex_unlock(&mutex_ecrivain);
					if(r) { perror("Error mutex unlock"); exit(0); }
				}
			}
			else
			{
				pthread_cond_signal(&condition); //s'il n'y a que des espaces
			}
		}
	}
}

/*
	Affiche plusieurs fois le livre
*/
void lirePlusieursFois(void * plusieurs)
{
	int i, n = (void *)plusieurs;
	
	for(i=0; i<n; i++)
		lire();
	
	pthread_exit(NULL);
}

/*
	Affiche le nombre de recurrence	de chaque lettre
*/
void afficheRecurrenceLettre(int nbEcrivain)
{
	int i;

	for (i = 0; i < nbEcrivain; ++i)
		printf("%c : %d\n", i+65, carac[i]);
}


/******************************************************************
							MAIN
******************************************************************/
int main(int argc, char ** argv)
{
	int nbEcrivain, plusieurs, r, i;
	pthread_t lecteur, threads[26];

	//initialisation du mutex
	pthread_mutex_init(&mutex_ecrivain, NULL);
	pthread_mutex_init(&mutex_space, NULL);
	pthread_cond_init(&condition, NULL);

	// Initialisation du livre
	for (int i = 0; i < NB; ++i)
		livre[i] = ' ';
	
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
		
		r = pthread_create(&threads[i], NULL, ecrire, (void *)i);	//il faudrait le faire explicitement en pas joignable pour des questions de ressources
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
