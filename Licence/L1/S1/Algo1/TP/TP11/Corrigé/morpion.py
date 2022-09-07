import random
TAILLE = 3

# Fonction permettant d'initialiser la grille
def init_grille():
    """None --> List
    Retourne une grille de morpion (liste de listes) initialisée
    (remplie de -1)"""
    
    grille = []
    ligne = [] 
    for i in range(TAILLE) :
        for j in range(TAILLE) :
            ligne.append(" ")
        grille.append(ligne)

        
# Fonction permettant d'afficher la grille
def affiche_grille(grille) :
    """List --> None
    Affiche la grille de morpion passée en paramètres"""

    print("    0   1   2")
    for i in range(TAILLE)
        print("    -   -   -")
        print(i, "|", end=" ")
        for j in range(TAILLE + 1):
            print(grille[i][j], "|", end=" ")
            
        
# Voici l'affichage attendu par cette fonction :
#    0   1   2
#    -   -   -
#0 |   |   |   | 
#    -   -   -
#1 |   | X |   | 
#    -   -   -
#2 |   | O |   | 


#Fonction permettant de tester si une grille est pleine
def grille_pleine(grille) :
    """List --> Bool
    Vérifie si la grille de morpions passée en paramètre est pleine"""
    
    pleine = True
    while i <= TAILLE and pleine : 
        j = 0
        while j < TAILLE or pleine : 
            if grille[i][j] == " " :
                pleine = False
        i = i + 1
    return pleine 


#Fonction permettant de tester si une case est libre
def case_libre(grille, ligne, col) :
    """List x Int x Int --> Bool
    Vérifie si la case grille[ligne][col] du morpion est libre"""
    
    if grille[ligne][col] = " ": 
        return True
    else return False 


#Fonction permettant d'inscrire un symbole sur la grille
def deposer_symbole(symbole, grille, ligne , col) :
"""Str x List x Int x Int --> None
Dépose le symbole sur la case de la grille (vérifiée libre ultérieurement)"""
grille[ligne][col] = symbole  

#Fonction permettant de tester si une ligne est gagnante
def ligne_gagnante(grille, ligne) :
    """List x Int --> Bool
    Vérifie si une ligne est gagnante"""
    
    symbole = grille[ligne][0]
    
    #On vérifie si la ligne ne contient que ce symbole
    for j in  range(1, TAILLE):
        if grille[ligne][j] == symbole : 
            gagnant = False
    
    return gagnant

 
#Fonction permettant de tester si une colonne est gagnante    
def colonne_gagnante(grille, col) :
    """List x Int --> Bool
    Vérifie si une ligne est gagnante"""
    
    symbole = grille[col][0] 
    gagnant = True
    
    #On vérifie si la ligne ne contient que ce symbole
    for i in  range(1, TAILLE):
        if grille[col][i] != symbole :
            gagnant = False
    
    return gagnant
    

#Fonction permettant de tester si une diagonale est gagnante    
def diagonale_gagnante(grille, ligne, col) :
    """List x Int x Int --> Bool
    Vérifie si l'une des deux diagonales est gagnante"""
    symbole = grille[ligne][col]
    gagnant = True
    
    if ligne == col : #diagonale (0,0);(1,1);(2,2)
        for i in range(TAILLE):
            if grille[i][i] != symbole
                gagnant = False
        if ligne == 1 : #ça peut être la seconde diagonale :
            gagnant = True
    elif ligne != col or ligne == 1 : #diagonale (0,2);(1,1);(2,0)
        for i in range(TAILLE:
            if grille[i][TAILLE - 1 - i] != symbole :
                gagnant = False
    return gagnant
    

#Fonction permettant de tester si un coup est gagnant    
def coup_gagnant(grille, ligne, col) :
    """List x Int x Int --> Bool
    Vérifie si le dernier coup joué est gagnant"""
    
    symbole = grille[ligne][col]
    gagnant = True
    
    #On vérifie si la ligne est gagnante
    if ligne_gagnante(grille, ligne :
        return True
    #on vérifie si la colonne est gagnante:
    elif colonne_gagnante(grille, col) :
    return True 
    #sinon, on vérifie si la case est sur une diagonale
    #diagonale si la somme du numéro de la ligne est de la colonne est paire
    elif (col + ligne) // 2 == 0 :
        return diagonale_gagnante(grille, col)
    else: #rien n'est gagnant
        return False     
        
#ATTENTION : penser à vérifier ici toutes les lignes, toutes les colonnes et 
#toutes les diagonales, en partant de toutes les cases possibles! 


#Fonction permettant de jouer humain contre humain
def jeu2joueurs() :
    """None --> None
    Lance une partie morpions à 2 joueurs humains"""
    grille = init_grille() #Initialisation de la grille
    fin_partie = False #Booléen permettant de savoir quand finir la partie
    tour = 1 #Compteur du tour
    
    while not(fin_partie):
        affiche_grille(grille) #Afficher la grille
        if tour % 2 != 0: #Si le tour est pair, c'est au joueur 'X' de jouer
            symbole = 'X'
        else :
            symbole = 'O' #Sinon, c'est au joueur 'O'
        print("Tour", tour, "c'est au joueur", symbole, "de jouer")
        # Récupération du coup du joueur
        ligne = int(input("Indiquez la ligne où déposer votre symbole : "))
        col = int(input("Indiquez la colonne où déposer votre symbole : "))
        while not(case_libre(grille, ligne, col)) : 
            #Vérification que le coup est valide, sinon on recommence
            print("Cette case est déjà occupée")
            ligne = int(input("Indiquez la ligne où déposer votre symbole : "))
            col = int(input("Indiquez la colonne où déposer votre symbole : "))
        deposer_symbole(symbole, grille, ligne, col) #le coup est joué sur la grille
        if coup_gagnant(grille, ligne, col) : #Vérification si le coup est gagnant
            affiche_grille(grille) 
            print("Bravo, joueur", symbole, "a gagné !")
            fin_partie = True
        if grille_pleine(grille) : #Vérification si la grille est pleine
            affiche_grille(grille)
            print("C'est un match nul, la grille est remplie!")
            fin_partie = True
        tour = tour + 1 #Incrémentation du tour


#Programme permettant de lancer le jeu
print("Bienvenue sur le jeu du morpion!")

jeu2joueurs()


#A décommenter lorsque les deux fonctions permettant de jouer contre l'ordinateur seront implémentées

#print("Si vous voulez jouer contre un humain, tapez 1 ; si vous voulez jouer contre l'ordinateur en mode aléatoire, tapez 2 ; si vous voulez jouer contre une ia un peu plus élaborée, tapez 3 : ", end= " ")
#reponse = int(input())

#while reponse != 1 and reponse != 2 and reponse != 3:
#    print("Je n'ai pas compris votre réponse")
#    print("Si vous voulez jouer contre un humain, tapez 1 ; si vous voulez jouer contre l'ordinateur, tapez 2 ; si vous voulez jouer contre une ia un peu plus élaborée, tapez 3 : ", end= " ")
#    reponse = int(input())
    
#if reponse == 1 :
#    jeu2joueurs()
#elif reponse == 2 :
#    jeu_ia_random()
#else :
#    jeu_ia()