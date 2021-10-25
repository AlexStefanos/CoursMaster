/*
 * file.h
 * Auteur : Leonard Namolaru
 * Ajout dexplications à partir du cours Programmation Impérative / F. Cloppet 
 */

/* Pour éviter les inclusions multiples   
 * Placer le contenu du fichier .h à l’intérieur d’une inclusion conditionnelle
 */

#ifndef FILE_HEADER //if not def
//Permet de tester si un symbole n'a pas déja été définie par un #define

/* l'expression FILE_HEADER  vaut
 * 0 si le fichier n'a pas été inclus
 * 1 s'il a déjà été inclus
 */
 
	#define FILE_HEADER
		
	typedef struct t_file_element{
		// Chaque fois que nous passons à l'élément suivant, nous approchons de la fin de la file.
		struct t_file_element* precedent; // Pointeur vers lelement précédent de la file.
		struct t_file_element* suivant; // Pointeur vers lelement suivant de la file.
		int etiquette;
	} t_file_element ;

	typedef struct t_file{
		struct t_file_element* premier; // Pointeur vers le 1er element de la file.
	} t_file ;
	
	
	/* Si nous incluons ce fichier dans le fichier plusCourtChemin.c, 
	 * il est nécessaire d'ajouter le mot extern avant chaque fonction. 
	 * Cependant, lorsque nous incluons ce fichier dans le fichier file.c, 
	 * il n'est pas nécessaire d'ajouter le mot extern.
	*/
	#ifndef FILE //if not def
		#define WHERE_FILE extern
	#else
		#define WHERE_FILE
	#endif
	
	/* Prototypage des fonctions */
	WHERE_FILE t_file* createFile();
	WHERE_FILE t_file_element* NouvElement(int etiquetteElement);
	WHERE_FILE void enfiler(t_file_element* file_element, t_file* file);
	WHERE_FILE int defiler(t_file* file);
	WHERE_FILE int est_vide(t_file* file);	
	
#endif