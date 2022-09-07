/*
 * TP9_Ex01.c
 *
 *  Created on: 24 nov. 2020
 *      Author: alexandre
 */

#include <stdio.h>
#include <stdlib.h>

#define MAXEMPLOYE 10

typedef	struct	adresse
{
	unsigned int	num;
	char            rue;
	unsigned int 	code;
	char	        localite;
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
	char *	        nomJF;
}Complement;

typedef	struct	personne{
	char *	        nom;
	char *		prenom;
	Adresse	        ad;
	Sexe	        sex;
	Complement	comp;
}Employe;

typedef	Employe	societe[MAXEMPLOYE];

int ft_strlen(char *str)
{
    int i;

    i = 0;
    while (str[i])
        i++;
}

void    *Init()
{
    Employe *emp;
    Adresse *adr;

    emp = malloc(sizeof(Employe));
    emp->nom = "\0";
    emp->prenom = "\0";
    emp->sex = "\0";
    emp->comp = NULL;
    adr = malloc(sizeof(Adresse));
    adr->num = NULL;
    adr->rue = "\0";
    adr->code = NULL;
    adr->localite = "\0";
    emp->ad = adr;
}

void    *SaisieFiche()
{
    Employe *emp = Init();
    char buffer;
    char sexe,compl;

    printf("Saisissez le nom de l'employé(e) : ");
    scanf("%s", emp->nom);
    scanf("%c", &buffer);
    printf("Saisissez le prénom de l'employé(e) : ");
    scanf("%s", emp->prenom);
    scanf("%c", &buffer);
    printf("Saisissez le numéro de l'adresse de l'employé(e) : ");
    scanf("%u", &emp->ad.num);
    scanf("%c", &buffer);
    printf("Saisissez le nom de l'adresse de l'employé(e) : ");
    scanf("%s", &emp->ad.rue);
    scanf("%c", &buffer);
    printf("Saisissez le code de l'adresse de l'employé(e) : ");
    scanf("%u", &emp->ad.code);
    scanf("%c", &buffer);
    printf("Saisissez le nom de la localite de l'employé(e) : ");
    scanf("%s", &emp->ad.localite);
    scanf("%c", &buffer);
    printf("Saisissez le Sexe de l'employé(e) (Veuillez saisir 1 pour feminin ou 0 pour masculin) : ");
    scanf("%c",&sexe);
    do{
        switch(sexe)
        {
            case '0':emp->sex = masculin;
                     printf("Situation Militaire (Veuillez saisir 1 pour libere ou 2 pour exempte ou 3 pour reforme ou 4 pour incorporable) : ");
                     scanf("%c", &compl);
                     scanf("%c", &buffer);
                    switch(compl)
                     {
                         case '0' : emp->comp.sitMil = libere;
                         case '1' : emp->comp.sitMil = exempte;
                         case '2' : emp->comp.sitMil = reforme;
                         case '3' : emp->comp.sitMil = incorporable;
                     }
                     break;
            case '1': emp->sex = feminin;
                    printf("FEMME");
                    break;
        }
    }while(sexe!='0' && sexe!='1');
}

void    AfficheFiche()
{
    Employe *emp = SaisieFiche();
    if(emp->nom != NULL)
        printf("%s", emp->nom);
}
int	main()
{
    AfficheFiche();
}
