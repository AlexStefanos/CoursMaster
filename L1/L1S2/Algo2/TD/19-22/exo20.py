#-*- coding:utf-8 -*-

def residu1(n):
    q = n//10
    r = n-q*10
    print("n est égal à ",n)
    print("r est égal à ",r)
    return ("q est égal à",q)

def residu2(n) :
    for i in range (len(str(n))):
        liste = [int(x) for x in str(n)]
    print(liste)
    a = 0
    som = 0
    stop = 0
    while len(liste) != 1 or stop != 1:
        for i in range(len(liste)):
            a = liste[i]
            som = som + a
        n = som
        print(n)
        liste = [int(x) for x in str(n)]
        print(liste)
        stop = 1
    return som

print(residu2(92))
