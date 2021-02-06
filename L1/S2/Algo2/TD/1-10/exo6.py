# -*- coding: utf-8 -*-
M = [mot[:-1] for mot in open('motss.txt')]
sorted(M)
D = dict()
for mot in M:
    T = tuple(sorted(mot))
    if T in D :
        D[T] = D[T] + 1
    else :
        D[T] = 1
maxi=(max(D[T] for T in D))
print(maxi)
    
