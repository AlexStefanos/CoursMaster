/*
 * file.c
 * Auteur : Leonard Namolaru
 */
#include <stdio.h> 
#include <stdlib.h>
#define FILE
#include "file.h"

/* t_file* createFile()
 * Alloue l'espace mémoire pour une file, remplit les champs de la structure t_file.
 * Retourne un pointeur sur la file.
 */
t_file* createFile() {
	t_file* ptrFile = NULL;
	ptrFile = (t_file*) malloc( sizeof(t_file) );
	
	if(!ptrFile){
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}
			
	ptrFile -> premier = NULL;
	return ptrFile;	
} // createFile()

/* t_file_element* NouvElement(int etiquetteElement)
 * Alloue l'espace mémoire pour un element, remplit les champs de la structure t_file_element.
 * Retourne un pointeur sur lelement
 */
t_file_element* NouvElement(int etiquetteElement){
	t_file_element* ptrElement = NULL;
	ptrElement = (t_file_element*) malloc( sizeof(t_file_element) );
	
	if(!ptrElement){
		fprintf(stderr, "La fonction malloc() a échoué: espace mémoire insuffisant. \n");
		exit(EXIT_FAILURE);
	}
			
	ptrElement -> precedent = NULL; // Pointeur vers lelement précédent de la file.
	ptrElement -> suivant = NULL; // Pointeur vers lelement suivant de la file.
	ptrElement -> etiquette =  etiquetteElement; 
	
	return ptrElement;
} //NouvElement()

/* void enfiler(t_file_element* file_element, t_file* file)
 * Permet denfiler un element a la fin de la file
 */
void enfiler(t_file_element* file_element, t_file* file){
	if( est_vide(file) )
	{
		// Si la file est vide, l'élément devient le premier élément de la file.
		file -> premier = file_element;
	}
	else // Si la file est PAS vide
	{
		t_file_element* elementActuel = file -> premier;
		t_file_element* dernierePosition;		

		// Le but est d'atteindre la fin de la file 
		// Chaque fois que nous passons à l'élément suivant, nous approchons de la fin de la file.
		while(elementActuel != NULL){
			dernierePosition = elementActuel;
			elementActuel = elementActuel -> suivant; // Pointeur vers lelement suivant de la file.
		} // while
	
		// et puis d'ajouter le nouvel élément à la fin de la file
		dernierePosition -> suivant = file_element;
		file_element -> precedent  = dernierePosition;
	}	
} // enfiler()


/* int defiler(t_file* file)
 * Permet de defiler le 1er element de la file.
 * La fonction renvoie la valeur de l'etiquette de l'élément supprimé ou -1 si la file est vide.
 */
int defiler(t_file* file) {
	int x = -1; // La valeur à retourné si la file est vide
	if( !est_vide(file) ) {
		x = (file -> premier) -> etiquette; // l'etiquette de l'élément.
		t_file_element* ancienPremier = file -> premier;
		file -> premier = (file -> premier) -> suivant; // Chaque fois que nous passons à l'élément suivant, nous approchons de la fin de la file.
		free(ancienPremier); // Libérez l'espace mémoire de l'élément supprimé
	}
	return x; // La fonction renvoie la valeur de l'etiquette de l'élément supprimé
} // defiler()

/* int est_vide(t_file* file)
 * La fonction renvoie 1 si la file est vide, 0 sinon.
 */
int est_vide(t_file* file){
	return ( ( (file -> premier) == NULL ) ? 1 : 0 );
} //est_vide()