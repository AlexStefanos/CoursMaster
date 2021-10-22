#include <stdio.h> // fprintf(), printf()
#include <stdlib.h> // atoi(), malloc(), exit(), EXIT_FAILURE
#include <pthread.h> // pthread_create(), pthread_attr_init(), pthread_attr_setdetachstate

// La grille sera implémentée par un tableau à deux dimensions de caractères.
int nb_lignes = atoi( argv[1] ) , nb_colonnes = atoi( argv[2] );
char** tab = NULL;

void affichage();
void cellule(int i, int j);
int main(int argc, char *argv[]) {
	/* L’appel du programme se fera de la façon suivante :
	 * game_of_life X Y x1 y1 x2 y2 … xN yN
	 * ou X Y sont les dimensions de la grille
	 * et xi yi sont les coordonnée d’une cellule vivante au démarrage de l’automate.
	 */
	if( argc < 3 ) {
		fprintf(stderr, "Utilisation : %s X Y x1 y1 x2 y2 … xN yN \n", argv[0]);
		exit(EXIT_FAILURE);
	}

	tab = (char**) malloc( nb_lignes * sizeof(char *) );
	if(tab == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué \n");
		exit(EXIT_FAILURE);
	}

	int i, j, t, function_result;
	for(i = 0 ; i < nb_lignes ; i++)
		tab[i] = (char *) malloc( (nb_colonnes + 1) * sizeof(char) );

	// tab[i][j] pareill que *(*(tab+i)+j)
	for(i = 0 ; i < nb_lignes ; i++){
		for(j = 0 ; j < nb_colonnes ; j++) {
			tab[i][j] = ' ';
		}
		tab[i][nb_colonnes] = '\0';
	}

	// Une cellule vivante sera représentée par le caractère '*'
	//une cellule morte par un blanc.
	for(i = 3 ; i < argc -1 ; i += 2) {
		char positionLigne[2];
		positionLigne[0] = argv[i][1];
		positionLigne[1] = '\0';

		char positionColonne[2];
		positionColonne[0] = argv[i+1][1];
		positionColonne[1] = '\0';

		tab[atoi(positionLigne)][atoi(positionColonne)] = '*';
	}

	// L'identifiant d'un thread est du type pthread_t
	// Il y aura X*Y threads cellule
	pthread_t* threads = (pthread_t *) malloc( (nb_lignes * nb_colonnes) * sizeof(pthread_t) );

	pthread_attr_t attr; // Déclaration d’une variable de type pthread_attr_t
	pthread_attr_init( &attr );  // Initialisationdecettevariable avecpthread_attr_init()
	pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHSTATE);

	for(t = 0 ; t < (nb_lignes * nb_colonnes) ; t++) {
		function_result = pthread_create(&threads[t], &attr, cellule, (void *)t);
		if(function_result != 0) { // 0 en cas de succès ; une valeur autre que 0 en cas d’erreur
			fprintf(stderr, "ERREUR : pthread_create() a retourné %d \n", function_result);
			exit(EXIT_FAILURE);
		}
	}

	pthread_t tid; // L’identifiant d’un thread est du type pthread_t
	pthread_attr_init( &attr ); // Initialisation d’une variable de type pthread_attr_t avec pthread_attr_init()
	pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE); // Créer explicitement les threads à l’état joignable
	function_result = pthread_create(&tid, &attr, affichage, (void *) (nb_lignes * nb_colonnes));
	if( function_result != 0 ) {
		fprintf(stderr, "EREUR : pthread_create() a retourné %d \n", function_result);
		exit(EXIT_FAILURE);
	}
	pthread_attr_destroy(&attr); // Libération des ressources avec pthread_attr_destroy() une fois la création du thread terminée
	function_result = pthread_join(tid, NULL);
	if( function_result != 0 ) {
		fprintf(stderr, "ERREUR : pthread_join() a retourné %d \n", function_result);
		exit(EXIT_FAILURE);
	}
} // main

void affichage(void* thread_number) {
	int i, j;
	for(i = 0 ; i < nb_lignes ; i++) {
		for(j = 0 ; j < nb_colonnes ; j++) {
			printf("%c", tab[i][j]);
		}
		printf("\n");
	}
} // affichage()

void cellule(void* thread_number) {
	int countVivantes = 0;

	if ( i != 0 && (tab[i-1][j] == '*') ) countVivantes++;
	if ( i != (nb_lignes-1) && (tab[i+1][j] == '*') ) countVivantes++;
	if ( j != 0 && (tab[i][j-1] == '*') ) countVivantes++;
	if ( j != (nb_colonnes-1) && (tab[i][j+1] == '*') ) countVivantes++;

	if(tab[i][j] == ' ') { // Une cellule morte
		if(countVivantes == 3)
			tab[i][j] == '*';
	} else {// Une cellule vivante
		if(countVivantes == 2 || countVivantes == 3)
			tab[i][j] == '*';
		else
			tab[i][j] == ' ';
	}
} // cellule()
