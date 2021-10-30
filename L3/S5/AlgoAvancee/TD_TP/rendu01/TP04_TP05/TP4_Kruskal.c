#include <stdio.h>
#include <stdlib.h>
#define INFINI 1000.0

typedef struct t_arete {
	int sommet1;
    int sommet2;
	float poids;
} t_arete ;

t_arete* chargeGraphe(int tailleDuGraphe, int ordreDuGraphe);
void printAretes(t_arete* tableauAretes, int nombreAretes);

void triParSelection(t_arete * tableauAretes, int nombreAretes);
int rechercheIndicePlusPetit(t_arete * tableauAretes, int nombreAretes, int i);
void echanger(t_arete * tableauAretes, int i, int j);

t_arete * kruskal(t_arete * graphe, int ordre, int s, int n) {
	t_arete *arbre = NULL ;
	int *connexe ;
	int indiceA = 0, indiceG = 0 ;
	int x, s1, s2 ;
	t_arete u ;
  arbre   = (t_arete*) malloc( sizeof(t_arete) * (ordre - 1) );
  connexe = (int*) malloc( sizeof(int) * ordre);

  if(connexe == NULL || arbre == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué : espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}
  for (x = 0 ; x < ordre ; x++)
		connexe[x] = x ;
  triParSelection(graphe, n);
	while (indiceA < ordre-1 && indiceG < n) {
		u = graphe[indiceG] ;
		s1 = connexe[u.sommet1] ; s2 = connexe[u.sommet2] ;

		if (s1 == s2)
			indiceG++ ;
		else {
			arbre[indiceA] = u ;
			indiceA++ ; indiceG++ ;
			for (x  = 0 ; x < ordre ; x++) {
				if (connexe[x]==s1)
					connexe[x] = s2 ;
			}
		}
	}
  if (indiceA < ordre-1) {
    	fprintf(stderr, "Le graphe n'est pas connexe \n") ;
    	exit(EXIT_FAILURE);
    }

    return arbre ;
}

void triParSelection(t_arete * tableauAretes, int nombreAretes) {
	int i;
	for(i = 0 ; i < nombreAretes - 1 ; i++) {
		int indiceMin = rechercheIndicePlusPetit(tableauAretes, nombreAretes, i);
		if(indiceMin != i) echanger(tableauAretes, i, indiceMin);
	}
}

int rechercheIndicePlusPetit(t_arete * tableauAretes, int nombreAretes, int i) {
	int indiceMin = i;
	int j;
	for(j = i + 1 ; j < nombreAretes ; j++) {
		if(tableauAretes[j].poids < tableauAretes[indiceMin].poids) indiceMin = j;
	}

	return indiceMin;
}

void echanger(t_arete * tableauAretes, int i, int j) {
	t_arete tmpVal = tableauAretes[i];
	tableauAretes[i] = tableauAretes[j];
	tableauAretes[j] = tmpVal;
}

int main(int argc, char *argv[]) {
	int tailleDuGraphe, ordreDuGraphe, sommetDeReference;

	do {
		printf("Taille du graphe (n > 0) = ? \n");
		fflush(stdout);
		scanf("%d", &tailleDuGraphe);
	}while(tailleDuGraphe < 1);

	do {
		printf("Ordre du graphe (n > 0) = ? \n");
		fflush(stdout);
		scanf("%d", &ordreDuGraphe);
	}while(ordreDuGraphe < 1);

	printf("\n");
	t_arete* tableauAretesDuGraphe = chargeGraphe( tailleDuGraphe, ordreDuGraphe );
	printf("\n");

	printf("Les arêtes du graphe : \n");
	printAretes( tableauAretesDuGraphe , tailleDuGraphe );

	do {
		printf("Sommet de référence (s >= 0) = ? \n");
		fflush(stdout);
		scanf("%d", &sommetDeReference);
	}while( (sommetDeReference < 0) || (sommetDeReference > (ordreDuGraphe-1)) );

	t_arete* arbre = kruskal(tableauAretesDuGraphe, ordreDuGraphe, sommetDeReference, tailleDuGraphe);
	printf("Les arêtes de l'arbre : \n");
	int nombreAretes = ordreDuGraphe - 1;
	printAretes( arbre , nombreAretes );
}

void printAretes(t_arete* tableauAretes, int nombreAretes){
	int i;
	for(i = 0 ; i < nombreAretes ; i++)
		printf("Arête %d : sommet1 : %d , sommet2 : %d , poids : %.1f \n", i, tableauAretes[i].sommet1, tableauAretes[i].sommet2, tableauAretes[i].poids );
	printf("\n");
}

t_arete* chargeGraphe(int tailleDuGraphe, int ordreDuGraphe){
	int i;
	t_arete* tableauAretes = NULL;

	tableauAretes = (t_arete*) malloc( sizeof(t_arete) * tailleDuGraphe );
	if(tableauAretes == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	printf("Les arêtes du graphe : \n");
	printf("Nous commençons le comptage des sommets et des arêtes à partir de zéro \n");
	for(i = 0 ; i < tailleDuGraphe ; i++) {
		printf("Arête %d : \n", i);

		do {
			printf("\t sommet 1 : ");
			fflush(stdout);
			scanf("%d", &tableauAretes[i].sommet1);
		}while( (tableauAretes[i].sommet1 < 0) || (tableauAretes[i].sommet1 > (ordreDuGraphe-1)) );

		do {
			printf("\t sommet 2 : ");
			fflush(stdout);
			scanf("%d", &tableauAretes[i].sommet2);
		}while( (tableauAretes[i].sommet2 < 0) || (tableauAretes[i].sommet2 > (ordreDuGraphe-1)) );

		do {
			printf("\t poids    : ");
			fflush(stdout);
			scanf("%f", &tableauAretes[i].poids);
		}while(tableauAretes[i].poids < 0);
	}

	return tableauAretes;
}
