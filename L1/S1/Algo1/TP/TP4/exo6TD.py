longueur =int(input("Entrez la longueur de la liste"))
liste=[]
tri=True
for i in range (longueur):
    n=int(input("Entrez la valeur d'un élément"))
    liste.append(n)
    if(liste[i]<liste[i-1]):
        tri=False
print ("Votre liste est la suivante :",liste)
if Tri :
    print("La liste est triée")
else :
    print("La liste n'est pas triée")
