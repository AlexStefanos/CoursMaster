# -*- coding:utf-8 -*-

def pgcd(a,b):
    if b==0:
        return a
    else :
        q=a//b
        r=a%b
        return (pgcd(b,r))

def complexite_pgcd(a,b):
    n=0
    while b != 0 :
        n = n+1
        a,b=b,a%b
    return n
