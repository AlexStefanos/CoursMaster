# -*- coding: utf-8 -*-

import random

def moyenne(somme2):
    moy=0
    moy=somme/100
    return moy
a=0
c=0
i=0
maxi=0
carre=0
somme=0
tabl=[]
for i in range(0,100):
    a=random.randint(0,1000)
    while a in tabl :
        a=random.randint(0,1000)
    if a not in tabl:
        tabl.append(a)
        b=tabl[i]
        if b > maxi:
            maxi=b
        somme=somme+tabl[i]
        i=i+1
a = moyenne(somme)
for e in range (0,100):
    if tabl[e]<a :
        c=c+1
    e=e+1
for f in range (0,100):
    carre=carre+tabl[f]
print("Voici la liste : ", tabl)
print("Le max est : ", maxi)
print("La moyenne est : ", a)
print(c)
print(carre)
