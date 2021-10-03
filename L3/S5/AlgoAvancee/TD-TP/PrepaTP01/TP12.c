#include <stdio.h>
#include <stdlib.h>

typedef struct node {
    struct node *father;
    char label;
    int nb;
    struct node *sonL, *sonR;
}node;

//changer tous les switch en while + suite de if => voir s'il y a moins d'errerus de printf

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

void pathwayPrefix(node *nd) {
    if (nd != NULL) {
        printf("%c(%d)\t\n", nd->label, nd->nb);
        pathwayPrefix(nd->sonL);
        pathwayPrefix(nd->sonR);
    }
}

void deleteTree(node *nd) {
    if(nd != NULL) {
        deleteTree(nd->sonL);
        deleteTree(nd->sonR);
        if(nd->father != NULL) {
            if(nd->father != NULL)
                nd->father->sonL = NULL;
            else
                nd->father->sonR = NULL;
        }
        free(nd);
        printf("L'arbre a été supprimé avec succès\n");
    }
}

void displayMainMenu() {
    fflush(stdin);
    printf("\n----------Menu principal----------\n");
    printf("Insérer des noeuds (veuillez appuyer sur I):\n");
    printf("Supprimer un noeud (veuillez appuyer sur S):\n");
    printf("Afficher l'arbre (veuillez appuyer sur A):\n");
    printf("Quitter (veuillez appuyer sur Q):\n");
}

int main() {
    node *tree = NULL;
    node *nd = NULL;
    char c, select, select2;
    int pos, exit = 0;
    printf("Indiquer l'étiquette de la racine : \n");
    fflush(stdin);
    scanf("%c", &c);
    tree = newNode(c);
    while((select != 'Q') && (select != 'q')) {
        displayMainMenu();
        printf("Choix : \n");
        scanf("%c", &select);
        if((select == 'i') || (select == 'I')) {
                printf("Insérer après quel noeud voulez-vous insérer un nouveau noeud (sachant que l'on commence à 1) :\n");
                scanf("%d", &pos);
                fflush(stdin);
                printf("Indiquer l'étiquette du nouveau noeud : \n");
                scanf("%c", &c);
                fflush(stdin);
                nd = newNode(c);
                while(exit != 1) {
                    printf("Insérer en fils gauche (veuillez appuyer sur G) :\n");
                    printf("Insérer en fils droit (veuillez appuyer sur D) :\n");
                    fflush(stdin);
                    scanf("%c", &select2);
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
                nd = searchNode(tree, pos);
                if(nd != tree)
                    deleteTree(nd);
                else //pas encore compris
                    tree = NULL;
                printf("\n");
            }
            else if((select == 'A') || (select == 'a')) {
                printf("----------Affichage de l'arbre----------\n");
                pathwayPrefix(tree);
                printf("\n");
            }
            else if((select == 'Q') || (select == 'q'))
                printf("C'est bon c'est fini\n");
            else {
                printf("Vous n'avez pas sélectionné un des caractères acceptés dans ce menu. Veuillez réessayer\n");
            }
    }
    free(tree);
    free(nd);
    return(EXIT_SUCCESS);
}
