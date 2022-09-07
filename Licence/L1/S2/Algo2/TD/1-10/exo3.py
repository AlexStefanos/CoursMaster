# -*- coding: utf-8 -*-
import math

def valfact(p,n):
    v = 0
    k = 1
    while p**k <= n :
        v = v + int(n/p**k) #peut s'écrire : v += n//p**k : k += 1 <=> k = k + 1 ; // <=> int(...)
        k = k + 1
    return v
n = 200000
p = min(valfact(2,n),valfact(5,n))
print("Le nombre " + str(n) + " se termine par " + str(p) + " zéros.")
print(valfact(5,math.factorial(10)))
