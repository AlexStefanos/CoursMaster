# -*- coding: utf-8 -*-
import math
import cmath
def racines_complexes(a,b,c):
    a = float(a)
    b = float(b)
    c = float(c)
    delta = b**2 - 4*a*c
    print("Delta est égal à : ",delta)
    if delta == 0 : #fonctionne
        z0 = float(-b/2*a)
        print("z0 est égal à : ",z0)
        return z0
    elif delta > 0 : #fonctionne
        z1 = float((-b-math.sqrt(delta))/2*a)
        z2 = float((-b+math.sqrt(delta))/2*a)
        print("z1 est égal à : ",z1,"z2 est égal à : ",z2)
        return z1,z2
    elif delta < 0 : #ne fonctionne pas
        z3 = complex((-b-1j*cmath.sqrt(math.fabs(delta)))/2*a)
        z4 = complex((-b+1j*cmath.sqrt(math.fabs(delta)))/2*a)
        print("z3 est égal à : ",z3,"z4 est égal à : ",z4)
        return z3,z4
print(racines_complexes(3,2,1))
