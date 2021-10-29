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
    printf("Les points dans l'ordre voulue :\n");
    do {
        printf("\tx = %d, y = %d\n", currentCell->pos.x, currentCell->pos.y);
	currentCell = currentCell->next;
    }while(currentCell != NULL);
    free(currentCell);
}

int main(int argc, char **argv) {
    int pos;
    char chosen;
    point p;
    point tmp = {tmp.x=-1, tmp.y=-1};
    cell *newCe = NULL;
    cell *list = newCell(tmp);
    do {
        chosen = 'a';
        printf("\n-------------------------------------------------Menu principal--------------------------------------------------\n");
        printf("Créer une cellule (veuillez appuyer sur C)\n");
        printf("Insérer une cellule (veuillez appuyer sur I)\n");
        printf("Supprimer une cellule (veuillez appuyer sur S)\n");
        printf("Afficher la liste (veuillez appuyer sur A)\n");
        printf("Quitter (veuillez appuyer sur Q)\n");
        printf("Votre choix : ");
	scanf("%s", &chosen);
	switch (chosen) {
            case 'C' :
	        printf("Coordonnées de la nouvelle cellule (x y) : \n");
                printf("Exemple : \n\tSi vous voulez que x = 5 et y = 2, veuillez insérer les valeurs ainsi : 5 2\n");
	        scanf("%d %d", &p.x, &p.y);
	        newCe = newCell(p);
	        break;
	    case 'I' :
                if (newCe == NULL) {
		    printf("Vous devez d'abord créé une celulle !\n\n");
		    break;
                }
		printf("Position de la cellule à insérer : ");
		scanf("%d", &pos);
		insertCell(pos, newCe, list);
		break;
	    case 'S' :
		printf("Position de la Cell à supprimer : (>=1) ");
		scanf("%d", &pos);
		deleteCell(pos, list);
		break;
	    case 'A':
		displayCell(list);
	        break;
	}
    }while (chosen != 'Q');
    free(newCe);
    free(list);
    return EXIT_SUCCESS;
}
