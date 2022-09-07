/*
 * TP8_Ex01.c
 *
 *  Created on: 17 nov. 2020
 *      Author: alexandre
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct date{
	int jour;
	int mois;
	int annee;
}date;

typedef struct personne{
	char nom[31];
	date d_embauche;
	date d_poste;
}personne;

void	saisie(personne *pers)
{
	char rep;
	char buffer;
	printf("Nom : \n");
	scanf("%s", pers->nom);
	scanf("%c", &buffer);
	printf("Date embauche (jj mm aa) : ");
	scanf("%d %d %d", &pers->d_embauche.jour, &pers->d_embauche.mois, &pers->d_embauche.annee);
	scanf("%c", &buffer);
	do
	{
		printf("\nDate Poste = Date Embauche ? (O/N) : ");
		scanf("%c", &rep);
		scanf("%c", &buffer);
		switch(rep)
		{
			case 'O': case 'o' : pers->d_poste = pers->d_embauche; break;
			case 'N': case 'n': printf("\nDate Poste (jj mm aa) ");
			scanf("%d %d %d", &(pers->d_poste.jour), &(pers->d_poste.mois), &(pers->d_poste.annee));
		}
	}while ((rep != 'O') && (rep != 'o') && (rep != 'N') && (rep != 'n'));
}

void	affichage(personne *p)
{

}

int	main()
{
	personne *p;
	int i,nb;
	personne *entreprise;
	do{
		printf("De combien d'employes est formee votre entreprise ? \n");
		scanf("%d", &nb);
	}while(nb <= 0);
	entreprise = malloc(nb*sizeof(personne));
	if (entreprise == NULL)
	{
		printf("Probleme d'allocation memoire pour entreprise \n");
		exit(-1);
	}
	//saisie des infos relatives aux employes de l'entreprise
	for (i=0, p=entreprise; i<nb; i++, p++)
	{
		printf("\n***Saisie des infos du %d%s employe\n", i+1,(i+1)==1?"er":"eme");
		saisie(p);
	}

	//affichage des infos des employes de l'entrerpise
	/*printf("\n == Affichage des infos des employes de l'entreprise === \n");
	for (i=0, p=entreprise; i<nb; i++, p++)
	{
		affichage(*p);
		printf("\n");
	}*/
	return (0);
}
