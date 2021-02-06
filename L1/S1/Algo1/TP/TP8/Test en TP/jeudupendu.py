#-*- coding: utf-8 -*-

def recup_lettres():
    """
    """
    carac = input("Entrez un caractère : ")
    longueur=len(carac)
    print(longueur)
    return carac

recup_lettres()

import random
from donnees import liste_mots

def mot_random():
    """
    """
    x=random.choice(liste_mots)
    mot=x
    longueur2=len(mot)
    print(longueur2)
    mot2=len(mot)*"*"
    print(mot2)
    for i in range (len(mot)):
        if recup_lettres() in mot:
            a=i
    return(mot)

print(mot_random())
