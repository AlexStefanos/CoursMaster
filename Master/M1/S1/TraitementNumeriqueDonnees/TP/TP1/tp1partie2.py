import numpy as np
import matplotlib.pyplot as plt

print("Partie 2 : Question 1 :")
print("Sans Numpy")
A = [[1, 1], [1, 1], [1, 1]]
B = [[2, 0], [3, 1,], [6, 8]]
print(A)
print(B)
C = [[0, 0], [0, 0], [0, 0]]
for i in range(2):
    for j in range(3):
        C[j][i] = A[j][i] + B[j][i]
print(C)
print()
print("Avec Numpy")
An = np.ones((3, 2))
Bn = np.array([[2, 0], [3, 1], [6, 8]])
print(An)
print(Bn)
Cn = np.add(An, Bn)
print(Cn)
print()

print("Question 2 :")
print("Sans Numpy")
for i in range(2):
    for j in range(3):
        A[j][i] = A[j][i] * 3
        B[j][i] = B[j][i] / 4
print(A)
print(B)
print()
print("Avec Numpy")
np.multiply(An, 3)
np.divide(Bn, 4)
print(A)
print(B)
print()

print("Question 3 :")
print("Sans Numpy")
u = [[8, 12, 16], [3, 6, 2]]
v = [[1, 2, 9], [2, 4, 7]]
w = [[5, 3, 1], [6, 2, 4]]
x = [[0, 0, 0], [0, 0, 0]]
for i in range(3):
    for j in range(2):
        u[j][i] = u[j][i] / 3
        v[j][i] = v[j][i] * 2
        w[j][i] = w[j][i] * 3
        x[j][i] = v[j][i] - w[j][i] + u[j][i]
print(x)
print()
print("Avec Numpy")
u = [[8, 12, 16], [3, 6, 2]]
v = [[1, 2, 9], [2, 4, 7]]
w = [[5, 3, 1], [6, 2, 4]]
x = np.multiply(v, 2) - np.multiply(w, 3) + np.divide(u, 3)
print(x)
print()

print("Question 4 et 5 :")
# print("Sans Numpy")
# def multiplicationMatricielle(A, B):
#     global C
#     if(A.shape[1] == B.shape[0]):
#         C = np.zeros((A.shape[0], B.shape[1]))            Erreur avec les paramètres A et B
#         for i in range(A.shape[1]): 
            # for j in range(B.shape[0]):
            #     C[i][j] = A[i][j] * B[i][j]
#         return(C)
#     else:
#         return("Erreur")
# A = [[1, 2], [6, 10], [5, 3]]
# x = [[16], [2, 7]]
# C = multiplicationMatricielle(A, x)
# print()
print("Avec Numpy")
A = [[1, 2], [6, 10], [5, 3]]
x = [[16], [7]]
print(np.dot(A, x))                                        
A = [[11, 12], [13, 14], [15, 16]]
B = [[3, 4, 5], [6, 7, 9]]
print(np.dot(A, B))
print()

print("Question 6 :")
# print("Sans Numpy")
# def puissanceMatricielle(A):
#     global B
#     if(A.shape[1] == B.shape[0]):
#         C = np.zeros((A.shape[0], B.shape[1]))            Erreur avec les paramètres A et B
#         for i in range(A.shape[1]): 
#             for j in range(A.shape[0]):
#                 B[i]|j] = A[i][j] * A[i, j]
#         return(B)
#     else:
#         return("Erreur")
# B = puissanceMatricielle(A)
# print()
A = [[2, 3, 4], [9, 0 , 6], [1, 3, 2]]
print(np.linalg.matrix_power(A, 2))
print()

print("Question 7 :")
print("Sans Numpy")
A = np.array([[1, 0, 99,], [22, 10, 3], [8, 7, 6]])
B = np.array([[2, 3, 6], [3, 4, 9], [8, 7, 5]])
if(A.shape[1] == B.shape[0]):
    C = np.zeros((A.shape[0], B.shape[1]))
    for i in range(3): 
        for j in range(3):
            C[i][j] = A[i][j] * B[i][j]
print(C)
print()
if(B.shape[1] == A.shape[0]):
    C = np.zeros((B.shape[0], A.shape[1]))
    for i in range(3): 
        for j in range(3):
            C[i][j] = B[i][j] * A[i][j]
print(C)
print()
print("Avec Numpy")
C = np.dot(A, B)
print(C)
C = np.dot(B, A)
print(C)
print()

print("Question 8 :")
print("Sans Numpy")
A = np.array([[1, 0, 99,], [22, 10, 3], [8, 7, 6]])
B = np.array([[2, 3, 6], [3, 4, 9], [8, 7, 5]])
C = np.array([[10, 12, 14], [16, 18, 20], [22, 24, 26]])
if(A.shape[1] == B.shape[0]):
    D = np.zeros((A.shape[0], B.shape[1]))
    for i in range(3): 
        for j in range(3):
            D[i][j] = A[i][j] * B[i][j]
if(D.shape[1] == C.shape[0]):
    E = np.zeros((D.shape[0], C.shape[1]))
    for i in range(3): 
        for j in range(3):
            E[i][j] = D[i][j] * C[i][j]
print(E)
print()
if(B.shape[1] == C.shape[0]):
    D = np.zeros((B.shape[0], C.shape[1]))
    for i in range(3): 
        for j in range(3):
            D[i][j] = B[i][j] * C[i][j]
if(A.shape[1] == D.shape[0]):
    E = np.zeros((A.shape[0], D.shape[1]))
    for i in range(3): 
        for j in range(3):
            E[i][j] = A[i][j] * D[i][j]
print(E)
print()
print("Sans Numpy")
D = np.dot(A, B)
E = np.dot(D, C)
print(E)
print()
D = np.dot(B, C)
E = np.dot(A, D)
print(E)
print()

print("Question 9 :")
print("Sans Numpy")
I = np.array([[1, 1], [1, 1]])
A = np.array([[3, 5], [6, 8]])
if(A.shape[1] == I.shape[0]):
    C = np.zeros((A.shape[0], I.shape[1]))
    for i in range(2): 
        for j in range(2):
            C[i][j] = A[i][j] * I[i][j]
print(C)
print()
if(I.shape[1] == A.shape[0]):
    C = np.zeros((I.shape[0], A.shape[1]))
    for i in range(2): 
        for j in range(2):
            C[i][j] = I[i][j] * A[i][j]
print(C)
print()

print("Question 10 :")
print("Sans Numpy")
A = np.array([[1, 2], [3, 4]])
B = np.array([[0, 0], [0, 0]])
for i in range(len(A[0])):
    for j in range(len(A)):
        B[i][j] = A[j][i]
print(A)
print()
print(B)
print()
print("Avec Numpy")
B = np.transpose(A)
print(A)
print()
print(B)
print()

print("Question 11 :")
A = np.array([[1, 2], [3, 4]])
print(np.linalg.det(A))
print()
print(np.linalg.matrix_rank(A))
print()
print(np.linalg.inv(A))
print()

print("Question 12 :")
B = np.array([1, 5])
print(np.linalg.solve(A, B))
print()

print("Question 13 :")
print("Sans Numpy")
A = np.random.randint(0, 25, (10, 10))
B = np.random.randint(0, 25, (10, 10))
C = 0
if(A.shape[1] == B.shape[0]):
    for i in range(10): 
        for j in range(10):
            C += A[i][j] * B[i][j]
print(C)
print("Avec Numpy")
print(np.vdot(A, B))
print()

print("Question 14 :")
A = np.array([[1, 2], [3, 4], [5, 6]])
B = np.array([[5, 6], [7, 8], [9, 10]])
print(np.cross(A, B))

print("Question 15 : ")
print(np.sqrt(np.sum(np.square(A - B))))     
print()
print(np.linalg.norm(A - B))
print()