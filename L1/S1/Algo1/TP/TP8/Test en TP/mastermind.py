# -*- coding: utf-8 -*-

#1)
"""def nb_bien_places(proposition,solution):
    List -> Int
    Mastermind position par position
    nb=0
    for i in range(len(proposition)):
        if proposition [i] == solution [i]
        nb=nb+1
    return (nb)""" """ Voir corrigé car ne fonctionne pas ligne : if proposition[i]..."""

#2)
def nb_bien_places_couleur(proposition,solution,couleur):
    nb=0
    for i in range (len(proposition)):
        if proposition [i] == couleur and solution [i] == couleur :
            nb=nb+1
    return (nb)

#3)
def compte_occurences(liste):
    liste_nb_occur=[0,0,0,0,0,0,0]
    for i in range (len(liste)):
        k=liste[i]
        liste_nb_occur[k]=liste_nb_occur[k]+1
    return liste_nb_occur

#4)
def nb_couleurs_mal_placees(proposition,solution):
    nb=0
    for i in range (5):
        for j in range (len(solution)):
            if proposition [i] != solution[j]:
                nb=nb+1
    return (nb)

#5)
"""def nb_couleur_mal_placees(proposition,solution):
    mal_placees=0
    l_nb_occur_prop=compte_occurences(proposition)
    l_nb_occur_sol=compte_occurences(solution)
       voir corrigé j'arrive pas à lire"""

#6)
def generation_solution():
    liste=[0,0,0,0,0]
    for i in range (len(liste)):
        liste[i]=random.range
