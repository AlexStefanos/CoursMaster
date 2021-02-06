# -*- coding: utf-8 -*-

def factorielle(n):
    """Int --> Int
    Fonction retournant la factorielle de n"""
    assert type(n) is int, "Factorielle d'un entier"
    fact = 1
    for i in range(1, n+1) :
        fact = fact*i
    print(fact)


def coefficient_binomial(n, k):
    """Int x Int --> Int, avec k <= n
    Fonction retournant le coefficient binomial de k et n"""
    assert type(n) is int, "n est un entier"
    assert type(k) is int, "k est un entier"
    #Pour k in [0, n], le coefficient binomial est un entier
    coeff = factorielle(n)//(factorielle(k)*factorielle(n-k))
    print(coeff)

def newton(n):
    """Int --> None, n != 0
    (a+b)^n= Somme (k=0 à n) (n!//k! (n-k)!a^{n-k}b^k)."""
    assert type(n) is int, "n est un entier"
    assert not(n == 0), "n différent de 0"
    #Initialisation de la chaine
    chaine = "(a+b)^" + str(n) + " = "
    for k in range (n+1):
        c= coefficient_binomial(n,k)
        p=n-k
        if not(c==1) : #on écrit pas le facteur 1
            chaine = chaine + "a"
        if not (p==0) : #si un facteur = 0, on écrit pas les autres facteurs
            chaine= chaine + "^" + str(p)
            if not (p==1):
                chaine = chaine + "^" + str(p)
            if not(k==0):
                chaine = chaine + "b"
                if not(k==1):
                    chaine = chaine + "^" + str(k)
            if k < n: #On continue avec + sauf pour la dernière occurence
                chaine = chaine + " + "
    print(chaine)
n= int(input("Calculer le binôme de newton pour n = "))
newton(n)
