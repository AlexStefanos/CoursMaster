#include "loadGraph.h"

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