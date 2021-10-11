#include <stdio.h>
#include <stdlib.h>

typedef struct matrix{
    int **adjacency;
    int order;
    int s;
}matrix;

matrix loadGraph(matrix m) {
    int tmp;
    if(m.order != 0) {
        m.adjacency = malloc(sizeof(int) * m.order);
        for(int i = 0; i < m.order; i++) {
            m.adjacency[i] = malloc(sizeof(int) * m.order);
            for(int j = 0; j < m.order; j++) {
                    printf("Veuillez indiquer une valeur pour la case se trouvant sur la %de ligne et sur la %de colonne\n",(i+1), (j+1));
                    scanf("%d", &tmp);
                    printf("\n");
                    m.adjacency[i][j] = tmp;
            }
        }
        printf("Vous avez enfin fini de charger la matrice d'adjacence !\n");
        return(m);
    }
    else {
        printf("Il est nécessaire de donner une valeur à l'ordre de la matrice afin de pouvoir charger cette dernière\n");
        return(m);
    }
}

matrix loadGraphFromTxt(matrix m) {
    if(fopen("graphe.txt", "r") != NULL) {

        if(fclose("graphe.txt") == 0) {

        }
        else {
            printf("Erreur lors de la fermeture du fichier graphe.txt");
            return(m);
        }
    }
    else {
        printf("Il n'y a pas de fichier graphe.txt à lire dans le répertoire courant\n");
        return(m);
    }
}

void markNeighbors(int **adjacency, int order, int s) {
    int *marks, x, y;

    marks = malloc(sizeof(int) * order);
    for(x = 0; x < order; x++)
        marks[x] = 0;
    marks[s] = 1;
    for(x = 0; x < order; x++) {
        if(marks[x]) {
            for(y = 0; y <  order; y++) {
                if(adjacency[x][y] && !marks[y])
                    marks[y] = 1;
            }
        }
    }
}

int main(int argv, char **argc) {
    matrix **m = NULL;
    m.adjacency = NULL;
    m.order = 3;
    m.s = NULL;
    loadGraph(m);
}
