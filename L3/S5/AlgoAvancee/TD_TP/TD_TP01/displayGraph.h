#ifndef DISPLAYGRAPH_H
#define DISPLAYGRAPH_H
#include <stdio.h>

typedef struct matrix{
    int **adjacency;
    int order;
    int s;
}matrix;

void displayGraph(matrix m);

#endif