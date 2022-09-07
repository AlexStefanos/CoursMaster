#include <stdio.h>
#include <stdlib.h>

typedef struct node {
    struct node *father;
    char label;
    int nb;
    struct node *sonL, *sonR;
}node;

node *newNode(char c) {
    node *nw = NULL;
    static int nb;
    nw = malloc(sizeof(node));

    if(nw == NULL) {
        printf("Mauvaise gestion d'allocation mémoire\n");
        return(NULL);
    }
    else {
        nw->father = NULL;
        nw->label = c;
        nw->nb = ++nb;
        nw->sonL = NULL;
        nw->sonR = NULL;
    }
    return(nw);
}

node *searchNode(node *nd, int nbNode) {
    node *tmp = NULL;

    if(nd == NULL)
        return(NULL);
    else if(nd->nb == nbNode)
        return(nd);
    else {
        tmp = searchNode(nd->sonL, nbNode);
        if(tmp != NULL)
            return(tmp);
        return(searchNode(nd->sonR, nbNode));
    }
}

void insertSonL(node *ndInsert, node *tmp, int nbNode) {
    node *posInsert = NULL;
    posInsert = searchNode(tmp, nbNode);
    if(posInsert != NULL) {
        ndInsert->father = posInsert;
        if(posInsert->sonL != NULL) {
            ndInsert->sonL = posInsert->sonL;
            posInsert->sonL->father = ndInsert;
        }
        posInsert->sonL = ndInsert;
    }
    else
        printf("Aucun noeud n'a été trouvé à cette place mémoire %d\n", nbNode);
}
void insertSonR(node *ndInsert, node *tmp, int nbNode) {
    node *posInsert = NULL;
    posInsert = searchNode(tmp, nbNode);
    if(posInsert != NULL) {
        ndInsert->father = posInsert;
        if(posInsert->sonR != NULL) {
            ndInsert->sonR = posInsert->sonR;
            posInsert->sonR->father = ndInsert;
        }
        posInsert->sonR = ndInsert;
    }
}

void pathwayPrefixL(node *nd) {
    if(nd != NULL) {
        for(int i = (8 - nd->nb);i > 0; i--)
            printf("\t");
        printf("%c(%d)\n", nd->label, nd->nb);
        if (nd->sonL != NULL)
            pathwayPrefixL(nd->sonL);
    }
}

void pathwayPrefixR(node *nd) {
    if(nd != NULL) {
        printf("\t\t\t\t\t\t\t");
            for(int i = (nd->nb - 1);i > 0; i--)
                printf("\t");
        printf("%c(%d)\n", nd->label, nd->nb);
        if (nd->sonR != NULL)
                pathwayPrefixR(nd->sonR);
    }
}

void pathwayPrefix(node *nd) {
    if(nd != NULL) {
        printf("\t\t\t\t\t\t\t%c(%d)\n", nd->label, nd->nb);
        if (nd->sonL != NULL)
            pathwayPrefixL(nd->sonL);
        if (nd->sonR != NULL)
            pathwayPrefixR(nd->sonR);
    }
}

void pathwayInfix(node *nd) {
    if(nd != NULL) {
        printf("%c", nd->label);
        pathwayPrefix(nd->sonL);
        pathwayPrefix(nd->sonR);
    }
}

void pathwaySuffix(node *nd) {
    if(nd != NULL) {
        pathwaySuffix(nd->sonL);
        pathwaySuffix(nd->sonR);
        printf("%c", nd->label);
    }
}

void deleteTree(node *nd) {
    if(nd != NULL) {
        deleteTree(nd->sonL);
        deleteTree(nd->sonR);
        if(nd->father != NULL) {
            if(nd->father->sonL == nd)
                nd->father->sonL = NULL;
            else if (nd->father->sonR == nd)
                nd->father->sonR = NULL;
        }
        nd = NULL;
        free(nd);
        printf("L'arbre a été supprimé avec succès\n");
    }
}

void displayMainMenu() {
    printf("\n-------------------------------------------------Menu principal--------------------------------------------------\n");
    printf("Insérer des noeuds (veuillez appuyer sur I)\n");
    printf("Supprimer un noeud (veuillez appuyer sur S)\n");
    printf("Afficher l'arbre (veuillez appuyer sur A)\n");
    printf("Quitter (veuillez appuyer sur Q)\n");
    printf("Votre choix : ");
}

int main() {
    node *tree = NULL;
    node *nd = NULL;
    char c, select, select2, buffer;
    int pos, exit = 0;
    printf("Indiquer l'étiquette de la racine : ");
    scanf("%c", &c);
    scanf("%c", &buffer);
    printf("\n");
    tree = newNode(c);
    while((select != 'Q') && (select != 'q')) {
        displayMainMenu();
        scanf("%c", &select);
        scanf("%c", &buffer);
        if((select == 'i') || (select == 'I')) {
                printf("Insérer après quel noeud voulez-vous insérer un nouveau noeud (sachant que l'on commence à 1) : ");
                scanf("%d", &pos);
                scanf("%c", &buffer);
                printf("\n");
                printf("Indiquer l'étiquette du nouveau noeud : ");
                scanf("%c", &c);
                scanf("%c", &buffer);
                printf("\n");
                nd = newNode(c);
                while(exit != 1) {
                    printf("Insérer en fils gauche (veuillez appuyer sur G)\n");
                    printf("Insérer en fils droit (veuillez appuyer sur D)\n");
                    printf("Votre choix : ");
                    scanf("%c", &select2);
                    scanf("%c", &buffer);
                    printf("\n");
                    while(select2 != 'D' && select2 != 'd' && select2 != 'G' && select2 != 'g') {
                            printf("Vous n'avez pas sélectionné un des caractères acceptés dans ce menu. Veuillez réessayer : \n");
                        printf("Insérer en fils gauche (veuillez appuyer sur G)\n");
                        printf("Insérer en fils droit (veuillez appuyer sur D)\n");
                        printf("Votre choix : ");
                        scanf("%c", &select2);
                        scanf("%c", &buffer);
                        printf("\n");
                    }
                    if((select2 == 'G') || (select == 'g')) {
                            insertSonL(nd, tree, pos);
                            exit = 1;
                    }
                    else if((select2 == 'D') || (select2 =='d')) {
                            insertSonR(nd, tree, pos);
                            exit = 1;
                    }
                    else
                            printf("Vous n'avez pas sélectionné un des caractères acceptés dans ce menu. Veuillez réessayer\n");
            }
            exit = 0;
          }
            else if((select == 'S') || (select == 's')) {
                printf("Quel est le numéro de création de l'arbre à supprimer :\n");
                scanf("%d", &pos);
                scanf("%c", &buffer);
                nd = searchNode(tree, pos);
                if(nd != tree) {
                    deleteTree(nd);
                    nd = NULL;
                }
                else
                    tree = NULL;
                printf("\n");
            }
            else if((select == 'A') || (select == 'a')) {
                printf("-----------------------------------------------Affichage de l'arbre-----------------------------------------------\n");
                printf("Parcours préfixe : \n");
                pathwayPrefix(tree);
                printf("\nParcours infixe : \n");
                pathwayInfix(tree);
                printf("\nParcours suffixe : \n");
                pathwaySuffix(tree);
                printf("\n");
            }
            else if((select == 'Q') || (select == 'q'))
    printf("\n-------------------------------------------------THE END--------------------------------------------------\n\n");
            else
                printf("Vous n'avez pas sélectionné un des caractères acceptés dans ce menu. Veuillez réessayer\n");
    }
    free(tree);
    free(nd);
    return(EXIT_SUCCESS);
}
