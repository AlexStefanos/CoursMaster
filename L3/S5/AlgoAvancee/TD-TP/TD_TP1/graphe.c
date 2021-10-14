#include <stdio.h>
#include <stdlib.h>

typedef struct matrix{
    int **adjacency;
    int order;
    int s;
}matrix;

void displayGraph(matrix m) {
    printf("Voici la matrice chargée :\n");
    for(int i = 0; i < m.order; i++) {
        for(int j = 0; j < m.order; j++)
            printf("%d\t", m.adjacency[i][j]);
        printf("\n");
    }
}

matrix loadGraph(matrix m) {
    int tmp;
    m.adjacency = malloc(sizeof(int) * m.order * m.order);
    for(int i = 0; i < m.order; i++) {
        m.adjacency[i] = malloc(sizeof(int) * m.order);
        for(int j = 0; j < m.order; j++) {
                printf("Veuillez indiquer une valeur (0 ou 1) pour la case se trouvant sur la ligne n°%d et sur la colonne n°%d : ",(i+1), (j+1));
                scanf("%d", &tmp);
                while(tmp != 0 && tmp != 1) {
                    printf("La valeur donnée n'est pas valide. Veuillez indiquer une valeur égale à 0 ou 1 (de poids 0 ou 1) : ");
                    scanf("%d", &tmp);
                    printf("\n");
                }
                printf("\n");
                m.adjacency[i][j] = tmp;
        }
    }
    printf("Vous avez enfin fini de charger la matrice d'adjacence !\n");
    return(m);
}

matrix loadGraphFromTxt(matrix m) {
    FILE *fl;
    char c;

    fl = fopen("graphe.txt", "r");
    if(fl == NULL) {
        printf("Erreur lors de l'ouverture du fichier");
        return(m);
    }
    while((c = fgetc(fl)) != EOF) {
        printf("%c", c);
    }
    fclose(fl);
    return(m);
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
    matrix m;
    int tmp;

    m.adjacency = NULL;
    m.s = 0;
    printf("De quelle façon voulez-vous charger votre matrice ?\n\t1) En donnant les valeurs 1 par 1 à l'aide d'un scanf ? (tapez 1)\n\t2) En utilisant un fichier texte de poids 0 ou 1 dont la première ligne indiquera l'ordre du graphe (tapez 2)\n");
    scanf("%d",&tmp);
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
        loadGraphFromTxt(m);
    }
    free(m.adjacency);
    return(EXIT_SUCCESS);
}
