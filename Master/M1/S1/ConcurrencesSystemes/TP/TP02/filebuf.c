#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

// Lecture écriture d'un tableau d'entiers à partir d'un fichier
// La tableau possède une taille réelle MAX mais ne peut contenir
// que N entiers (taille effective)
//
// Le premier entier du tableau (indice 0) doit contenir le nombre d'éléments 
// effectifs  du tableau.
// Le premier élément est donc à l'indice 1.
//
 
// fic2tab
// charge un tableau à partir d'un fichier 
// size :  la taille réelle  du tableau (tab[0] non compris)

int max; 
//
int fic2tab(char * pathname,int * tab,int size){
  int cible;
   // lecture  du fichier
   if ( (cible  = open(pathname,O_RDONLY)) < 0){
     fprintf(stderr,"probleme d'ouverture du fichier\n");
     return -1;
   }
 
   if (read(cible,tab,(size+1) * sizeof(int)) !=(size+1) * sizeof(int)) {    
     fprintf(stderr,"probleme de lecture du fichier\n");
     return -1;
   }
   close(cible);
   return 0;
}


// tab2 fic
// ecrit un tableau dans un fichier 
// size :  la taille réelle du tableau (tab[0] non compris)
//

int tab2fic(char * pathname,int * tab,int size){
  int cible;
  // creation du fichier
   if ( (cible  = open(pathname,O_WRONLY|O_CREAT|O_TRUNC,0666)) < 0){
     fprintf(stderr,"probleme d'ouverture du fichier\n");
     return -1;
   }
 
   if (write(cible,tab,(size+1) * sizeof(int)) !=(size+1) * sizeof(int)) {    
     fprintf(stderr,"probleme d'ecriture du fichier\n");
     return -1;
   }
   close(cible);
   return 0;
}




// fonction de depot d'un article dans le fichier
// v objet a deposer



void deposer(char* filename,int *v) {
 
  int tab[max +1];
  if (fic2tab(filename,tab,max)  ) exit(1);
  tab[tab[0] +1] = *v;
  tab[0]++;
  if (tab2fic(filename,tab,max)  ) exit(1);
   
}


//  fonction d'extraction d'un article dans le fichier
//  gestion d'un tampon circulaire
//  v objet recuperer


void extraire(char* filename,int *v) {
   
  int tab[max +1];
  if (fic2tab(filename,tab,max)  ) exit(1);
  *v=tab[1];
  //for (i=1;i < tab[0]  ; i++)
  // tab[i] = tab[i + 1];
  memmove(tab+1,tab+2,(tab[0] -1)*sizeof(int));  
  tab[0]--;
  if (tab2fic(filename,tab,max)  ) exit(1);


}

// fonction d'initialisation le fichier materialisant le tampon
//
//  les emplement des MAX article suivent


void init(char * filename,int MAX){
     max=MAX;
    { 
    int *tab=malloc(MAX* sizeof(int));
    tab[0]=0 ;    
    tab2fic(filename,tab,MAX);
    }
   
}

 

