#ifndef LOADGRAPH_H
#define LOADGRAPH_H
#include <stdio.h>
#include <stdlib.h>

typedef struct matrix{
    int **adjacency;
    int order;
    int s;
}matrix;

matrix loadGraph(matrix m);

matrix loadGraphFromTxt(matrix m);

#endif