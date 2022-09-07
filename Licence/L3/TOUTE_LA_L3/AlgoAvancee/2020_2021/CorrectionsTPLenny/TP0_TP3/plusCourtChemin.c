/* plusCourtChemin.c
 * auteur : Leonard NAMOLARU
 */
#include <stdio.h> // printf(), scanf(), fprintf()
#include <stdlib.h> // malloc(), atoi(), free(), exit(), EXIT_FAILURE
#include <string.h> // strrchr(), strtok()
#include "file.h"

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
	}	
} // printGraphe()

void afficheSommetsMarques(char* chemin, int s) {
	printf("**** Afficher tous les sommets marqués ****\n");
	printf("Numéro du sommet de référence : %d\n", s);

	printf("%s", chemin);
	printf("\n");		
} // afficheSommetsMarques()

/* Procédure qui recherche le plus court chemin depuis un sommet de référence
 * Paramètres
 * adjacence : matrice dadjacence du graphe.
 * ordre : nombre de sommets
 * s : numéro de sommet de référence.
 * l : tableau dynamique alloué des longueurs minimales des sommets depuis s
 * pred : tableau dynamique alloué des prédécesseurs des sommets.  
 */
void plusCourtChemin(int** adjacence, int ordre, int s, int* l, int* pred) {
	//Variables locales
	int* marques = NULL; // tableau dynamique indiquant si les sommets sont marqués ou non
	int x, y; // numéros de sommets intermédiaires
	t_file* f; // file dattente de sommets à créer en sinspirant des listes doublement chainée avec un .h et un .c dédié
	char* chemin = NULL;
	int cheminIndex = 0;

	// (ordre * 2) car les chiffres dans le chemin sont séparés par une virgule + \0 
	chemin = (char*) malloc( sizeof(char) * ((ordre*2) + 1) );
	if(chemin == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}	
	
	//Allouer le tableau marques de taille << ordre >>
	marques = (int*) malloc( sizeof(int) * ordre );
	if(marques == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	//Initialiser les marquages et les longueurs minimales à 0
	for(x = 0 ; x < ordre ; x++) {
		marques[ x ] = 0;
		l[ x ] = 0;
	}

	//Marquer le sommet s à 1
	marques[ s ] = 1;
	*(chemin + cheminIndex) = ('0' + s); // int to ASCII
	*(chemin + (++cheminIndex) ) = ',';
	cheminIndex ++;

	//Créer (allouer) la file f et enfiler s dans f	
	f = createFile();
	enfiler( NouvElement(s) , f);


	while( !est_vide(f) ) { // Tant que la file f nest pas vide
        	x = defiler(f); // Défiler le premier sommet x de la file f

		// Pour tous les sommets y adjacents à x et non marqués
		for(y = 0 ; y < ordre ; y++) {
			if(adjacence[x][y] && !marques[y]) {
				marques[ y ] = 1; //marquer le sommet y
				*(chemin + cheminIndex) = ('0' + y); //int to ASCII
				*(chemin + (++cheminIndex) ) = ',';
				cheminIndex ++;
				
				enfiler( NouvElement(y) , f); // enfiler le sommet y dans f
				pred[ y ] = x; // x est le prédécesseur de y
				l[ y ] = l[ x ] + 1; // incrémenter la longueur de y
			}
		}
	}
	
	*(chemin + (--cheminIndex) ) = '\0';
	afficheSommetsMarques(chemin, s);
}

int main(int argc, char* argv[]) {
	int ordreDuGraphe;
	int s;
	int* l = NULL;
	int* pred = NULL;

	do {
		printf("Ordre du graphe (n > 0) = ? \n");
		fflush(stdout);
		scanf("%d", &ordreDuGraphe); //saisir le nombre de sommets du graphe.
	}while(ordreDuGraphe < 1); // tant que l'utilisateur n'entre pas un nombre correct (> 0)

	int** adjacence = chargeGraphe( ordreDuGraphe );	
	printGraphe( adjacence, ordreDuGraphe );
	
	l = (int*) malloc( sizeof(int) * ordreDuGraphe );
	if(l == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	pred = (int*) malloc( sizeof(int) * ordreDuGraphe );
	if(pred == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	

	do {
		printf("Sommet de référence (s >= 0) = ? \n");
		fflush(stdout);
		scanf("%d", &s);
	}while( (s < 0) || (s > (ordreDuGraphe-1)) );
	plusCourtChemin(adjacence, ordreDuGraphe, s, l, pred);
	
	return (0);
}