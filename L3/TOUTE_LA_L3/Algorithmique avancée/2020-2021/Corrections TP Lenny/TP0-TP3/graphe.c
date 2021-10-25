/* graphe.c
 * auteur : Leonard NAMOLARU
 */
#include <stdio.h> // printf(), scanf(), fprintf()
#include <stdlib.h> // malloc(), atoi(), free(), exit(), EXIT_FAILURE
#include <string.h> // strrchr(), strtok()

/* int** chargeGraphe(int n)
 * La fonction prend en paramètre le nombre de sommets du graphe (Ordre du graphe)
 */
int** chargeGraphe(int n){
	int i, j; 
	int** lignes = NULL;
	char* ptrNewLine;
	char* token;
	
	// Allocation dynamique de mémoire pour n pointeurs, 
        // chacun pointant vers une ligne de la matrice.
	lignes = (int**) malloc( sizeof(int*) * n );
	if(lignes == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}
	
	// Allocation dynamique de mémoire pour les colonnes  
	for(i = 0 ; i < n ; i++) { 
		*(lignes + i) = (int*) malloc( sizeof(int) * n );
	
		if(*(lignes + i) == NULL) {
			fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
			exit(EXIT_FAILURE);
		}
	}
	
	// L'utilisateur saisit les chiffres pour chaque ligne de la matrice.
        // les chiffres sont séparés par une virgule. 
	for(i = 0 ; i < n ; i++) {
		// (n * 2) car l'utilisateur saisit les chiffres séparés par une virgule + \0
		char* tmpInput = (char*) malloc( sizeof(char) * (n * 2) + 1 ); 
		if(tmpInput == NULL) {
			fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
			exit(EXIT_FAILURE);
		}

		printf("Ligne %d (%d chiffres séparés que par des virgule) : \n", (i+1), n );
		fflush(stdout);
		scanf("%s", tmpInput);	
		
		// Remplace le caractère indiquant une nouvelle ligne \n 
                // par le caractère indiquant la fin d'une chaîne \0
		ptrNewLine = strrchr(tmpInput, '\n');
		if(ptrNewLine != NULL) *ptrNewLine = '\0';
		
		// Lorsque l'utilisateur a entré les chiffres, il a été invité à insérer une virgule entre les chiffres.
                // Nous coupons maintenant la chaîne en morceaux lorsque la virgule est le caractère qui indique à la fonction quand couper la chaîne.
		// Chaque chiffre de la chaîne est converti en entier puis inséré dans la matrice.
		
		// char *strtok(char *str, const char *delim)
		token = strtok(tmpInput, ",");
		for(j = 0 ; token != NULL ; j++) {
			// La fonction atoi renvoie zéro par défaut ou cas d'erreur, 
			// donc si l'utilisateur n'entre pas assez de chiffres ou entre des caractères incorrects,
			// ils sont automatiquement remplacés par le chiffre 0.
			*(*(lignes + i) + j) = atoi( token );
			token = strtok(NULL, ",");	
		} // for(j)
			
		free(tmpInput);
	}// for(i)
				
	return lignes;
} // chargeGraphe()

void printGraphe(int** adjacence, int ordre){
	int i, j;
	for(i = 0 ; i < ordre ; i++){
		for(j = 0 ; j < ordre ; j++)
			printf(" %d ", *(*(adjacence + i) + j) );
		printf("\n");
	} // for	
} // printGraphe()

void afficheSommetsMarques(char* chemin, int s) {
	printf("**** Afficher tous les sommets marqués ****\n");
	printf("Numéro du sommet de référence : %d\n", s);
	printf("%s", chemin);
	printf("\n");		
} // afficheSommetsMarques()

/* void marquerVoisins(int** adjacence, int ordre, int s)
 * Procédure qui marque tous les sommets par ordre de voisinage depuis un sommet de référence.
 * Paramètres
 * - adjacence : matrice d'adjacence du graphe.
 * - ordre : nombre de sommets
 * - s : numéro du sommet de référence
 */
void marquerVoisins(int** adjacence, int ordre, int s) {
	//Variables locales
	int* marques = NULL; //tableau dynamique indiquant si les sommets sont marqués ou non
	int x, y; // numéros de sommets intermédiaire
	char* chemin = NULL;
	int cheminIndex = 0;

	// (ordre * 2) car les chiffres dans le chemin sont séparés par une virgule + \0 
	chemin = (char*) malloc( sizeof(char) * ((ordre*2) + 1) );
	if(chemin == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}	

	//Allouer le tableau marques de taille << ordre >>
	marques = (int*) malloc( sizeof(int) * ordre);
	if(marques == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	//Initialiser les marquages à 0
	for(x = 0 ; x < ordre ; x++)
		marques[ x ] = 0;

	//Marquer le sommet s à 1
	marques[ s ] = 1;
	*(chemin + cheminIndex) = ('0' + s);
	*(chemin + (++cheminIndex) ) = ',';
	cheminIndex ++;

	// Pour tous les sommets x marqués 
        // Marquer les sommets non marqués y adjacents à x  
	for (x = 0 ; x < ordre ; x++) {
			for (y = 0 ; y < ordre ; y++) {
				 if (adjacence[x][y] &&!marques[y] && marques[x]) {
					marques[y] = 1;
					*(chemin + cheminIndex) = ('0' + y);
					*(chemin + (++cheminIndex) ) = ',';
					cheminIndex ++;
				 } // if
			} // for(y)
	} //for(x)
	*(chemin + (--cheminIndex) ) = '\0';
	afficheSommetsMarques(chemin, s);
} //marquerVoisins() 

int main(int argc, char *argv[]) {
	int ordreDuGraphe;
	int s;

	do {
		printf("Ordre du graphe (n > 0) = ? \n");
		fflush(stdout);
		scanf("%d", &ordreDuGraphe); //saisir le nombre de sommets du graphe.
	}while(ordreDuGraphe < 1); // tant que l'utilisateur n'entre pas un nombre correct (> 0)
	  

	int** adjacence = chargeGraphe( ordreDuGraphe );	
	printGraphe( adjacence, ordreDuGraphe );

	do {
		printf("Sommet de référence (s >= 0) = ? \n");
		fflush(stdout);
		scanf("%d", &s);
	}while( (s < 0) || (s > (ordreDuGraphe-1)) );
	marquerVoisins(adjacence, ordreDuGraphe, s);
		
} // main