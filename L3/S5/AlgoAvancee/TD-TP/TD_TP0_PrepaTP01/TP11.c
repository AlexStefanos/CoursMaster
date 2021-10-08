#include <stdio.h>
#include <stdlib.h>

typedef struct coord {
    int x;
    int y;
}coord;

typedef struct cell {
    coord cod;
    struct cell *next, *prev;
}cell;

cell *newCell(coord cod) {
    cell *cl;
    cell *null = NULL;
    cl = malloc(sizeof(cell));
    if (cl = NULL) {
        printf("Mauvaise gestion de l'allocation mémoire\n");
        return(null);
    }
    cl->next = NULL;
    cl->prev = NULL;
    cl->cod=cod;
    return(cl);
}

void insert(int pos, cell *cl, cell *list) {
    cell *clSend;
    int tmp = 0;
    clSend = list;

    while(pos != tmp && clSend != NULL) {
        clSend = clSend->next;
        pos++;
    }
    if(clSend != NULL) {
        cl->next = clSend->next;
        cl->prev = clSend;
        clSend->next->prev = cl;
        clSend->next = cl;
    }
    else
        printf("La place mémoire cherchée n'existe pas\n");
}

void deleteCell(int pos, cell *list) {
    int tmp = 0;
    cell *clSend;
    clSend = list;

    while(pos != tmp && clSend != NULL) {
        clSend = clSend->next;
        tmp++;
    }
    if(clSend == NULL)
        printf("La place mémoire cherchée n'existe pas\n");
    else if(clSend->next != NULL) {
        clSend->next->prev = clSend->prev;
        clSend->prev->next = clSend;
        free(clSend);
    }
    else
        printf("Il n'y a rien à supprimer\n");
}

void displayCell(cell *list) {
    if(list == NULL)
        printf("La cellule donnée est vide\n");
    while(list->next != NULL) {
        printf("%p\n", list->next);
        printf("\n");
        printf("%p\n", list->prev);
        printf("\n");
    }
}

int main() {
    coord cod;
    cod.x = 2;
    cod.y = 4;
    cell *cl = NULL;
    cl = newCell(cod);
}
