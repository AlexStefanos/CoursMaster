/*
 * Nom de fichier : prim.c
 * Contenu du fichier : Algorithme de Prim.
 * Description : L'algorithme de Prim est un algorithme qui calcule un arbre couvrant minimal dans un graphe connexe valué et non orienté (Source : Wikipedia - CC BY-SA 3.0).
 *
 * Projet : TP4 - Arbre couvrant, Rendu Amélioré TD/TP Partie 2 , UE Algorithmique avancée (IF05X040), Licence Informatique et Applications, Université de Paris.
 * Auteur : Leonard NAMOLARU ( La fonction prim() - basée sur le code donné dans le sujet du TP4).
 */

#include <stdio.h>  // printf()
#include <stdlib.h> // malloc(), free(), exit(), EXIT_FAILURE, srand(), rand(), atof()
#include <string.h> // strrchr(), strtok()
#include <time.h> // time()

#define INFINI 1000.0 // un poids réel supérieur à la plus grande longueur totale

// Définition d'une structure de données pour une représentation d’arêtes
typedef struct t_arete {
	int sommet1;
    int sommet2;
	float poids;
} t_arete ;

// Prototypage des fonctions
float** chargeGraphe(int n);
void printGraphe(float** adjacencePoids, int ordre);
void printArbre(t_arete* arbre, int nombreAretes);

/* t_arete* prim(float **adjacencePoids, int ordre)
 * Fonction qui retourne l’arbre couvrant de poids minimum d’un graphe valué et non orienté depuis un sommet de référence aléatoire.
 *
 * Paramètres:
 * adjacencePoids: matrice d’adjacence pondérée du graphe.
 * ordre: nombre de sommets.
 */
t_arete* prim(float **adjacencePoids, int ordre) {
	// Variables locales
	t_arete* arbre; // arbre d’incidence nœud-arc de poids minimum à retourner
	int indiceA = 0; // indice de l’arbre initialisé à 0
	int *marques;// tableau dynamique indiquant si les sommets sont marqués ou non
	int s, x, y, ymin; // numéros de sommets intermédiaires
	float min; // distance minimale du prochain sommet à marquer
	int xAretePoidsMinimal;

	// Allouer l’arbre de «ordre-1» arêtes et le tableau marque de «ordre» entiers
	marques = (int*) malloc( sizeof(int) * ordre);
        arbre   = (t_arete*) malloc( sizeof(t_arete) * (ordre - 1) );
	if(marques == NULL || arbre == NULL) {
		printf("La fonction malloc() a échoué : espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	// Initialiser le marquage des sommets à 0
	for(x = 0 ; x < ordre ; x++)
		marques[x] = 0;

	// Choisir un sommet s aléatoirement compris entre 0 et ordre-1
	srand( time(NULL) ); // void srand(unsigned int seed)
	s = rand()%ordre; // rand()%(MAX-MIN+1)
	// Marquer le sommet aléatoire s
	marques[s] = 1;

	//tant que les arêtes de l’arbre ne sont pas toutes traitées
	while (indiceA < ordre-1) {
		// Initialiser la longueur minimale à l’INFINI
		min = INFINI;

		// Pour tous les sommets x marqués
		// Chercher le sommet de longueur minimale «ymin» adjacent à x
		// et non marqué
		for (x = 0 ; x < ordre; x++) {
			if (marques[x]) {
				for ( y = 0 ; y < ordre; y++) {
					if (adjacencePoids[x][y] && !marques[y] && adjacencePoids[x][y] < min) {
						min = adjacencePoids[x][y]; // poids min
						ymin = y; // sommet y de poids min
						xAretePoidsMinimal = x;
					} // if
				} // for(y)
			} // if
		} // for(x)
		// marquer le sommet «ymin» de longueur minimale
		marques[ymin] = 1;

		// Insérer l’arête (xAretePoidsMinimal, ymin) de longueur min à la position « indiceA» de l’arbre
		arbre[indiceA].sommet1 = xAretePoidsMinimal;
                arbre[indiceA].sommet2 = ymin;
                arbre[indiceA].poids = min;

		// Passer à l’arête suivante de l‘arbre
		indiceA++;
	} // while

	return arbre; // retourner l’arbre de poids minimum
} // prim()

int main(int argc, char *argv[]) {
	int ordreDuGraphe;
	int s;

	do {
		printf("Ordre du graphe (n > 0) = ? \n");
		fflush(stdout);
		scanf("%d", &ordreDuGraphe); //saisir le nombre de sommets du graphe.
	}while(ordreDuGraphe < 1); // tant que l'utilisateur n'entre pas un nombre correct (> 0)


	float** adjacencePoids = chargeGraphe( ordreDuGraphe );
	printf("\n");
	printGraphe( adjacencePoids, ordreDuGraphe );
	printf("\n");

	t_arete* arbre = prim(adjacencePoids, ordreDuGraphe);
	int nombreAretes = ordreDuGraphe - 1;
	printArbre( arbre , nombreAretes);
} // main()


/* *********************************************************************** */
void printGraphe(float** adjacencePoids, int ordre){
	int i, j;
	printf("Le graphe : \n");
	for(i = 0 ; i < ordre ; i++){
		for(j = 0 ; j < ordre ; j++)
			printf(" %.1f ", *(*(adjacencePoids + i) + j) );
		printf("\n");
	} // for
} // printGraphe()

void printArbre(t_arete* arbre, int nombreAretes){
	int i;
	printf("Les arêtes de l'arbre : \n");
	for(i = 0 ; i < nombreAretes ; i++){
		// On augmente de 1 (+1) pour que leur numérotation se fasse à partir de 1 et non à partir de 0.
		printf("Arête %d : sommet1 : %d , sommet2 : %d, poids : %.1f \n", (i+1), arbre[i].sommet1 + 1, arbre[i].sommet2 + 1, arbre[i].poids );
	}
	printf("\n");
} // printArbre()

/* int** chargeGraphe(int n)
 * La fonction prend en paramètre le nombre de sommets du graphe (Ordre du graphe).
 */
float** chargeGraphe(int n){
	int i, j;
	float** lignes = NULL;
	char* ptrNewLine;
	char* token;

	// Allocation dynamique de mémoire pour n pointeurs,
        // chacun pointant vers une ligne de la matrice.
	lignes = (float**) malloc( sizeof(float*) * n );
	if(lignes == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	// Allocation dynamique de mémoire pour les colonnes
	for(i = 0 ; i < n ; i++) {
		*(lignes + i) = (float*) malloc( sizeof(float) * n );

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
			// La fonction atof (ASCII to float) renvoie zéro par défaut ou cas d'erreur,
			// donc si l'utilisateur n'entre pas assez de chiffres ou entre des caractères incorrects,
			// ils sont automatiquement remplacés par le chiffre 0.0
			*(*(lignes + i) + j) = atof( token );
			token = strtok(NULL, ",");
		} // for(j)

		free(tmpInput);
	}// for(i)

	return lignes;
} // chargeGraphe()