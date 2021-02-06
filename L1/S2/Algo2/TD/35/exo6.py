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
A3 = creer_arbre('-',[A2,A1])
A4 = creer_arbre('*',[creer_arbre(3),creer_arbre('x')])
A5 = creer_arbre('+',[A4,creer_arbre(2)])
A = creer_arbre('/',[A5,A3])

def etiquette(A):
    """Retourne l'étiquette de l'arbre A"""
    return A[0]

def enfants(A):
    """Retourne la liste des enfants de la racine de l'arbre A"""
    return A[1:]

def evaluer(A,x):
    """Retourne la valeur de l'expression algérbrique A pour la valeur de x spécifiée"""
    e = etiquette(A)
    E = enfants(A)
    if len(E) == 0 :
        if type(e) is int or type(e) is float :
            return e
        assert e == 'x'
        return x
    #Cas d'un noeud interne (2 enfants)
    assert len(E) == 2
    a = evaluer(E[0],x)
    b = evaluer(E[1],x)
    if e == '+' :
        return a+b
    if e == '-' :
        return a-b
    if e == '*':
        return a*b
    if e == '/':
        return a/b
    return e

def derivee (A) :
    e = etiquette(A)
    E = enfants(A)
    if len(E) == 0:
        if type(e) is int or type(e) is float :
            return 0
        assert e == 'x'
        return 1
    assert len(E) == 2
    a = derivee(E[0])
    b = derivee(E[1])
    c = E[0]
    print("c est",c)
    d = E[1]
    print("d est",d)
    if e == '+' : # Addition
        return (creer_arbre('+', [creer_arbre(a), creer_arbre(b)] ))
    if e == '-' : # Soustraction
        return (creer_arbre('-', [creer_arbre(a), creer_arbre(b)] ))
    if e == '*' :
        return (creer_arbre('+', [creer_arbre('*', [creer_arbre(a),d]) , creer_arbre('*', [creer_arbre(b), c])]))
    if e == '/' :
        return (creer_arbre('/', [creer_arbre('-', [creer_arbre('*', [creer_arbre(a), d]) , creer_arbre('*', [c, creer_arbre(b)])]),
         creer_arbre('*', [d, d])]))
    return e

print(derivee(A))
print(evaluer(derivee(A),0))
