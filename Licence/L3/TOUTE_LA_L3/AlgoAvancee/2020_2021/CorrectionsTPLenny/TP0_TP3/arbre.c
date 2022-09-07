/*
 * arbre.c
 * Auteur : Leonard Namolaru
 * Projet :  TP numéro 12, UE Programmation Impérative, Licence informatique - Université de Paris 
 */
#define ARBRE 
#include <stdio.h> // fprintf(), printf()
#include <stdlib.h> // exit(), EXIT_FAILURE, malloc(), free()
#include "arbre.h"

/* noeud* nouvNoeud(char carac)
 * Une fonction qui prend en paramètre un caractère carac,
 * permet d'allouer de l'espace mémoire pour un noeud et d'initialiser les champs du noeud :
 * etiquette avec carac, numeroDeCreation avec le numéro de création du noeud automatiquement incrémenté à chaque appel,
 * et pointeur vers le pere, pointeur vers le fils Gauche, pointeur vers le fils Droit à NULL/
 * return : le noeud créé et initialisé.
 */
noeud* nouvNoeud(char carac){
	/* variable locale à une fonction static
	   la variable (automatiquement initialisée à 0) n’est pas détruite à la fin de lappel de la fonction
	   elle conserve sa valeur entre 2 appels de fonctions
	   Variable rémanente
	   (Source : cours Programmation Impérative / F. Cloppet)
	*/
	static int numero_de_creation = 1; // numéro de création du noeud automatiquement incrémenté à chaque appel
	
	noeud* ptrnouvNoeud = NULL;
	ptrnouvNoeud = (noeud*) malloc( sizeof(noeud) );
	
	if(!ptrnouvNoeud){
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}

	ptrnouvNoeud -> pere = NULL; // Pointeur vers le pere.		
	ptrnouvNoeud -> filsG = NULL; // Pointeur vers le fils Gauche.
	ptrnouvNoeud -> filsD = NULL; // Pointeur vers le fils Droit.
	ptrnouvNoeud -> etiquette =  carac; // "chaque noeud de l'arbre stockera un caractère".
	ptrnouvNoeud -> numeroDeCreation = numero_de_creation; // numéro de création du noeud automatiquement incrémenté à chaque appel
	
	numero_de_creation++; // numéro de création du noeud automatiquement incrémenté à chaque appel
	
	return ptrnouvNoeud; // return : le noeud créé et initialisé
} //nouvNoeud()


/* noeud* rechercheNoeud(noeud* n, int num_noeud)
 * Une fonction qui prend en paramètre un arbre (ou sous-arbre) et le numéro du noeud recherché
 * et qui renvoie le noeud dont le champ contenant le numero de création est égal au numéro de noeud passé en paramètre.
 */
noeud* rechercheNoeud(noeud* n, int num_noeud){
	noeud *tmpNoeud;
	
	if(n == NULL)
		return (NULL);
	if(n->numeroDeCreation == num_noeud)
		return (n);
	
	tmpNoeud = rechercheNoeud(n->filsG, num_noeud);
	if(tmpNoeud != NULL)
		return (tmpNoeud);
		
	return( rechercheNoeud(n->filsD, num_noeud) );
} // rechercheNoeud()

/* void insererFG(noeud* noeud_a_inserer, noeud* racine_arbre, int num_noeud)
 * Une fonction qui prend en paramètres le noeud à insérer, l'arbre (noeud qui correspond à la racine) dans lequel il doit etre insérer
 * et le numéro du noeud sous lequel il doit etre inséré en fils gauche.
 * Cette fonction ne renvoie rien.
 */
void insererFG(noeud* noeud_a_inserer, noeud* racine_arbre, int num_noeud){
	noeud* inserSousNoeud; // noeud sous lequel noeud_a_inserer doit etre inséré en fils gauche.
	
	inserSousNoeud = rechercheNoeud(racine_arbre, num_noeud); // Appel à la fonction rechercheNoeud pour trouver le noeud ou noeud_a_inserer doit etre inséré en fils gauche.
	
	if(inserSousNoeud == NULL){
		fprintf(stderr, "L'emplacement %d n'existe pas dans l'arbre. \n", num_noeud);	
		return;
	}	
	
	// Si le noeud inserSousNoeud possède déja un fils gauche, alors il deviendra le fils gauche de noeud_a_inserer;
	if(inserSousNoeud->filsG != NULL){
		noeud_a_inserer->filsG = inserSousNoeud->filsG; 
		(inserSousNoeud->filsG)->pere = noeud_a_inserer;
	}

	inserSousNoeud->filsG = noeud_a_inserer;
	noeud_a_inserer->pere = inserSousNoeud;
} // insererFG()

/* void insererFD(noeud* noeud_a_inserer, noeud* racine_arbre, int num_noeud)
 * Une fonction qui prend en paramètres le noeud à insérer, l'arbre (noeud qui correspond à la racine) dans lequel il doit etre insérer
 * et le numéro du noeud sous lequel il doit etre inséré en fils droit.
 * Cette fonction ne renvoie rien.
 */
void insererFD(noeud* noeud_a_inserer, noeud* racine_arbre, int num_noeud){
	noeud* inserSousNoeud; // noeud sous lequel noeud_a_inserer doit etre inséré en fils droit.
	
	inserSousNoeud = rechercheNoeud(racine_arbre, num_noeud); // Appel à la fonction rechercheNoeud pour trouver le noeud ou noeud_a_inserer doit etre inséré en fils droit.
	
	if(inserSousNoeud == NULL){
		fprintf(stderr, "L'emplacement %d n'existe pas dans l'arbre. \n", num_noeud);	
		return;
	}	
	
	// Si le noeud inserSousNoeud possède déja un fils droit, alors il deviendra le fils droit de noeud_a_inserer;
	if(inserSousNoeud->filsD != NULL){
		noeud_a_inserer->filsD = inserSousNoeud->filsD; 
		(inserSousNoeud->filsD)->pere = noeud_a_inserer;
	}

	inserSousNoeud->filsD = noeud_a_inserer;
	noeud_a_inserer->pere = inserSousNoeud;
} // insererFD()


/* void parcoursPrefixe(noeud* racine_arbre)
 * Le parcours préfixe d'un arbre revient à partir de la racine de larbre, à visiter le noeud rencontré,
 * et à parcourir la branche gauche du noeud en visitant tous les noeuds rencontrés.
 * Ce parcours continue jusqua un noeud NULL.
 * A ce stade, on retourne à lancetre le plus proche qui a un fils droit et on continue.
 */
void parcoursPrefixe(noeud* racine_arbre) { 
	if(racine_arbre == NULL)
		return;
	
	// Parcours Préfixe - Racine traitée avant l’appel récursif de parcours des sous arbres (Source : cours Programmation Impérative / F. Cloppet)
	printf(" %c ", racine_arbre->etiquette); // "cette fonction affichera le caractère contenu dans chacun des noeuds visités."
	parcoursPrefixe(racine_arbre->filsG);
	parcoursPrefixe(racine_arbre->filsD);	
}