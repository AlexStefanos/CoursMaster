from donnees import liste_mots
import random



nb = random.randrange(1361)
motsolu = liste_mots[nb]
motprop = []
for i in range (0 , len(motsolu)):
    motprop.append('*')

s = 0
i = 12
while i != 0:
    print ("Mot à trouver : ",motprop)
    print ("encore ",i," chance(s).")
    c = input("Tapez une lettre (uniquement en minuscule): ")
    if c in motsolu:
        for a in range(0, len(motsolu)):
            if motsolu[a] == c:
                motprop.insert(a , c)
                del motprop[a+1]
                s = s + 1
        print("Bien joué !!:D")
    else :
        print ("non cette lettre ne se trouve pas dans le mot.")
    if s == len(motsolu):
        print("Bravo !! tu as trouvé le mot")
        print("le mot etait bien ",motsolu,".")
        break
    i = i - 1
if i == 0:
    print ("Tu n'as pas trouvé le mot , le mot etait",motsolu)
    print ("tu feras mieux une prochaine fois :).")

