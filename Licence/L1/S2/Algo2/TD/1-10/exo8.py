# -*- coding: utf-8 -*-

M = [mot[:-1] for mot in open("mots.txt")]
D = dict()
for mot in M :
    E = frozenset(mot)
    D[E] = D.get(E,0) + 1 #ici le 0 est la valeur par défaut à ajouter dans D si le get ne trouve pas E
m = max(D.values())
print("m = ", m)
for E in D : #affichage du nombre seulement
    if D[E] == m :
        print("Clé", E)
        for mot in M : #affichage des 592 mots associés
            if frozenset(mot) == E :
                print(mot)
