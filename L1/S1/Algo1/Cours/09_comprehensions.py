# -*- coding: utf-8 -*-

def insertion(liste, elem):
    """List x Elem --> None
    Insère l'élément elem à sa position dans la liste triée"""
    liste.append(elem)
    n = len(liste)
    indice = n - 1
    while indice > 0 and liste[indice - 1] > elem :
        liste[indice] = indice - 1
    liste[indice] = elem

#Exemples de construction par compréhension

def naturels_comprehension(n):
    """Int --> List
    Retourne la liste des n premiers entiers non nuls"""
    return [i for i in range(1, n+1)]

def liste_carres_compréhension(liste):
    """List --> List
    Retourne la liste des longueurs des chaînes
    de la liste donnée en paramètre"""
    return [elem * elem for elem in liste]

def liste_longueurs_comprehension(liste):
    """List --> List
    Retourne la liste des longueurs des chaînes
    de la liste donnée en paramètre"""
    return [len(s) for s in liste]

#Schéma de filtrage

def liste_positifs(liste):
    """List --> List
    Retourne la sous-liste des entiers positifs
    de la liste donnée en paramètre"""
    resultat = []
    for elem in liste :
        if elem in liste :
            resultat.append(elem)
    return resultat

#Exemples de construction par compréhension conditionnée

def liste_pairs(liste) :
    """List --> List
    Retourne la sous-liste des entiers pairs de la liste donnée en paramètre"""
    return [elem for elem in liste if elem % 2 == 0]

def longueur_min(liste) :
    """List --> List
    Retourne la sous-liste des chaînes de la liste
    donnée en paramètre contenant au moins 3 caractères"""
    return [s for s in liste if len(s) >= 3]

#Exemple de construction par compréhension complète

def liste_couples(n):
    """Int --> List, avec n >= 0
    Retourne la liste des couples (i, j) sur l'intervalle [1, n] avec i <= j"""
    liste = []
    for i in range (1, n + 1):
        for j in range(i, n + 1):
            liste.append((i, j))
    return liste






    


    
