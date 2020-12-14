import random
TAILLE = 3

# Fonction permettant d'initialiser la grille
def init_grille():
    """None --> List
    Retourne une grille de morpion (liste de listes) initialisée
    (remplie de " ")"""
    
    grille = []
    
    for i in range(TAILLE) :
        ligne = []
        for j in range(TAILLE) :
            ligne.append(" ")
        grille.append(ligne)
        
    return grille

# Fonction permettant d'afficher la grille
def affiche_grille(grille) :
    """List --> None
    Affiche la grille de morpion passée en paramètres"""

    print("    0   1   2")
    for i in range(TAILLE):
        print("    -   -   -")
        print(i, "|", end=" ")
        for j in range(TAILLE):
            print(grille[i][j], "|", end=" ")
        print()

#Fonction permettant de tester si une grille est pleine
def grille_pleine(grille) :
    """List --> Bool
    Vérifie si la grille de morpions passée en paramètre est pleine"""
    
    pleine = True
    i = 0
    while i < TAILLE and pleine :
        j = 0
        while j < TAILLE and pleine :
            if grille[i][j] == " " :
                pleine = False
            j = j + 1
        i = i + 1
    return pleine 

#Fonction permettant de tester si une case est libre
def case_libre(grille, ligne, col) :
    """List x Int x Int --> Bool
    Vérifie si la case grille[ligne][col] du morpion est libre"""
    
    if grille[ligne][col] == " ":
        return True
    else :
        return False

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
    gagnant = True
    
    #On vérifie si la ligne ne contient que ce symbole
    for j in  range(1, TAILLE):
        if grille[ligne][j] != symbole :
            gagnant = False
    
    return gagnant
 
#Fonction permettant de tester si une colonne est gagnante    
def colonne_gagnante(grille, col) :
    """List x Int --> Bool
    Vérifie si une ligne est gagnante"""
    
    symbole = grille[0][col]
    gagnant = True
    
    #On vérifie si la ligne ne contient que ce symbole
    for i in  range(1, TAILLE):
        if grille[i][col] != symbole :
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
            if grille[i][i] != symbole :
                gagnant = False
        if ligne == 1 : #ça peut être la seconde diagonale :
            gagnant = True
    if ligne != col or ligne == 1 : #diagonale (0,2);(1,1);(2,0)
        for i in range(TAILLE):
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
    if ligne_gagnante(grille, ligne) :
        return True
    #on vérifie si la colonne est gagnante:
    elif colonne_gagnante(grille, col) :
        return True
    #sinon, on vérifie si la case est sur une diagonale
    #diagonale si la somme du numéro de la ligne est de la colonne est paire
    elif (col + ligne) % 2 == 0 :
        return diagonale_gagnante(grille, ligne, col)
    else: #rien n'est gagnant
        return False                        

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

#Fonction permettant de trouver la liste des coups possibles
def liste_coups_possibles(grille) :
    """List --> List
    Retourne la liste des coups possibles"""
    
    return [[i, j] for i in range(TAILLE) for j in range(TAILLE) if grille[i][j] == " "]

#Fonction permettant de jouer humain contre une ia aléatoire
def jeu_ia_random() :
    """None --> None
    Lance une partie de morpions contre un joueur aléatoire"""
    grille = init_grille() #Initialisation de la grille
    fin_partie = False #Booléen permettant de savoir quand finir la partie
    tour = 1 #Compteur du tour
    
    while not(fin_partie):
        affiche_grille(grille) #Afficher la grille
        if tour % 2 != 0: #Si le tour est pair, c'est à l'humain de jouer
            symbole = 'X'
            print("Tour", tour, "c'est au joueur", symbole, "de jouer")
            # Récupération du coup du joueur
            ligne = int(input("Indiquez la ligne où déposer votre symbole : "))
            col = int(input("Indiquez la colonne où déposer votre symbole : "))
            while not(case_libre(grille, ligne, col)) : #Vérification que le coup est valide, sinon on recommence
                print("Cette case est déjà occupée")
                ligne = int(input("Indiquez la ligne où déposer votre symbole : "))
                col = int(input("Indiquez la colonne où déposer votre symbole : "))
        else : #C'est à l'ordi de jouer
            symbole = 'O'
            print("Tour", tour, "c'est à l'ordinateur de jouer")
            coups_possibles = liste_coups_possibles(grille) #calcul de tous les coups possibles
            coup_choisi = random.choice(coups_possibles) #tirage aléatoire d'un coup
            ligne = coup_choisi[0]
            col = coup_choisi[1]
        deposer_symbole(symbole, grille, ligne, col) #le coup est joué sur la grille
        if coup_gagnant(grille, ligne, col) : #Vérification si le coup est gagnant
            affiche_grille(grille)
            if symbole == 'X':
                print("Bravo, vous avez gagné !")
            else:
                print("Dommage, l'ordinateur vous a battu")
            fin_partie = True
        if grille_pleine(grille) : #Vérification grille pleine
            affiche_grille(grille)
            print("C'est un match nul, la grille est remplie!")
            fin_partie = True
        tour = tour + 1

#Fonction permettant de jouer humain contre une ia légèrement plus élaborée
def jeu_ia() :
    """None --> None
    Lance une partie de morpions contre une IA légèrement plus élaborée"""
    grille = init_grille()
    fin_partie = False
    tour = 1
    
    while not(fin_partie):
        affiche_grille(grille)
        if tour % 2 != 0:
            symbole = 'X'
            print("Tour", tour, "c'est au joueur", symbole, "de jouer")
            ligne = int(input("Indiquez la ligne où déposer votre symbole : "))
            col = int(input("Indiquez la colonne où déposer votre symbole : "))
            while not(case_libre(grille, ligne, col)) :
                print("Cette case est déjà occupée")
                ligne = int(input("Indiquez la ligne où déposer votre symbole : "))
                col = int(input("Indiquez la colonne où déposer votre symbole : "))
        else :
            symbole = 'O'
            print("Tour", tour, "c'est à l'ordinateur de jouer")
            coups_possibles = liste_coups_possibles(grille)
            
            #On regarde si un prochain coup est gagnant
            gagnant_possible = False
            nb_coups = len(coups_possibles)
            i = 0
            while i < nb_coups and not(gagnant_possible) :
                coup = coups_possibles[i]
                deposer_symbole('O', grille, coup[0], coup[1])
                if coup_gagnant(grille, coup[0], coup[1]):
                    gagnant_possible = True
                    coup_choisi = coup
                deposer_symbole(" ", grille, coup[0], coup[1])
                i = i + 1
            if not(gagnant_possible) : #il n'y a pas de coup gagnant
                #On regarde si un prochain coup de l'adversaire est gagnant
                perdant_possible = False
                i = 0
                while i < nb_coups and not(perdant_possible) :
                    coup = coups_possibles[i]
                    deposer_symbole('X', grille, coup[0], coup[1])
                    if coup_gagnant(grille, coup[0], coup[1]):
                        perdant_possible = True
                        coup_choisi = coup
                    deposer_symbole(" ", grille, coup[0], coup[1])
                    i = i + 1
                if not(perdant_possible) : #il n'y a pas de coup perdant
                    #Jouer la case centrale si elle est libre
                    if case_libre(grille, 1, 1) :
                        coup_choisi = [1,1]
                    else : #On joue au hasard
                        coup_choisi = random.choice(coups_possibles) #tirage aléatoire d'un coup 
            ligne = coup_choisi[0]
            col = coup_choisi[1]
        deposer_symbole(symbole, grille, ligne, col)
        if coup_gagnant(grille, ligne, col) :
            affiche_grille(grille)
            if symbole == 'X':
                print("Bravo, vous avez gagné !")
            else:
                print("Dommage, l'ordinateur vous a battu")
            fin_partie = True
        if grille_pleine(grille) :
            affiche_grille(grille)
            print("C'est un match nul, la grille est remplie!")
            fin_partie = True
        tour = tour + 1


#Programme permettant de lancer le jeu
print("Bienvenue sur le jeu du morpion!")
print("Si vous voulez jouer contre un humain, tapez 1 ; si vous voulez jouer contre l'ordinateur en mode aléatoire, tapez 2 ; si vous voulez jouer contre une ia un peu plus élaborée, tapez 3", end= " ")
reponse = int(input())

while reponse != 1 and reponse != 2 and reponse != 3:
    print("Je n'ai pas compris votre réponse")
    print("Si vous voulez jouer contre un humain, tapez 1 ; si vous voulez jouer contre l'ordinateur, tapez 2 ; si vous voulez jouer contre une ia un peu plus élaborée, tapez 3", end= " ")
    reponse = int(input())
    
if reponse == 1 :
    jeu2joueurs()
elif reponse == 2 :
    jeu_ia_random()
else :
    jeu_ia()