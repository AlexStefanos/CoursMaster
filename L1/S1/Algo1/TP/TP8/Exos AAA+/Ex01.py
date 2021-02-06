import random
import os

solution = []
for i in range (0, 5):
    nb = random.randrange(8)
    solution.append(nb)
bien_place = []
present = []

proposition = []
def nb_present(solution, proposition):
    i = 0
    j = 0
    present = []
    while i != 5:
        j = 0
        while j != 5:
            if solution[j] == proposition[i] :
                present.append(proposition[i])
            j = j + 1
        i = i + 1
    return present

def nb_mal_places(solution, proposition):
    s = 0
    mal = []
    for i in range (0, 5):
        if solution[i]  == proposition[i]:
            s = s + 1
        else:
            if proposition[i] in present:
                mal.append(proposition[i])
    return mal

bien = []


def nb_bien_places(solution, proposition):
    s = 0
    bien = []
    for i in range (0, 5):
        if solution[i]  == proposition[i]:
            bien.append(proposition[i])
    return bien

def pos_Mal(solution, proposition):
    pos_bien = []
    z = 0
    for i in range (0, len(proposition)):
        if proposition[i] in bien and proposition[i] in mal:
            if proposition[i] == solution[i]:
                z = z + 1
            else :
                pos_mal.append(i)
    return pos_bien

def pos_Bien(solution, proposition):
    pos_mal = []
    for i in range (0, len(proposition)):
        if proposition[i] in bien and proposition[i] in mal:
            if proposition[i] == solution[i]:
                pos_bien.append(i)
    return pos_mal

#print ("combinaison secrète : ",solution)
comp = 1
pos_mal = []
pos_bien = []

while (comp <= 10):
    print ("Tentative n°",comp)
    i = 0
    proposition = [0, 0, 0, 0, 0]
    print ("Veuillez Donner 5 couleurs (entre 0 et 7)")
    while (i != 5):
        print("couleur :",i+1)
        nb = int(input())
        if nb >= 0 and nb <= 7:
            proposition.insert(i, nb)
            del proposition[i+1]
        while nb < 0 or nb > 7:
            print ("Rentrez une valeur entre 0 et 7")
            print ("Couleur : ",i+1)
            nb = int(input())
            proposition.insert(i, nb)
            del proposition[i+1]
        i = i + 1
    os.system("clear")
    print ("Votre proposition : ",proposition)
    bien = nb_bien_places(solution, proposition)
    present = nb_present(solution, proposition)
    mal = nb_mal_places(solution, proposition)  
    pos_mal = pos_Mal(solution, proposition)
    pos_bien = pos_Bien(solution, proposition)
    if (proposition == solution):
        os.system("clear")
        print ("Votre proposition : ",proposition)
        print ("Félicition vous avez remporté la partie !!")
        print ("Solution : ",solution)
        break
    print (len(bien),"Pion(s) bien placées et ",len(mal)," pion(s) mal placées")
    print(", correspondant aux couleurs ",bien," pour les biens placées, et aux couleurs ",mal," pour les mal placées.")
    comp = comp + 1
if comp > 10 and proposition != solution:
    print ("Vous avez perdu la partie, Vous aurez peut-etre plus de chance une prochaine fois :).")
