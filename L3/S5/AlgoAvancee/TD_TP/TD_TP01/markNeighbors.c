#include "markNeighbors.h"

void markNeighbors(int **adjacency, int order, int s) {
    int *marks, x, y, i = 1;

    marks = malloc(sizeof(int) * order * order);
    for(x = 0; x < order; x++)
        marks[x] = 0;
    marks[s] = 1;
    printf("Voici l'ordre de marquage commançant par le sommet s donné :\n");
    printf("Marque n°0 : %d\n", s);
    for(x = 0; x < order; x++) {
        if(marks[x]) {
            for(y = 0; y <  order; y++) {
                if(adjacency[x][y] && !marks[y]) {
                    marks[y] = 1;
                    printf("Marque n°%d : x = %d, y = %d\n", i, x, y);
                    i++;
                }
            }
        }
    }
}