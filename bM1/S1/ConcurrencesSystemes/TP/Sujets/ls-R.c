/* 
   ls recursif

*/
#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>

void scanDir  (char * dir ) {

  DIR * nom_directorie ;
  struct dirent * fichier ;
  if ( nom_directorie = opendir(dir) ){
    fichier = readdir(nom_directorie) ;
    while (fichier != NULL) {
      /* construction du nom complet du fichier */
      char fic[PATH_MAX];
      struct stat etat ;
      sprintf(fic,"%s/%s",dir,fichier->d_name);
      
      printf("%s\n",fic);
      if ( stat(fic,&etat) != -1 ) {
	if ((etat.st_mode & S_IFMT) == S_IFDIR)
	  if (strcmp(fichier->d_name,".") && strcmp(fichier->d_name,"..")) {
	    scanDir(fic);
	    
	  }
      }
      
      fichier = readdir(nom_directorie);
    }
    closedir(nom_directorie);
  }
}

int main ( int argc , char **argv ) {
  if ( argc < 2 ) {
    printf("usage :  %s rep \n",argv[0]);
    exit(1);
  }
  scanDir(argv[1]);
}


