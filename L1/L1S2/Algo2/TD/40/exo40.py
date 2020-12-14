#-*- coding utf-8 -*-

#Question 1

def lit_grille(str):
    liste = []
    l1 = [];l2 = [];l3 = [];l4 = [];l5 = [];l6 = [];l7 = [];l8 = [];l9 = []
    for i in range(0,9):
        if str[i] == "." :
            l1.append(None)
        else:
            l1.append(int(str[i]))
    for i in range(9,18):
        if str[i] == "." :
            l2.append(None)
        else :
            l2.append(int(str[i]))
    for i in range(18,27):
        if str[i] == "." :
            l3.append(None)
        else :
            l3.append(int(str[i]))
    for i in range(27,36):
        if str[i] == "." :
            l4.append(None)
        else :
            l4.append(int(str[i]))
    for i in range(36,45):
        if str[i] == "." :
            l5.append(None)
        else :
            l5.append(int(str[i]))
    for i in range(45,54):
        if str[i] == "." :
            l6.append(None)
        else :
            l6.append(int(str[i]))
    for i in range(54,63):
        if str[i] == "." :
            l7.append(None)
        else :
            l7.append(int(str[i]))
    for i in range(63,72):
        if str[i] == "." :
            l8.append(None)
        else :
            l8.append(int(str[i]))
    for i in range(72,81):
        if str[i] == "." :
            l9.append(None)
        else :
            l9.append(int(str[i]))
    liste.append(l1);liste.append(l2);liste.append(l3);liste.append(l4);liste.append(l5);liste.append(l6);liste.append(l7);liste.append(l8);liste.append(l9)
    return(liste)

str = "2.69.4.....5..69878....5..23..6..25.65.....73.91..3..45..1....81634..7.....5.74.6"
G = lit_grille(str)
print("")
print("Voici le résultat pour la question 1 :",G)
print("")

#Question 2

def affiche_grille(liste):
    for i in range(len(liste)):
        if liste[0][i] == None :
            print(".", end=" ")
        else :
            print(liste[0][i], end=" ")
    print()
    for i in range(len(liste)):
        if liste[1][i] == None:
            print(".", end=" ")
        else :
            print(liste[1][i], end=" ")
    print()
    for i in range(len(liste)):
        if liste[2][i] == None:
            print(".", end=" ")
        else :
            print(liste[2][i], end=" ")
    print()
    for i in range(len(liste)):
        if liste[3][i] == None:
            print(".", end=" ")
        else :
            print(liste[3][i], end=" ")
    print()
    for i in range(len(liste)):
        if liste[4][i] == None:
            print(".", end=" ")
        else :
            print(liste[4][i], end=" ")
    print()
    for i in range(len(liste)):
        if liste[5][i] == None:
            print(".", end=" ")
        else :
            print(liste[5][i], end=" ")
    print()
    for i in range(len(liste)):
        if liste[6][i] == None:
            print(".", end=" ")
        else :
            print(liste[6][i], end=" ")
    print()
    for i in range(len(liste)):
        if liste[7][i] == None:
            print(".", end=" ")
        else :
            print(liste[7][i], end=" ")
    print()
    for i in range(len(liste)):
        if liste[8][i] == None:
            print(".", end=" ")
        else :
            print(liste[8][i], end=" ")
    print()
    return None

print("Voici le résultat pour la question 2 :")
affiche_grille(G)
print("")

#Question 3

def possibles(G,i,j):
    #valeurs présentes sur la ligne iEt com
    L = set(G[i][k] for k in range(9))
    #valeurs présentes sur la colonne j
    C = set(G[k][j] for k in range(9))
    #valeurs présentes dans la sous-grille contenant la case (i,j)
    i0,j0 = i-i%3,j-j%3
    S = set(G[i0+k][j0+l] for k in range(3) for l in range (3))
    return {1,2,3,4,5,6,7,8,9} - (L|C|S)

print(" ")
print("Voici le résultat pour la Question 3 :",possibles(G,1,0))
print(" ")

#Question 4

def resoudre(G):
    #renvoie vrai si la grille G admet une solution, faux sinon
    #la grille g est modifiée, et contient la solution si la fonction renvoie vrai
    for i in range(9):
        for j in range(9):
            if G[i][j] is None :
                # on essaie de trouver une solution en remplissant G[i][j]
                for x in possibles(G,i,j):
                    G[i][j] = x
                    return True
                #on n'a pas trouvé de solution en remplissant G[i][j]
                G[i][j] = None
                return False
    return True #car toutes les cases de G sont déjà remplies

print(" ")
print("Voici les résultats pour la Question 4 :",resoudre(G))
affiche_grille(G)
print(" ")
