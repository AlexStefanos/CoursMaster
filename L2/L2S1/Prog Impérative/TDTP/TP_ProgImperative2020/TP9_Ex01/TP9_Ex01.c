/*
 * TP9_Ex01.c
 *
 *  Created on: 24 nov. 2020
 *      Author: alexandre
 */

#include <stdio.h>
#include <stdlib.h>

#define MAXEMPLOYE 2
#define MAXCAR 30

typedef	char Nom[MAXCAR];

typedef	struct	adresse
{
	unsigned int	num;
	Nom				Rue;
	unsigned int 	code;
	Nom				Localite;
}Adresse;

typedef	enum
{
	feminin,
	masculin
}Sexe;

typedef	enum{
	libere,
	exempte,
	reforme,
	incorporable,
}Sit_Milit;

typedef	union{
	Sit_Milit	sitMil;
	Nom	nomJF;
}Complement;

typedef	struct	personne{
	Nom 	nom;
	Nom		prenom;
	Adresse	ad;
	Sexe	sex;
	Complement	comp;
}Employe;

typedef	Employe	societe[MAXEMPLOYE];

int	main()
{

}
