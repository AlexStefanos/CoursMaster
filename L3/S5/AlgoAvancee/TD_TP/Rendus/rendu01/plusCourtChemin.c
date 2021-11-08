#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "file.h"

int **loadGraph(int n){
    int i, j;
    int **adjacency;
    char *ptrNewLine, token;
    char buffer;

    adjacency = malloc(sizeof(int) * n * n);
    if(adjacency == NULL) {
        fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
	exit(EXIT_FAILURE);
    }
    for(i = 0 ; i < n ; i++) {
	adjacency[i] = malloc(sizeof(int) * n);
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

void displayGraphe(int **adjacency, int order){
    int i, j;
    for(i = 0 ; i < order ; i++) {
        for(j = 0 ; j < order ; j++)
	    printf("%d ", adjacency[i][j]);
	printf("\n");
    }
}

void displayMarks(char *path, int s) {
    printf("------------------------------Afficher tous les sommets marqués-------------------------\n");
    printf("Numéro du sommet de référence : %d\n", s);
    printf("%s\n", path);
}

void shortestPath(int **adjacency, int order, int s, int *l, int *prev) {
    int *marks;
    char *path;
    int x, y, pathIndex;
    t_file_head *file_head;

    path = malloc(sizeof(char) * ((order*2) + 1));
    if(path == NULL) {
        fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
	exit(EXIT_FAILURE);
    }
    marks = malloc(sizeof(int) * order );
    if(marks == NULL) {
	fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
	exit(EXIT_FAILURE);
    }
    for(x = 0 ; x < order ; x++) {
	marks[x] = 0;
	l[x] = 0;
    }
    marks[s] = 1;
    path[pathIndex] = ('0' + s);
    path[++pathIndex] = ',';
    pathIndex++;
    file_head = createFile();
    thread(newElement(s), file_head);
    while(!isEmpty(file_head) ) {
        x = scroll(file_head);
	for(y = 0 ; y < order ; y++) {
	    if(adjacency[x][y] && !marks[y]) {
	        marks[ y ] = 1;
	        path[pathIndex]= ('0' + y);
	        path[++pathIndex]= ',';
	        pathIndex++;
	        thread( newElement(y) , file_head);
	        prev[y] = x;
	        l[y] = l[x] + 1;
	    }
	}
    }
    path[--pathIndex] = '\0';
    displayMarks(path, s);
}

int main(int argc, char **argv) {
    int order;
    int s;
    int *l, *prev;
    char buffer;

    while(order < 1) {
	printf("order du graphe (n > 0) = ? \n");
	scanf("%d", &order);
	scanf("%c", &buffer);
    }
    int **adjacency = loadGraph(order);
    displayGraphe(adjacency, order);

    l = malloc(sizeof(int) * order);
    if(l == NULL) {
	fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
	exit(EXIT_FAILURE);
    }
    prev = malloc(sizeof(int) * order);
    if(prev == NULL) {
	fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
	exit(EXIT_FAILURE);
    }

    while((s < 0) || (s > (order-1))) {
	printf("Sommet de référence (s >= 0) = ? \n");
	scanf("%d", &s);
	scanf("%c", &buffer);
    }
    shortestPath(adjacency, order, s, l, prev);
    return(EXIT_SUCCESS);
}
