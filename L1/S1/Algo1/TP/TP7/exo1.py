def recherche_sequentielle_liste_non_triee(liste, elem):
    appartient = False
    i=0
    n=len(liste)
    while i < n and not (appartient):
        if liste [i]==elem :
            appartient = True
        i=i+1
    print(appartient)

recherche_sequentielle_liste_non_triee([1,2,3], 2)