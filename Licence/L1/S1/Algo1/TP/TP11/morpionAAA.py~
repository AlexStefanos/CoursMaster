import os

grille = [[" " for i in range(3)] for i in range(3)]

def afficher_grille(grille):
    print ("    0   1   2")
    print ("0","|",grille[0][0],"|",grille[0][1],"|",grille[0][2],"|")
    print ("  -------------")
    print ("1","|",grille[1][0],"|",grille[1][1],"|",grille[1][2],"|")
    print ("  -------------")
    print ("2","|",grille[2][0],"|",grille[2][1],"|",grille[2][2],"|")
    

def cases_vi(grille, li, co) :
    liste = [0, 1, 2]
    if li in liste and co in liste :
        if grille[li][co] == " ":
            return True
        else:
            return False
    else :
        return False

def gest_erreur(a, b):
    liste = [0, 1, 2]
    if a  in liste and b  in liste:
        return True
    return False

def match_nul(grille) :
    a = True
    for i in range (0, 3) :
        for j in range (0, 3) :
            if grille[i][j] == " ":
                return False
    return a
        

def victoire_o(grille):
    if grille[0][0]== "O" and grille[0][1]== "O" and grille[0][2] == "O":
        return True
    elif grille[1][0]== "O" and grille[1][1]== "O" and grille[1][2] == "O":
        return True
    elif grille[2][0]== "O" and grille[2][1]== "O" and grille[2][2] == "O":
        return True
    elif grille[0][0]== "O" and grille[1][0]== "O" and grille[2][0] == "O":
        return True
    elif grille[0][1]== "O" and grille[1][1]== "O" and grille[2][1] == "O":
        return True
    elif grille[0][2]== "O" and grille[1][2]== "O" and grille[2][2] == "O":
        return True
    elif grille[0][0]== "O" and grille[1][1]== "O" and grille[2][2] == "O":
        return True
    elif grille[0][2]== "O" and grille[1][1]== "O" and grille[2][0] == "O":
        return True
    return False

def victoire_x(grille):
    if grille[0][0]== "X" and grille[0][1]== "X" and grille[0][2] == "X":
        return True
    elif grille[1][0]== "X" and grille[1][1]== "X" and grille[1][2] == "X":
        return True
    elif grille[2][0]== "X" and grille[2][1]== "X" and grille[2][2] == "X":
        return True
    elif grille[0][0]== "X" and grille[1][0]== "X" and grille[2][0] == "x":
        return True
    elif grille[0][1]== "X" and grille[1][1]== "X" and grille[2][1] == "X":
        return True
    elif grille[0][2]== "X" and grille[1][2]== "X" and grille[2][2] == "X":
        return True
    elif grille[0][0]== "X" and grille[1][1]== "X" and grille[2][2] == "X":
        return True
    elif grille[0][2]== "X" and grille[1][1]== "X" and grille[2][0] == "X":
        return True
    return False

i = 0
while i != 1000:
    a = afficher_grille(grille)
    print (a)
    if i % 2 == 0:
        print ("jouer 1 : O")
        try :
            li = int(input("Entrez la ligne : "))
            co = int(input("Entrez une colonne : "))
            y = gest_erreur(li, co)
        except :
           # y = gest_erreur(li, co)
            while y == False :
                print ("[",li," ,",co,"] ne sont pas des cordonnées valables.")
                li = int(input("Entrez la ligne : "))
                co = int(input("Entrez une colonne : "))
        z = cases_vi(grille, li, co)
        y = gest_erreur(li, co)
        if y == True and  cases_vi(grille, li, co) == True :
            grille[li][co] = 'O'
        else :
            if y == False :
                while y == False :
                    print ("Veuillez rentrer des cordonnées valables.")
                    li = int(input("Entrez la ligne : "))
                    co = int(input("Entrez une colonne : "))
                    z = cases_vi(grille, li, co)
                    y = gest_erreur(li, co)
                    if z == True and y == True:
                        grille[li][co] = 'O'
            if z == False :
                while z == False :
                    print ("La case est déja occupée, Veuillez rentrer des cordonnées valables.")
                    li = int(input("Entrez la ligne : "))
                    co = int(input("Entrez une colonne : "))
                    z = cases_vi(grille, li, co)
                    y = gest_erreur(li, co)
                    if z == True and y == True :
                        grille[li][co] = 'O'
        
            
    else :
        print ("jouer 2 : X")
        li = int(input("Entrez la ligne : "))
        co = int(input("Entrez une colonne : "))
        z = cases_vi(grille, li, co)
        y = gest_erreur(li, co)
        if y == True and z == True :
            grille[li][co] = 'X'
        else :
            if y == False :
                while y == False :
                    print ("Veuillez rentrer des cordonnées valables.")
                    li = int(input("Entrez la ligne : "))
                    co = int(input("Entrez une colonne : "))
                    z = cases_vi(grille, li, co)
                    y = gest_erreur(li, co)
                    if z == True and y == True:
                        grille[li][co] = 'X'
            if z == False :
                while z == False :
                    print ("La case est déja occupée, Veuillez rentrer des cordonnées correctes.")
                    li = int(input("Entrez la ligne : "))
                    co = int(input("Entrez une colonne : "))
                    z = cases_vi(grille, li, co)
                    y = gest_erreur(li, co)
                    if z == True and y == True :
                        grille[li][co] = 'X'
    i = i + 1
    os.system("clear")
    o = victoire_o(grille)
    x = victoire_x(grille)
    h = match_nul(grille)
    if h == True :
        print ("Aucun gagnant, match Nul.")
        break
    if o == True:
        print ("Partie remporter par le joueur 1. Bravo !")
        break
    if x == True:
        print ("Partie remporter par le joueur 2. Bravo !")
        break
    
a = afficher_grille(grille)
print (a)

