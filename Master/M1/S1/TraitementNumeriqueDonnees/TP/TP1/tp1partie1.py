import numpy as np
import matplotlib.pyplot as plt
from numpy import random
from random import random
from operator import add
import time

print("Partie 1 : Question 1 :")
n = int(input("Entrez un entier :"))
l1 = [random() for i in range(n)]
l2 = [random() for i in range(n)]

start = time.perf_counter()
l3 = map(add, l1, l2)
end = time.perf_counter()
print(end - start)

A1 = np.array(l1)
A2 = np.array(l2)

start = time.perf_counter()
A3 = time.perf_counter()
end = time.perf_counter()
print(end - start)
print()

print("Question 2 :")
x = np.array([0, 1, 2, 3])
v = np.array([1, 3, 2, 4])
print(v)
print(type(v))
fig = plt.figure()
plt.plot(x, v, 'rv--', label = 'v(x)')
plt.legend(loc = 'lower right')
plt.xlabel('x')
plt.ylabel('v')
plt.title('Mon titre')
plt.xlim([-1, 4])
plt.ylim([0, 5])
plt.show()
fig.savefig('toto.png')
print()

print("Question 3 :")
v = np.array([2, 4])
m = np.array([[3, 6], [4, 7]])
print(v.dtype, m.dtype)
#m[0, 0] = "hello"  Erreur
print()

print("Question 4:")
v = np.array([2, 9, 0, 1])
M = np.array([[2, 0], [6, 8]])
print(type(v))
print(type(M))
print(v.shape)
print(M.shape)
print()

print("Question 5 :")
tab = np.arange(0, 10)
print(tab)
tab = np.linspace(0, 9)
print(tab)
tab = np.logspace(0, 9)
print(tab)
print()

print("Question 6 :")
M = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
print(M)
nb = np.random.uniform(0, 1, 1)
print(nb)
loiNormal = np.random.randn(3, 3)
print(loiNormal)
hist = plt.hist(loiNormal, 40)
plt.show()
print()

print("Question 7 :")
v0 = np.zeros(3)
print(v0)
M0 = np.array([np.ones(3), np.ones(3), np.ones(3)])
print(M0)
print()

print("Question 8 :")
M = np.diag([1, 2, 3])
print(M)
M = np.identity(3)
print(M)
print()

print("Question 9:")
M = np.array([[np.inf, np.inf, np.inf], [np.inf, np.inf, np.inf], [np.inf, np.inf, np.inf], [np.inf, np.inf, np.inf], [np.inf, np.inf, np.inf]])
print(M)
print()

print("Question 10 :")
def initfunction(i, j):
    return 100 + 10*i + j
M = np.fromfunction(initfunction, (5, 3))
print(M)
print()

print("Question 11 :")
M = np.ones((7, 5, 3))
print(M)
np.save("file.npy", M)
b = np.load("file.npy")
print(b)
print()

print("Partie 2 : Question 1:")
A = np.zeros((2, 4))
A[1][2] = 1
print(A)
print(A.shape)
print(A.ndim)
B = np.zeros((3, 2))
B[1][1] = 1
print(B)
print(B.shape)
print(B.ndim)
print()

print("Question 2 :")
print(M[2])
print(M[1])
print(M)
M[2] = 22
M[1] = 22
print(M[2])
print(M[1])
print(M)
print()

print("Question 3:")
x = M[0:2:2]
print(x)