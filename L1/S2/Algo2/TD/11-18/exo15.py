# -*- coding: utf-8 -*-

def f(a,b):
    if b==0:
        return a
    return f(a+1,b-1)

#print(f(23,41))
#print(f(10,-1))

def g(x):
    if x==0 :
        return 1
    return 10*g(x-1)

#print(g(8))
#print(g(7.99))

def h(x):
    assert type(x) is int
    if x==0:
        return "0b"
    return h(x//2)+str(x%2)
#print (h(19))
#print (h(1.3))
