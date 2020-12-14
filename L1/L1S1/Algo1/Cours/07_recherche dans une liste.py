# -*- coding: utf-8 -*-

def recherche_sequentielle_liste_non_triee(liste, elem):
    """List x Elem --> Bool
    Vérifie si l'élément elem appartient à la liste non triée"""
    appartient = False
    i=0
    n=len(liste)
    while i<n and not(appartient):
        if liste[i] == elem :
            appartient = True
        i=i+1
    print (appartient)
    return appartient

recherche_sequentielle_liste_non_triee([1,2,3,4,5,6], 12)

# -*- coding: utf-8 -*-

def verif_liste_triee(liste):
    """List --> Bool
    Vérifie si la liste est triée"""
    trie = True
    i = 0
    n = len(liste)
    while i<(n - 1) and trie :
        if liste[i] > liste[i+1]:
            trie = False
        i=i+1
    return trie

def recherche_sequentielle_liste_triee(liste, elem):
    """List x Elem --> Bool
    Vérifie si l'élément elem appartient à la liste triée"""
    if elem > liste[len(liste) - 1]:
        return False
    else :
        i=0
        while liste[i] < elem :
            i=i+1
        if liste[i] == elem :
            return True
        else :
            return False

recherche_sequentielle_liste_triee([1,2,3,4,5,6,7,8,9,10], 8)

def recherche_dichotomique(liste, elem):
    """List x Elem -> Bool
    Vérifie si l'élément elem appartien à la liste triée"""
    appartient = False
    inf, sup = 0, len(liste) -1

    while inf <= sup and not(appartient) :
        med = (inf + sup)//2
        if liste[med] == elem :
            appartient = True
        elif liste[med] > True :
            sup = med - 1
        else :
            inf = med + 1
    return appartient



