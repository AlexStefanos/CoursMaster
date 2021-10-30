#include <stdio.h>
#include <stdlib.h>
#define FILE
#include "file.h"

t_file_head *createFile() {
    t_file_head *ptrFile;

    ptrFile = malloc(sizeof(t_file_head));
    if(!ptrFile) {
        fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
        exit(EXIT_FAILURE);
    }
    ptrFile->first = NULL;
    return(ptrFile);
}

t_file_body *newElement(int labelElement) {
    t_file_body *ptrElement;

    ptrElement = malloc(sizeof(t_file_body));
    if(!ptrElement) {
        fprintf(stderr, "Echec lors de l'allocation de l'espace mémoire (malloc)\n");
        exit(EXIT_FAILURE);
    }
    ptrElement->prev = NULL;
    ptrElement->next = NULL;
    ptrElement->label = labelElement;
}

void thread(t_file_body *file_body, t_file_head *file_head) {
    if(isEmpty(file_head))
        file_head->first = file_body;
    else {
        t_file_body *currentElement = file_head->first;
        t_file_body *lastPosition;

        while(currentElement) {
            lastPosition->next = file_body;
            file_body->prev = lastPosition;
        }
    }
}

int scroll(t_file_head *file_head) {
    int tmp = -1;
    if(isEmpty(file_head)) {
        tmp = (file_head->first)->label;
        t_file_body *lastFirst = file_head->first;
        file_head->first = (file_head->first)->next;
        free(lastFirst);
    }
    return(tmp);
}

int isEmpty(t_file_head *file_head) {
    if((file_head->first) == NULL)
        return(1);
    else
        return(0);
}
