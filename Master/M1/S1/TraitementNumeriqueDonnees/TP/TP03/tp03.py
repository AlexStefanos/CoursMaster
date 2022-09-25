import numpy as np
import matplotlib.pyplot as plt

def genSig(n, k):
    N = np.arange(0, n)
    for i in range(n):
        if(N[i] == 0):
            N[i] = 1
        else:
            N[i] = 0
    N[k] = 1
    plt.stem(N)
    plt.title("Question 1")
    plt.show()
    return(N)

N = genSig(20, 5)
plt.clf()

def genSig2(n, k, type):
    N = np.arange(0, n)
    if(type == 'I'):
        for i in range(n):
            if(N[i] == 0):
                N[i] = 1
            else:
                N[i] = 0
        plt.title("Question 2 : Impulsion")
    elif(type == 'S'):
        for i in range(n):
            if(N[i] >= 0):
                N[i] = 1
            else:
                N[i] = 0
        plt.title("Question 2 : Saut Unitaire")
    else:
        print("Erreur")
    plt.stem(N)
    plt.show()
    return(N)

N = genSig2(20, 5, 'S')
plt.clf()
N = genSig2(20, 5, 'I')
plt.clf()

def genSig3(n, k):
    N = np.arange(0, n)
    for i in range(n):
        if(N[i] >= 0):
            N[i] = i
        else:
            N[i] = 0
    for i in range(k):
        N[i] = 0
    plt.stem(N)
    plt.title("Question 3")
    plt.show()
    return(N)

N = genSig3(20, 5)
plt.clf()

def genSig4(n, k, I, S):
    N = np.arange(0, n)
    for i in range(n):
        if(N[i] == 0):
            N[i] = 1
        else:
            N[i] = 0
    N[k] = 1
    O = np.arange(0, n)
    if((I != 0) and (S == 0)):
        for i in range(n):
            if(O[i] == 0):
                O[i] = 1
            else:
                O[i] = 0
    elif((S != 0) and (I == 0)):
        for i in range(n):
            if(O[i] >= 0):
                O[i] = 1
            else:
                O[i] = 0
    else:
        print("Erreur")
    P = np.arange(0, n)
    for i in range(n):
        if(P[i] >= 0):
            P[i] = i
        else:
            P[i] = 0
    for i in range(k):
        P[i] = 0
    plt.stem(N + O + P)
    plt.title("Question 4")
    plt.show()
    return(N)

N = genSig4(20, 5, 0, 1)
plt.clf()

def genSin(n, f, fs):
    N = np.arange(n)
    t = np.linspace(0,(N-1)/fs, n)
    # S = t*np.exp(np.sin(2*np.pi()*N[0]*t))
    plt.stem(N)
    # np.plot(t,S)
    plt.title("Question 5")
    plt.show()
    return(N)

N = genSin(20, 50, 512)
plt.clf()

def expSig(n, a):
    N = np.arange(0, n)
    for i in range(n):
        N[i] = a**n
        if(np.isreal(N[i]) == False):
            N[i] = (a**n)(np.cos(n) + i*np.sin(n))
    plt.stem(N)
    plt.title("Question 6")
    plt.show()
    return(N)

N = expSig(20, -0.95)
plt.clf()