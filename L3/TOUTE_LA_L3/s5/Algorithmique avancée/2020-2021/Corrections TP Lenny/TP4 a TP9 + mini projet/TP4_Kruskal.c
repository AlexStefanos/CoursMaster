/*
 * Nom de fichier : kruskal.c
 * Contenu du fichier : Algorithme de Kruskal.
 * Description : L'algorithme de Kruskal est un algorithme qui calcule un arbre couvrant minimal dans un graphe connexe valué et non orienté (Source : Wikipedia - CC BY-SA 3.0).
 *
 * Projet : TP4 - Arbre couvrant, Rendu Amélioré TD/TP Partie 2 , UE Algorithmique avancée (IF05X040), Licence Informatique et Applications, Université de Paris.
 * Auteur : Leonard NAMOLARU ( La fonction kruskal() - basée sur le code donné dans le sujet du TP4).
 */

#include <stdio.h>  // printf(), fprintf()
#include <stdlib.h> // malloc(), exit(), EXIT_FAILURE, atof()
#define INFINI 1000.0 // un poids réel supérieur à la plus grande longueur totale

// Définition d'une structure de données pour une représentation d’arêtes
typedef struct t_arete {
	int sommet1;
    int sommet2;
	float poids;
} t_arete ;

// Prototypage des fonctions
t_arete* chargeGraphe(int tailleDuGraphe, int ordreDuGraphe);
void printAretes(t_arete* tableauAretes, int nombreAretes);

void triParSelection(t_arete * tableauAretes, int nombreAretes);
int rechercheIndicePlusPetit(t_arete * tableauAretes, int nombreAretes, int i);
void echanger(t_arete * tableauAretes, int i, int j);

/* t_arete * kruskal (t_arete * graphe, int ordre, int s, int n)
 * Fonction qui retourne l’arbre couvrant de poids minimum d’un graphe valué et non orienté depuis un sommet de référence.
 *
 * Paramètres :
 * graphe : tableau d’arêtes du graphe.
 * ordre : nombre de sommets.
 * s : numéro de sommet de référence.
 * n : nombre d’arêtes du graphe.
 */
t_arete * kruskal(t_arete * graphe, int ordre, int s, int n) {
	// Variables locales
	t_arete *arbre = NULL ; // tableau d’arêtes de poids minimum à retourner
	int *connexe ; // tableau dynamique des numéros de sommets connexes de l’arbre
	int indiceA = 0, indiceG = 0 ; // indices de l’arbre et du graphe initialisés à 0
	int x, s1, s2 ; // numéros de sommets intermédiaires
	t_arete u ; // arête reliant 2 sommets x1 et x2

	// Allouer l’arbre de « ordre - 1 » arêtes
    arbre   = (t_arete*) malloc( sizeof(t_arete) * (ordre - 1) );

    // Allouer le tableau connexe de « ordre » sommets
    connexe = (int*) malloc( sizeof(int) * ordre);

    if(connexe == NULL || arbre == NULL) {
		fprintf(stderr, "La fonction malloc() a échoué : espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

    // Initialiser les connexités indicées sur les numéros de sommets
    for (x = 0 ; x < ordre ; x++)
    	connexe[x] = x ;

    // Trier le graphe par ordre croissant des poids de ses « n » arêtes
    triParSelection(graphe, n);

	// tant que les arêtes de l’arbre et du graphe ne sont pas toutes traitées
	while (indiceA < ordre-1 && indiceG < n) {
		u = graphe[indiceG] ; // retourner l’arête u numéro indiceG du graphe
		s1 = connexe[u.sommet1] ; s2 = connexe[u.sommet2] ; // les sommets s1, s2 de l’arête u

		// Tester si les sommets s1 et s2 de l’arête u forment un cycle dans l’arbre
		if (s1 == s2) { // cycle si s1 et s2 connexes

			indiceG++ ; // passer à l’arête suivante du graphe
		}
		else { // pas de cycle

			// insérer l’arête u à la position « indiceA » de l’arbre
			arbre[indiceA] = u ;
			indiceA++ ; indiceG++ ; // passer à l’arête suivante de l’arbre et du graphe

			// Indiquer que les sommets s1 et s2 sont connexes
			for (x  = 0 ; x < ordre ; x++) {
				if (connexe[x]==s1)
					connexe[x] = s2 ;
			} // for
		} // else
	} // while

    // Le graphe est non connexe si le nombre d’arêtes de l’arbre < nombre de sommets-1
    if (indiceA < ordre-1) {
    	fprintf(stderr, "Le graphe n'est pas connexe \n") ;
    	exit(EXIT_FAILURE);
    }

    return arbre ; // retourner l’arbre de poids minimum
} // kruskal()


/* void triParSelection(t_arete * tableauAretes, int nombreAretes)
 * Source : Cours Programmation Avancee et Application / Jean-Guy Mailly
 * Commentaires : Cours Algorithmique et structures de donnees / Gael Mahe
 */
void triParSelection(t_arete * tableauAretes, int nombreAretes) {
	int i;
	for(i = 0 ; i < nombreAretes - 1 ; i++) { // Parcourir le tableau : pour tout i E [0, n − 1]
		int indiceMin = rechercheIndicePlusPetit(tableauAretes, nombreAretes, i); // On cherche le plus petit element du tableau pour j E [i, n]
		if(indiceMin != i) echanger(tableauAretes, i, indiceMin); // On echange tableauAretes[i] et tableauAretes[indiceMin]
	}
}

/* int rechercheIndicePlusPetit(t_arete * tableauAretes, int nombreAretes, int i)
 * Source : Cours Programmation Avancee et Application / Jean-Guy Mailly
 * Commentaires : Cours Algorithmique et structures de donnees / Gael Mahe
 *
 * L’algorithme de tri par selection va utiliser la fonction rechercheIndicePlusPetit().
 * On cherche le plus petit element du tableau pour j E [i, n].
 */
int rechercheIndicePlusPetit(t_arete * tableauAretes, int nombreAretes, int i) {
	int indiceMin = i;
	int j;
	for(j = i + 1 ; j < nombreAretes ; j++) {
		if(tableauAretes[j].poids < tableauAretes[indiceMin].poids) indiceMin = j;
	}

	return indiceMin;
}

/* void echanger(t_arete * tableauAretes, int i, int j)
 * Source : Cours Programmation Avancee et Application / Jean-Guy Mailly
 * Commentaires : Cours Algorithmique et structures de donnees / Gael Mahe
 *
 * L’algorithme de tri par selection va utiliser la fonction echanger().
 * Prend en entree un tableau et deux indices.
 * Echange les elements du tableau correspondant a ces deux indices.
 */
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
		scanf("%d", &tailleDuGraphe); // saisir le nombre d’arêtes du graphe.
	}while(tailleDuGraphe < 1); // tant que l'utilisateur n'entre pas un nombre correct (> 0)

	do {
		printf("Ordre du graphe (n > 0) = ? \n");
		fflush(stdout);
		scanf("%d", &ordreDuGraphe); //saisir le nombre de sommets du graphe.
	}while(ordreDuGraphe < 1); // tant que l'utilisateur n'entre pas un nombre correct (> 0)

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
} // main()


/* *********************************************************************** */
void printAretes(t_arete* tableauAretes, int nombreAretes){
	int i;
	for(i = 0 ; i < nombreAretes ; i++)
		printf("Arête %d : sommet1 : %d , sommet2 : %d , poids : %.1f \n", i, tableauAretes[i].sommet1, tableauAretes[i].sommet2, tableauAretes[i].poids );
	printf("\n");
} // printAretes()

/* t_arete* chargeGraphe(int tailleDuGraphe)
 * La fonction prend en paramètre le nombre d’arêtes du graphe (Taille du graphe)
 * et le nombre de sommets du graphe (Ordre du graphe).
 */
t_arete* chargeGraphe(int tailleDuGraphe, int ordreDuGraphe){
	int i;
	t_arete* tableauAretes = NULL;

	// Allocation dynamique de mémoire pour le tableau d’arêtes.
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
} // chargeGraphe()
