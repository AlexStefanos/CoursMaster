#-*- coding:utf-8 -*-

def creer_arbre(e,L=[]):
    """
    Retourne un arbre dont la racine a pour étiquette e
    et pour enfants les éléments de la liste L (éventuellement vide)
    signature : étiquette x liste d'arbres -> arbre
    """
    return [e]+L


A1 = creer_arbre('-',[creer_arbre('x'),creer_arbre(1)])
A2 = creer_arbre('*',[creer_arbre('x'),creer_arbre('x')])
A3 = creer_arbre('-',[creer_arbre(A2),creer_arbre(A1)])
A4 = creer_arbre('*',[creer_arbre(3),creer_arbre('x')])
A5 = creer_arbre('+',[creer_arbre(A4),creer_arbre(2)])
A = creer_arbre('/',[creer_arbre(A5),creer_arbre(A3)])

print(A)
