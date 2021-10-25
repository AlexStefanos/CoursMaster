#include "stdlib.h"
#include "stdio.h"
#include <pthread.h>
#include "unistd.h"

#define MAX_THREADS 1000

// paramètres des threads
struct thread_param
{
	int tid;
	int nb_incr;
	long retour;
};

struct thread_param t_thread_param[MAX_THREADS];
int compteur=0; // 1) Variable de synchronisation des threads
pthread_cond_t condition_compteur;// 2) Déclaration de la variable de condition
pthread_mutex_t mutex_compteur; // 3) Sémaphore associé à la variable de condition

void *AttenteSeuil(void *threadparam)
{
	struct thread_param *mes_param;
	long retour;
	int r;
	int attente=0;
	
	mes_param = (struct thread_param *) threadparam;

	// 2) Début de section critique
	r=pthread_mutex_lock(&mutex_compteur);

	//> Le seuil non atteint
	while (compteur < mes_param->nb_incr)
	{
		printf("Thread %d: <<< J’attends ....\n", mes_param ->tid);
		attente =1;
		// 3) Attente du signal
		r=pthread_cond_wait(&condition_compteur , &mutex_compteur);
	} // while car la la condition peut ne plus etre vraie aprés pthread_cond_wait () !!
	if(attente)
	{
		printf("Thread %d: >>> J’ai reçu le signal: ", mes_param->tid);
		printf("le compteur a atteint %d\n", compteur); 
	}
	else
	{
		printf("Thread %d: >>> Je n’attends pas: ", mes_param->tid);
		printf("le compteur vaut déja %d\n", compteur);
	}
	
	printf("le compteur vaut déja %d\n", compteur);

	r = pthread_mutex_unlock (&mutex_compteur);
	printf("Thread %d: Fin \n", mes_param ->tid);
	mes_param ->retour=mes_param ->tid;
	pthread_exit((void *)mes_param ->retour);
} // AttenteSeuil

 // Code du thread 2
void *IncrCompteur(void *threadparam)
{
	struct thread_param *mes_param;
	long retour;
	int r;
	
	mes_param = (struct thread_param *) threadparam;
	printf("Thread %d: Debut\n", mes_param ->tid);

	for (;;)
	{
		//Début de section critique
		r=pthread_mutex_lock (&mutex_compteur);
		if (compteur == mes_param ->nb_incr)
		{
			//Le seuil est atteint: envoi du signal
			r=pthread_cond_signal(&condition_compteur);
			printf("Thread %d: Fin \n", mes_param ->tid);
			//> Fin de section critique
			r=pthread_mutex_unlock (&mutex_compteur);
			//> Terminaison du thread
			mes_param ->retour=mes_param ->tid;
			pthread_exit((void *)mes_param ->retour);
		}
		// 3) Le seuil n’est pas atteint: modifier la variable compteur
		else
		{
			compteur=compteur + 1;
		}

		r=pthread_mutex_unlock (&mutex_compteur);
	}
}

int main (int argc, char *argv[])
{
	int i, r, nb_threads , nb_incr;
	void *status;
	pthread_attr_t attr;
	pthread_t threads[MAX_THREADS];

	if (argc != 3)
	{
		fprintf(stderr, "Erreur usage: %s nb_threads nb_incr\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	
	printf("DEBUT MAIN\n");

	nb_threads=atoi(argv[1]);
	nb_incr=atoi(argv[2]);
	
	//Initialisation du sémaphore et de la variable de condition
	r=pthread_mutex_init (&mutex_compteur , NULL);
	r=pthread_cond_init (&condition_compteur , NULL);

	//> Initialisation et positionnement de la joignabilité des threads
	// 4) Création des threads d’incrémentation
	//> Création du thread d’attente
	//> Initialisation et positionnement de la joignabilité des threads
	//6) Attente de la fin des threads de types 1 et 2//> Libération des ressources du sémaphore
	//> Libération des ressources du sémaphore
	printf("FIN MAIN\n");
}