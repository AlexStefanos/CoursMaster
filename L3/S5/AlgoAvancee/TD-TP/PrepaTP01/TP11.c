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

cell newCell(coord cod) {
    cell *cl;
    cl = malloc(sizeof(cell));
    if (cl = NULL) {
        printf("Mauvaise gestion de l'allocation mémoire\n");
        return(NULL);
    }
    cl->next = NULL;
    cl->prev = NULL;
    cl->coord=cod;
    return cel;
}

void insert(int pos, cell *cl, cell *list) {
    cell *clSend;
    int tmp = 0;
    clSend = list;

    while(pos != tmp && clSend != NULL) {
        celSend = celSend->next;
        pos++;
    }
    if(celSend != NULL) {
        cl->next = clSend->next;
        cl->prev = clSend;
        clSend->next->prev->cl;
        clSend->next = cl;
    }
    else
        printf("La place mémoire cherchée n'existe pas\n");
}

void deleteCell(int pos, cell *list) {
    int tmp = 0;
    cell *clSend;
    celSend = list;

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
