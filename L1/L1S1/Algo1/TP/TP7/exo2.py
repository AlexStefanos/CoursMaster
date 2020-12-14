# -*- coding: utf-8 -*-

def mystere(liste, elem):
    """ List x Elem -> List
    REtoure une lise contenant le nbre d'apparitions de l'élément dans la liste , l'indice de sa 1ere apparition et dernière apparition"""
    resultat=[0, -1, -1]
    for i in range(len(liste)):
        if liste[i] == elem:
            resultat[0] = resultat[0] + 1
            resultat[2] = i
            if resultat[1] == -1 :
                resultat[1]=i

    return resultat
