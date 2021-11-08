/*
 * liste.h
 * Auteur : Leonard Namolaru
 * Projet :  TP numéro 11, UE Programmation Impérative, Licence informatique - Université de Paris 
 * Ajout dexplications à partir du cours Programmation Impérative / F. Cloppet 
 */

/* Pour éviter les inclusions multiples   
 * Placer le contenu du fichier .h à l’intérieur d’une inclusion conditionnelle
 */

#ifndef LISTE_HEADER //if not def
//Permet de tester si un symbole n'a pas déja été définie par un #define

/* l'expression LISTE_HEADER  vaut
 * 0 si le fichier n'a pas été inclus
 * 1 s'il a déjà été inclus
 */
 
	#define LISTE_HEADER
		
	typedef struct pointFormePolygonale{
		int pointX;
		int pointY;
	} pointFormePolygonale ;

	typedef struct cellule{
		struct cellule* celPrecedente; // Pointeur vers la cellule précédente de la liste.
		struct cellule* celSuivante; // Pointeur vers la cellule suivante de la liste.
		pointFormePolygonale pointXY; // Les coordonnées X et Y du point.
	} cellule ;
	
	
	/* Si nous incluons ce fichier dans le fichier maListe.c, 
	 * il est nécessaire d'ajouter le mot extern avant chaque fonction. 
	 * Cependant, lorsque nous incluons ce fichier dans le fichier list.c, 
	 * il n'est pas nécessaire d'ajouter le mot extern.
	*/
	#ifndef LISTE //if not def
		#define WHERE_LISTE extern
	#else
		#define WHERE_LISTE
	#endif
	
	/* Prototypage des fonctions */
	WHERE_LISTE cellule* NouvCel(pointFormePolygonale p);
	WHERE_LISTE void InsererCellule(int pl, cellule* cel, cellule* liste);
	WHERE_LISTE void SupprimeCellule(int pl, cellule* liste);
	WHERE_LISTE void Afficher(cellule* liste);	
	
#endif