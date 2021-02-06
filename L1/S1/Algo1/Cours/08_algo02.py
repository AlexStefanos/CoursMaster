# -*- coding: utf-8 -*-

def echange (liste, i, j):
    """List x Int x Int -> None
    Echange les éléments de liste en position i et j"""

    elem = liste[i]
    liste[i] = liste[j]
    liste[j] = elem

def tri_selection(liste) :
    """List -> None
    Trie la liste donnée en paramètre. La liste est directement modifiée par la procédure"""
    n=len(liste)
    for i in range(n-1):
        indice_min= i
        for j in range (i+1, n):
            if liste [j] < liste[indice_min] :
                indice_min = j
            if indice != i:
                echange(liste, i, indice_min)

def tri_insertion(liste):
    """List -> None
    Tri la liste donnée en paramètre"""
    for i in range(1, len(liste)):
        elem = liste[i]
        pos = i
        while pos > 0 and liste[pos - 1] > elem :
            liste[pos] = liste[pos - 1]
            pos = pos - 1
        liste[pos]=elem

def tri_comptage(liste) :
    """List -> List
    Tri la liste donnée en paramètre"""
    ind = []
    result = []
    n = len(liste)
    #initialisation des listes indices et résultat
    for i in range(n) :
        ind.append(0)
        result.append(0)
    for i in range(n - 1): #comptage
        for j in range(i+1, n):
            if liste[j] > liste[i] :
                ind[j] = ind[j] + 1
            else :
                ind[i] = ind[i] + 1

    for j in range(n) : #liste triée résultat
        result[ind[i]] = liste[i]

    return result

def drapeau(liste):
    """List --> None
    Tri la liste, contenant 3 couleurs R, J, B, donnée en paramètre"""
    i = 0
    j = 0
    r = len(liste) - 1
    while i <= r:
        if liste[i] == "J" :
            i = i + 1
        elif liste[i] == "B" :
            echange(liste, j, i)
            i = i + 1
            j = j + 1
        else :
            echange(liste, r, i)
            r = r - 1
            
