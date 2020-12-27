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
	Nom             Rue;
	unsigned int 	code;
	Nom	        Localite;
}Adresse;

typedef	enum
{
	feminin,
	masculin
}Sexe;

typedef	enum
{
	libere,
	exempte,
	reforme,
	incorporable,
}Sit_Milit;

typedef	union{
	Sit_Milit	sitMil;
	Nom	        nomJF;
}Complement;

typedef	struct	personne{
	Nom 	        nom;
	Nom		prenom;
	Adresse	        ad;
	Sexe	        sex;
	Complement	comp;
}Employe;

typedef	Employe	societe[MAXEMPLOYE];

void    SaisieFiche(Employe *emp)
{
    char buffer;

    printf("Saisissez le nom de l'employé(e) : ");
    scanf("%s", emp->nom);
    scanf("%c", &buffer);
    printf("Saisissez le prénom de l'employé(e) : ");
    scanf("%s", emp->prenom);
    scanf("%c", &buffer);
    printf("Saisissez le numéro de l'employé(e) : ");
    scanf("%u", &emp->ad.num);
    scanf("%c", &buffer);
    printf("Saisissez le Sexe de l'employé(e) : (Veuillez saisir feminin ou masculin)");
    while (scanf("%s", &emp->sex) != feminin)
        scanf("%s", &emp->sex);
}

int	main()
{
    Employe *emp;
    SaisieFiche(emp);
}
