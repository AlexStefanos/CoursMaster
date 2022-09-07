#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"
#include "fcntl.h"

int main(int argc, char const *argv[])
{
	/*
		- Ouvre en R+W
		- Créé le fichier s'il n'existe pas,
		- S'il existe alors la var erno est valuée à EEXIST
		- Ne pas oublier le 0 dans les droits (pour le set user id bit, set group id bit et stiky bit)
	*/
	int r = open("f1", O_RDWR | O_CREAT | O_EXCL, 0755);

	if(r==-1)
	{
		perror("open");	//affiche joliement le message associé à la valeur erno
		fprintf(stderr, "Erreur lors du open() : %d\n", r);
	}

	//Si on re execute le programme et que le fichier existait déjà, il n'y a pas recréation du fichier
	//(vérifiable avec l'inode)

	//La valeur des droits n'est pas toujours respectée à cause du mask (commande umask pour l'afficher)
	//Il permet d'interdire aux programmes de pouvoir créer des fichiers avec n'importe quels accès
	//La valeur effective des droits sera un NAND bit à bit des droits d'accès et du mask

	return 0;
}