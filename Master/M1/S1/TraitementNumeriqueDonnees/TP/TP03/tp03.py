import numpy as np
import matplotlib.pyplot as plt
import cmath

print("Question 1 :")
def genSig(n, k, I, S):
    N = np.arange(n)
    for i in range(n):
        if(N[i] == 0):
            N[i] = 1
        else:
            N[i] = 0
    y = np.arange(0, S)
    plt.stem(N)
    plt.stem(y)
    plt.show()
    return(N)

# N = genSig(20, 5, 20, 5)

def genSin(n, f, fs):
    N = np.arange(n)
    t = n/fs
    x = np.exp(np.sin(N))
    plt.stem(x, N)
    plt.show()
    return(N)

genSin(20, 50, 512)
#t = np.linespace(0, (N-1)/fs, N)
#S = a*sin(2*np.pi()*f0*t)
#np.plot(t,S)
#np.plot(S)