#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#define INFINI 1000.0

typedef struct t_edge {
    int top1;
    int top2;
    float weight;
} t_edge;

float **loadGraph(int n);
void displayGraph(float **adjacencyWeight, int order);
void displayTree(t_edge *tree, int nbEdge);

t_edge *prim(float **adjacencyWeight, int order) {
    t_edge *tree;
    int s, x, y, yMin, indexTree = 0;
    int *marks;
    float min;
    int xWeightMinEdge;

    marks = malloc(sizeof(int) * order);
    tree = malloc(sizeof(t_edge) * (order-1));
    if((marks == NULL) || (tree == NULL)) {
        fprintf(stderr, "Echec lors de l'allocation de la mémoire\n");
        exit(EXIT_FAILURE);
    }
    for(x = 0; x < order; x++)
        marks[x] = 0;
    srand(time(NULL));
    s = rand()%order;
    marks[s] = 1;

    while(indexTree < (order-1)) {
        min = INFINI;
        for(x = 0; x < order; x++) {
            if(marks[x]) {
                for(y = 0; y < order; y++) {
                    if(adjacencyWeight[x][y] && !marks[y] && (adjacencyWeight[x][y] < min)) {
                        min = adjacencyWeight[x][y];
                        yMin = y;
                        xWeightMinEdge = x;
                    }
                }
            }
        }
        marks[yMin] = 1;
        tree[indexTree].top1 = xWeightMinEdge;
        tree[indexTree].top2 = yMin;
        tree[indexTree].weight = min;
        indexTree++;
    }
    return(tree);
}

void displayGraph2(float **adjacencyWeight, int order) {
    int i, j;
    printf("------------------------------Affichage du Graphe------------------------------");
    for(i = 0; i < order; i++) {
        for(j = 0; j < order; j++)
            printf("%.1f", adjacencyWeight[i][j]);
        printf("\n");
    }
}

void displayTree(t_edge *tree, int nbEdges) {
    int i;
    printf("Voici les arêtes de l'arbre : \n");
    for(i = 0; i < nbEdges; i++)
        printf("Arête %d :\n\tSommet n°1 : %d\n\tSommet n°2 : %d\n\tPoids : %.1f\n", (i+1), (tree[i].top1 + 1), (tree[i].top2 + 1), tree[i].weight);
    printf("\n");
}

int **loadGraph2(int n){
    int i, j;
    float **adjacency;
    char *ptrNewLine, token;
    char buffer;

    adjacency = malloc(sizeof(float) * n * n);
    if(adjacency == NULL) {
        fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
	exit(EXIT_FAILURE);
    }
    for(i = 0 ; i < n ; i++) {
	adjacency[i] = malloc(sizeof(float) * n);
	if(adjacency[i] == NULL) {
	fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
	exit(EXIT_FAILURE);
	}
    }
    for(i = 0 ; i < n ; i++) {
        char *tmpInput = malloc(sizeof(char) * (n*2) + 1);
	if(tmpInput == NULL) {
	    fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
	    exit(EXIT_FAILURE);
	}
	    printf("Ligne %d (%d chiffres séparés que par des virgule) : \n", (i+1), n );
	    scanf("%s", tmpInput);
	    ptrNewLine = strrchr(tmpInput, '\n');
	    if(ptrNewLine != NULL)
                *ptrNewLine = '\0';
            token = strtok(tmpInput, ",");
	    for(j = 0 ; token != NULL ; j++) {
		adjacency[i][j] = atoi(token);
		token = strtok(NULL, ",");
	    }
	    free(tmpInput);
    }
    return(adjacency);
}

int main() {
    int order, s;
    char buffer;

    while(order < 1) {
        printf("Ordre du graphe (n > 0) : ");
        scanf("%d", &order);
        scanf("%c", &buffer);
    }
    float **adjacencyWeight = loadGraph2(order);
    displayGraph2(adjacencyWeight, order);
    t_edge *tree = prim(adjacencyWeight, order);
    int nbEdges = order-1;
    displayTree(tree, nbEdges);
}
