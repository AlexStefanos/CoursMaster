# -*- coding: utf-8 -*-
import os
os.system('clear')

print("Bienvenue dans mon jeu Mastermind ! Voici le principe du jeu : Un joueur choisit une combinaison secrète de 5 couleurs parmi 8 couleurs disponibles. Dans ce programme, les couleurs sont représentées par des numéros de 0 à 7 : ")
print("- 0 : Bleu")
print("- 1 : Rouge")
print("- 2 : Vert")
print("- 3 : Jaune")
print("- 4 : Violet")
print("- 5 : Blanc")
print("- 6 : Gris")
print("- 7 : Noir")

cprop=[]
ccache=[]
def demande_cs():
    nums=['0','1','2','3','4','5','6','7']
    csecr=[]
    csnum1=input("Veuillez entrer le numéro de la première couleur de la combinaison secrète proposée : ")
    while csnum1 not in nums :
        csnum1=input("Veuillez entrer le numéro de la première couleur de la combinaison secrète proposée : ")
    csnum2=input("Veuillez entrer le numéro de la deuxième couleur de la combinaison secrète proposée : ")
    while csnum2 not in nums :
        csnum2=input("Veuillez entrer le numéro de la deuxième couleur de la combinaison secrète proposée : ")
    csnum3=input("Veuillez entrer le numéro de la troisième couleur de la combinaison secrète proposée : ")
    while csnum3 not in nums :
        csnum3=input("Veuillez entrer le numéro de la troisième couleur de la combinaison secrète proposée : ")
    csnum4=input("Veuillez entrer le numéro de la quatrième couleur de la combinaison secrète proposée : ")
    while csnum4 not in nums :
        csnum4=input("Veuillez entrer le numéro de la quatrième couleur de la combinaison secrète proposée : ")
    csnum5=input("Veuillez entrer le numéro de la cinquième couleur de la combinaison secrète proposée : ")
    while csnum5 not in nums :
         csnum5=input("Veuillez entrer le numéro de la cinquième couleur de la combinaison secrète proposée : ")
    csecr.append(csnum1)
    csecr.append(csnum2)
    csecr.append(csnum3)
    csecr.append(csnum4)
    csecr.append(csnum5)
    return csecr
c=demande_cs()


os.system('clear')

def demande_cp():
    nums=['0','1','2','3','4','5','6','7']
    cprop=[]
    cpnum1=input("Veuillez entrer le numéro de la première couleur de la combinaison proposée : ")
    while cpnum1 not in nums :
        cpnum1=input("Veuillez entrer le numéro de la première couleur de la combinaison proposée : ")
    cpnum2=input("Veuillez entrer le numéro de la deuxième couleur de la combinaison proposée : ")
    while cpnum2 not in nums :
        cpnum2=input("Veuillez entrer le numéro de la deuxième couleur de la combinaison proposée : ")
    cpnum3=input("Veuillez entrer le numéro de la troisième couleur de la combinaison proposée : ")
    while cpnum3 not in nums :
        cpnum3=input("Veuillez entrer le numéro de la troisième couleur de la combinaison proposée : ")
    cpnum4=input("Veuillez entrer le numéro de la quatrième couleur de la combinaison proposée : ")
    while cpnum4 not in nums :
        cpnum4=input("Veuillez entrer le numéro de la quatrième couleur de la combinaison proposée : ")
    cpnum5=input("Veuillez entrer le numéro de la cinquième couleur de la combinaison proposée : ")
    while cpnum5 not in nums :
        cpnum5=input("Veuillez entrer le numéro de la cinquième couleur de la combinaison proposée : ")
    cprop.append(cpnum1)
    cprop.append(cpnum2)
    cprop.append(cpnum3)
    cprop.append(cpnum4)
    cprop.append(cpnum5)
    return cprop
d=demande_cp()

def nb_mal_places(proposition,solution):
    e=0
    for v in range (0,5):
        if (proposition[v] in solution) and (proposition[v] != solution[v]):
            e=e+1
            v=v+1
        else:
            v=v+1
    return e


def nb_bien_places(proposition,solution):
    a=0
    for i in range (0,5):
        if proposition[i]==solution[i]:
            a=a+1
            i=i+1
        else:
            i=i+1
    return a
b=nb_bien_places(d,c)
f=nb_mal_places(d,c)
print(b,"pion(s) est/sont bien placé(s) et",f,"pion(s) sont dans la liste mais mal placés")
w=1
if b==5:
    w=12
    print("Félicitations ! Vous avez trouvé la combinaison secrète")
else :
    print("Il vous reste",10-w,"tentatives.")
    w=w+1
    
while w<=10 :
    d=demande_cp()
    b=nb_bien_places(d,c)
    f=nb_mal_places(d,c)
    print(b,"pion(s) est/sont bien placé(s) et",f,"pion(s) est/sont dans la liste mais mal placés")
    if b==5 :
        w=10
        print("Félicitations ! Vous avez trouvé la combinaison secrète")
    else :
        print("Il vous reste",10-w,"tentatives.")
    w=w+1

if w==11 :
    print("Vous avez perdu ! Vous réussirez la prochaine fois.")
    
    
