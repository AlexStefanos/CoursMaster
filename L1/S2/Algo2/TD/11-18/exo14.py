# -*- coding: utf-8 -*-

from math import sin,pi

def f(x):
    if x==0:
        return 1
    else :
        return sin(x)/x

def trapeze (f,a,b,n):
    return (b-a)/(2*n)*(f(a)+f(b)+(2*sum(f(a+k*(b-a)/n) for k in range (1,n))))

n=1
for i in range(8):
    print(trapeze(f,0,pi,n))
    n = n*10
