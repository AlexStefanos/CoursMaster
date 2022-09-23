import numpy as np
import matplotlib.pyplot as plt
import cmath

print("Question 1 :")
def genSig(n, k):
    N = np.linspace(k, n)
    for i in range(n):
        if(N[i] == 0):
            N[i] = 1
        else:
            N[i] = 0
    plt.stem(N)
    plt.show()
    return(N)

N = genSig(20, 5)
plt.clf()

print("Question 2 :")
def genSig2(n, k, I, S):
    N = np.linspace(k, n)
    if((I != 0) and (S == 0)):
        for i in range(n):
            if(N[i] == 0):
                N[i] = 1
            else:
                N[i] = 0
    elif((S != 0) and ()):
        for i in range(n):
            if(N[i] >= 0):
                N[i] = 1
            else:
                N[i] = 0
    else:
        return(-1)
    plt.stem(N)
    plt.show()
    return(N)

N = genSig2(20, 5, 2, 5)

# def genSin(n, f, fs):
#     N = np.linspace(n)
#     t = n/fs
#     x = np.exp(np.sin(N))
#     plt.stem(x, N)
#     plt.show()
#     return(N)

# genSin(20, 50, 512)
# t = np.linespace(0, (N-1)/fs, N)
# S = a*sin(2*np.pi()*f0*t)
# np.plot(t,S)
# np.plot(S)