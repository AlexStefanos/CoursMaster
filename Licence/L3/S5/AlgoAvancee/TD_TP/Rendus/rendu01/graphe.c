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
    char tmp[0];
    int i = 0, j = 0;

    fl = fopen("graphe.txt", "r");
    printf("Veuillez vérifier que l'ordre donné à la 1ère ligne du .txt est bien l'ordre de la matrice donnée\n\n");
    if(fl == NULL) {
        printf("Erreur lors de l'ouverture du fichier");
        return(m);
    }
    c = fgetc(fl);
    tmp[0] = c;
    m.order = atoi(tmp);
    c = fgetc(fl);
    m.adjacency = malloc(sizeof(int) * m.order * m.order);
    for(int k = 0; k < m.order; k++)
        m.adjacency[k] = malloc(sizeof(int) * m.order);
    while((c = fgetc(fl)) != EOF) {
        if(c == '1' || c == '0') {
            if(i < m.order) {
                tmp[0] = c;
                m.adjacency[i][j] = atoi(tmp);
                j++;
                if(j == (m.order)) {
                    i++;
                    j = 0;
                }
            }
        }
    }
    fclose(fl);
    return(m);
}

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
