#include <stdio.h>
#include <stdlib.h>
#include "loadGraph.h"
#include "markNeighbors.h"
#include "displayGraph.h"

typedef struct matrix{
    int **adjacency;
    int order;
    int s;
}matrix;

int main(int argv, char **argc) {
    matrix m;
    int tmp;

    m.adjacency = NULL;
    m.s = 0;
    printf("De quelle façon voulez-vous charger votre matrice ?\n\t1) En donnant les valeurs 1 par 1 à l'aide d'un scanf ? (tapez 1)\n\t2) En utilisant un fichier texte de poids 0 ou 1 dont la première ligne indiquera l'ordre du graphe (tapez 2)\n");
    scanf("%d",&tmp);
    printf("\n");
    while(tmp != 1 && tmp != 2) {
        printf("La valeur donnée n'est pas valide. Veuillez donner une valeur égale à 1 ou 2.");
        scanf("%d", &tmp);
    }
    if(tmp == 1) {
        printf("De quel ordre voulez-vous votre matrice ? ");
        scanf("%d", &tmp);
        printf("\n");
        m.order = tmp;
        m = loadGraph(m);
        displayGraph(m);
    }
    else {
        m = loadGraphFromTxt(m);
        displayGraph(m);
    }
    printf("\n");
    markNeighbors(m.adjacency, m.order, m.s);
    free(m.adjacency);
    return(EXIT_SUCCESS);
}
