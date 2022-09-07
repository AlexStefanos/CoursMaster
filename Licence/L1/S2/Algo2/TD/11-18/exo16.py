# -*- coding: utf-8 -*-

import math as m
import math

def suite1(u0,n):
    u = u0
    for i in range (n) :
        if u > 0.5 :
            u = m.sin(u)
        else :
            u = m.cos(u)
    return u

print("Le résultat de la suite1 est :",suite1(10,5))

def suite2(u0,n0):
    if n==0:
        return u0
    u = suite2(u0,n-1)
    if u > 0.5 :
        return math.sin(u)
    return math.cos(u)
print ("Le résultat de la suite2 est:",suite2(10,5))

def suite3(u0,n):
    u = [u0]
    for k in range (n) :
        if u[-1]>0.5:
            u.append(sin([u-1]))
        else :
            u.append(cos([u-1]))
    return u

def suite4(u0,n):
    if n==0 :
        return [u0]
    u = suite4(u0,n-1)
    if u[-1]>0,5:
        u.append(sin([u-1]))
    else :
        u.append(cos([u-1]))
    return u
