import numpy as np
import matplotlib.pyplot as plt

def optimisation(A, v, n):
    w = np.zeros((2, 2))

    for k in range(n-1):
        w = np.dot(A, v)
        v = np.dot(w, np.linalg.matrix_power(w, -1))
        lamb = np.dot(v, np.dot(A, v))
    return(v, lamb)

A = np.array([[3, 1], [1, 1]])
v = np.array([[4, 2], [2, 2]])
print(optimisation(A, v, 10))
print(np.linalg.eig(A))