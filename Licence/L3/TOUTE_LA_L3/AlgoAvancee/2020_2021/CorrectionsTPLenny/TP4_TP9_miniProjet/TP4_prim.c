/*
 * Nom de fichier : prim.c
 * Contenu du fichier : Algorithme de Prim.
 * Description : L'algorithme de Prim est un algorithme qui calcule un arbre couvrant minimal dans un graphe connexe valu� et non orient� (Source : Wikipedia - CC BY-SA 3.0).
 *
 * Projet : TP4 - Arbre couvrant, Rendu Am�lior� TD/TP Partie 2 , UE Algorithmique avanc�e (IF05X040), Licence Informatique et Applications, Universit� de Paris.
 * Auteur : Leonard NAMOLARU ( La fonction prim() - bas�e sur le code donn� dans le sujet du TP4).
 */

#include <stdio.h>  // printf()
#include <stdlib.h> // malloc(), free(), exit(), EXIT_FAILURE, srand(), rand(), atof()
#include <string.h> // strrchr(), strtok()
#include <time.h> // time()

#define INFINI 1000.0 // un poids r�el sup�rieur � la plus grande longueur totale

// D�finition d'une structure de donn�es pour une repr�sentation d�ar�tes
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
 * Fonction qui retourne l�arbre couvrant de poids minimum d�un graphe valu� et non orient� depuis un sommet de r�f�rence al�atoire.
 *
 * Param�tres:
 * adjacencePoids: matrice d�adjacence pond�r�e du graphe.
 * ordre: nombre de sommets.
 */
t_arete* prim(float **adjacencePoids, int ordre) {
	// Variables locales
	t_arete* arbre; // arbre d�incidence n�ud-arc de poids minimum � retourner
	int indiceA = 0; // indice de l�arbre initialis� � 0
	int *marques;// tableau dynamique indiquant si les sommets sont marqu�s ou non
	int s, x, y, ymin; // num�ros de sommets interm�diaires
	float min; // distance minimale du prochain sommet � marquer
	int xAretePoidsMinimal;

	// Allouer l�arbre de �ordre-1� ar�tes et le tableau marque de �ordre� entiers
	marques = (int*) malloc( sizeof(int) * ordre);
        arbre   = (t_arete*) malloc( sizeof(t_arete) * (ordre - 1) );
	if(marques == NULL || arbre == NULL) {
		printf("La fonction malloc() a �chou� : espace m�moire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	// Initialiser le marquage des sommets � 0
	for(x = 0 ; x < ordre ; x++)
		marques[x] = 0;

	// Choisir un sommet s al�atoirement compris entre 0 et ordre-1
	srand( time(NULL) ); // void srand(unsigned int seed)
	s = rand()%ordre; // rand()%(MAX-MIN+1)
	// Marquer le sommet al�atoire s
	marques[s] = 1;

	//tant que les ar�tes de l�arbre ne sont pas toutes trait�es
	while (indiceA < ordre-1) {
		// Initialiser la longueur minimale � l�INFINI
		min = INFINI;

		// Pour tous les sommets x marqu�s
		// Chercher le sommet de longueur minimale �ymin� adjacent � x
		// et non marqu�
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
		// marquer le sommet �ymin� de longueur minimale
		marques[ymin] = 1;

		// Ins�rer l�ar�te (xAretePoidsMinimal, ymin) de longueur min � la position � indiceA� de l�arbre
		arbre[indiceA].sommet1 = xAretePoidsMinimal;
                arbre[indiceA].sommet2 = ymin;
                arbre[indiceA].poids = min;

		// Passer � l�ar�te suivante de l�arbre
		indiceA++;
	} // while

	return arbre; // retourner l�arbre de poids minimum
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
	printf("Les ar�tes de l'arbre : \n");
	for(i = 0 ; i < nombreAretes ; i++){
		// On augmente de 1 (+1) pour que leur num�rotation se fasse � partir de 1 et non � partir de 0.
		printf("Ar�te %d : sommet1 : %d , sommet2 : %d, poids : %.1f \n", (i+1), arbre[i].sommet1 + 1, arbre[i].sommet2 + 1, arbre[i].poids );
	}
	printf("\n");
} // printArbre()

/* int** chargeGraphe(int n)
 * La fonction prend en param�tre le nombre de sommets du graphe (Ordre du graphe).
 */
float** chargeGraphe(int n){
	int i, j;
	float** lignes = NULL;
	char* ptrNewLine;
	char* token;

	// Allocation dynamique de m�moire pour n pointeurs,
        // chacun pointant vers une ligne de la matrice.
	lignes = (float**) malloc( sizeof(float*) * n );
	if(lignes == NULL) {
		fprintf(stderr, "La fonction malloc() a �chou�: espace m�moire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	// Allocation dynamique de m�moire pour les colonnes
	for(i = 0 ; i < n ; i++) {
		*(lignes + i) = (float*) malloc( sizeof(float) * n );

		if(*(lignes + i) == NULL) {
			fprintf(stderr, "La fonction malloc() a �chou�: espace m�moire insuffisant. \n");
			exit(EXIT_FAILURE);
		}
	}

	// L'utilisateur saisit les chiffres pour chaque ligne de la matrice.
        // les chiffres sont s�par�s par une virgule.
	for(i = 0 ; i < n ; i++) {
		// (n * 2) car l'utilisateur saisit les chiffres s�par�s par une virgule + \0
		char* tmpInput = (char*) malloc( sizeof(char) * (n * 2) + 1 );
		if(tmpInput == NULL) {
			fprintf(stderr, "La fonction malloc() a �chou�: espace m�moire insuffisant. \n");
			exit(EXIT_FAILURE);
		}

		printf("Ligne %d (%d chiffres s�par�s que par des virgule) : \n", (i+1), n );
		fflush(stdout);
		scanf("%s", tmpInput);

		// Remplace le caract�re indiquant une nouvelle ligne \n
                // par le caract�re indiquant la fin d'une cha�ne \0
		ptrNewLine = strrchr(tmpInput, '\n');
		if(ptrNewLine != NULL) *ptrNewLine = '\0';

		// Lorsque l'utilisateur a entr� les chiffres, il a �t� invit� � ins�rer une virgule entre les chiffres.
                // Nous coupons maintenant la cha�ne en morceaux lorsque la virgule est le caract�re qui indique � la fonction quand couper la cha�ne.
		// Chaque chiffre de la cha�ne est converti en entier puis ins�r� dans la matrice.

		// char *strtok(char *str, const char *delim)
		token = strtok(tmpInput, ",");
		for(j = 0 ; token != NULL ; j++) {
			// La fonction atof (ASCII to float) renvoie z�ro par d�faut ou cas d'erreur,
			// donc si l'utilisateur n'entre pas assez de chiffres ou entre des caract�res incorrects,
			// ils sont automatiquement remplac�s par le chiffre 0.0
			*(*(lignes + i) + j) = atof( token );
			token = strtok(NULL, ",");
		} // for(j)

		free(tmpInput);
	}// for(i)

	return lignes;
} // chargeGraphe()