/*
 * arbre.h
 * Auteur : Leonard Namolaru
 * Projet :  TP numéro 12, UE Programmation Impérative, Licence informatique - Université de Paris 
 * Ajout dexplications à partir du cours Programmation Impérative / F. Cloppet 
 */

/* Pour éviter les inclusions multiples   
 * Placer le contenu du fichier .h à l’intérieur d’une inclusion conditionnelle
 */

#ifndef ARBRE_HEADER //if not def
//Permet de tester si un symbole n'a pas déja été définie par un #define

/* l'expression ARBRE_HEADER  vaut
 * 0 si le fichier n'a pas été inclus
 * 1 s'il a déjà été inclus
 */
 
	#define ARBRE_HEADER
	
	typedef struct noeud{
		struct noeud* pere; // Pointeur vers le pere.
		struct noeud* filsG; // Pointeur vers le fils Gauche.
		struct noeud* filsD; // Pointeur vers le fils Droit.
		char etiquette; // "chaque noeud de l'arbre stockera un caractère".
		int numeroDeCreation; 
	} noeud ;
			
	/* Si nous incluons ce fichier dans le fichier monArbre.c, 
	 * il est nécessaire d'ajouter le mot extern avant chaque fonction. 
	 * Cependant, lorsque nous incluons ce fichier dans le fichier arbre.c, 
	 * il n'est pas nécessaire d'ajouter le mot extern.
	*/
	#ifndef ARBRE //if not def
		#define WHERE_ARBRE extern
	#else
		#define WHERE_ARBRE
	#endif
	
	/* Prototypage */
	WHERE_ARBRE noeud* nouvNoeud(char carac);
	WHERE_ARBRE noeud* rechercheNoeud(noeud* n, int num_noeud);
	WHERE_ARBRE void insererFG(noeud* noeud_a_inserer, noeud* racine_arbre, int num_noeud);
	WHERE_ARBRE void insererFD(noeud* noeud_a_inserer, noeud* racine_arbre, int num_noeud);
	WHERE_ARBRE void parcoursPrefixe(noeud* racine_arbre);
	
#endif