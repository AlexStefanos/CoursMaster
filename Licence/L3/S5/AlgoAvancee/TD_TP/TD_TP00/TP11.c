#include <stdio.h>
#include <stdlib.h>

typedef struct point {
    int x, y;
}point;

typedef struct cell {
    point pos;
    struct cell * prev, * next;
}cell;

cell *newCell(point p) {
    cell *cel;
    cel = (cell *)malloc(sizeof(cell));
    if (!cel){
        printf("Erreur d'allocation !\n");
	exit(EXIT_FAILURE);
    }
    cel->pos.x = p.x;
    cel->pos.y = p.y;
    cel->prev = NULL;
    cel->next = NULL;
    return cel;
}

void insertCell(int pl, cell *cel, cell *list) {
	int pos = 0;
	while ((pos != pl) && (list->next != NULL)) {
            list = list->next;
	    pos++;
	}
	if (list->next == NULL) {
	    cel->prev = list;
	    list->next = cel;
	}
        else if (pos == pl){
	    list=list->prev;
	    cel->prev = list;
	    cel->next = list->next;
	    list->next = cel;
	    list->next->prev = cel;
	}
}

void deleteCell(int pl, cell *list) {
    int pos = 0;
    while (pos != pl && list->next != NULL) {
        list = list->next;
	pos++;
    }
    if (pos == pl) {
        if (list->next == NULL)
            list->prev->next = NULL;
	else {
	    list->prev->next = list->next;
	    list->next->prev = list->prev;
	}
    }
}

void displayCell(cell *list) {
    cell *currentCell = list->next;
    printf("Les points dans l'ordre inséré :\n");
    do {
        printf("\tx = %d, y = %d\n", currentCell->pos.x, currentCell->pos.y);
	currentCell = currentCell->next;
    }while(currentCell != NULL);
    free(currentCell);
}

void displayMainMenu() {
        printf("-------------------------------------------------Menu principal--------------------------------------------------\n");
        printf("Créer une cellule (veuillez appuyer sur C)\n");
        printf("Supprimer une cellule (veuillez appuyer sur S)\n");
        printf("Afficher la liste (veuillez appuyer sur A)\n");
        printf("Quitter (veuillez appuyer sur Q)\n");
        printf("Votre choix : ");
}


int main() {
    int pos, exit;
    char c, buffer;
    point p;
    point tmp = {tmp.x=-1, tmp.y=-1};
    cell *newCe = NULL;
    cell *list = newCell(tmp);

    exit = 0;
    c = 'z';
    while((c != 'Q') && (c != 'q')) {
	displayMainMenu();
        scanf("%c", &c);
        scanf("%c", &buffer);
	if((c == 'C') || (c == 'c')) {
            exit = 1;
	    printf("Coordonnées de la nouvelle cellule (x y) : \n");
            printf("Exemple : \n\tSi vous voulez que x = 5 et y = 2, veuillez insérer les valeurs ainsi : 5 2\n");
	    scanf("%d %d", &p.x, &p.y);
	    newCe = newCell(p);
            if (newCe == NULL)
		printf("Vous devez d'abord créé une celulle !\n\n");
	    printf("Position de la cellule à insérer (>=1) : ");
	    scanf("%d", &pos);
            scanf("%c", &buffer);
            while(pos < 1) {
                printf("Vous avez indiqué une valeur < 1. Veuillez réessayer\n");
	        scanf("%d", &pos);
                scanf("%c", &buffer);
            }
	    insertCell(pos, newCe, list);
        }
        else if((c == 'S') || (c == 's')) {
	    printf("Position de la cellule à supprimer (>=1) : ");
	    scanf("%d", &pos);
            scanf("%c", &buffer);
            while(pos < 1) {
                printf("Vous avez indiqué une valeur < 1. Veuillez réessayer\n");
	        scanf("%d", &pos);
                scanf("%c", &buffer);
            }
	    deleteCell(pos, list);
        }
        else if((c == 'A') || (c == 'a')) {
            if(exit == 0)
                printf("Veuillez créer des cellules avant de les afficher\n");
            else
                displayCell(list);
        }
        else if((c == 'Q') || (c == 'q'))
        printf("\n-------------------------------------------------THE END--------------------------------------------------\n\n");
        else
            printf("Vous n'avez pas séletionné un des caractères acceptés dans ce menu. Veuillez réessayer\n");
    }
    free(newCe);
    free(list);
    return(EXIT_SUCCESS);
}
