/*
 * TP07_Ex01.c
 *
 * Created on: 28 déc. 2020
 *      Author: alexandre
*/

#include <stdio.h>
#include <stdlib.h>


int ft_moyenne(int nb, int *tab)
{
    int i;
    int total;

    i = 0;
    total = 0;
    while (i != (nb))
    {
        total = total + tab[i];
        i++;
    }
    return (total / nb);
}

int ft_plus(int nb, int *tab)
{
    int i;
    int plus;

    i = 1;
    plus = tab[0];
    while (i != (nb))
    {
        if (tab[i] > plus)
            plus = tab[i];
        i++;
    }
    return (plus);
}

int ft_moins(int nb, int *tab)
{
    int i;
    int moins;

    i = 1;
    moins = tab[0];
    while (i != (nb))
    {
        if (tab[i] < moins)
            moins = tab[i];
        i++;
    }
    return (moins);
}

int main()
{
    int *tab;
    int nb,tmp;
    int moy,plus,moins;
    char buffer;
    int i;
    FILE *fich = fopen("noote.txt", "w");
    if (fich == NULL)
    {
        printf("CA MARCHE PAS\n");
        return (1);
    }
    i = 0;
    printf("Veuillez choisir le nombre de notes : ");
    scanf("%d", &nb);
    scanf("%c", &buffer);
    fprintf(fich,"%d\n", nb);
    tab = calloc(nb, sizeof(int));
    while (i != (nb))
    {
        scanf("%d", &tmp);
        scanf("%c", &buffer);
        tab[i] = tmp;
        fprintf(fich,"%d    ", tab[i]);
        i++;
    }
    moy = ft_moyenne(nb,tab);
    plus = ft_plus(nb,tab);
    moins = ft_moins(nb,tab);
    printf("%d %d %d\n", moy,plus,moins);
    fclose(fich);
    return (0);
}
