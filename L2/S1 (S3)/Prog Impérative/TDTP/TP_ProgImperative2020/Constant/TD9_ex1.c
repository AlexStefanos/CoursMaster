#include <stdio.h>
#include <stdlib.h>
#define NB 100
#define NBCAR 30

typedef char NOM[NBCAR];

typedef struct adresse{
	int numero;
	NOM Rue;
	NOM localite;
	NOM code;
}adresse;

typedef enum{
	LIBERE,EXEMPTE,REFORME,INCORPORABLE
}sit_milit;

typedef enum{MASCULIN,FEMININ}sexe;

typedef union{
	sit_milit sitMIL;
	NOM Nomjf;
}complement;

typedef struct employe{
	NOM nom,prenom;
	adresse ad;
	complement compt;
	sexe se;
}employe;

typedef employe Societe[NB];

void saisieFiche(employe *e){
	unsigned char se,sm;
	printf("saisir le nom de l'employé: \n");
	scanf("%s",e->nom);
	printf("veuillez saisir le prénom de l'employe : \n");
	scanf("%s",e->prenom);
	printf("Saisir 'O' pour un homme et 'F' pour une femme: \n");
	do{
		scanf("%c",&se);
		switch(se){
			case 0 : e->se = MASCULIN;
				printf("situation militaire: 'LIBERE'(0),'EXEMPTE'(1),'REFORME'(2),'INCORPORABLE'(3)");
				do{
					scanf("%c",&sm);
					switch(sm){
						case 0:e->compt.sitMIL =LIBERE;
							break;
						case 1: e->compt.sitMIL =EXEMPTE;
							break;
						case 2: e->compt.sitMIL=REFORME;
							break;
						case 3: e->compt.sitMIL=INCORPORABLE;
							break;
						}

				}while(sm!=0 && sm!=1 && sm!=2 && sm!=3);
					break;


			case 1:e->se=FEMININ;
				   break;
		}
	}while(se!=0 && se!=1);
	/*
        printf("Veuillez saisir l'adresse: \n");
	printf("Veuillez saisir le nom de la rue: \n");
	scanf("%s",e->ad.Rue);
	printf("Veuillez saisir le numéro de la rue: \n");
	scanf("%d",e->ad.numero);
	printf("veuillez sasir le code postale: \n");
	scanf("%d",&(e->ad.code));
	printf("veuillez saisir la ville: \n");
        */
}

int main()
{
    employe *e;
    saisieFiche(e);
}
