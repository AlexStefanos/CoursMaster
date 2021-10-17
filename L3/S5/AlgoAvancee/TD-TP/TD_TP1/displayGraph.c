#include "displayGraph.h"

void displayGraph(matrix m) {
    printf("Voici la matrice chargée :\n");
    for(int i = 0; i < m.order; i++) {
        for(int j = 0; j < m.order; j++)
            printf("%d\t", m.adjacency[i][j]);
        printf("\n");
    }
}
