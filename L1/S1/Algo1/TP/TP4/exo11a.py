chaine=input("Saisissez une suite d'entiers séparés par des espaces : ")
val=input("Saisissez un entier :")
i=0
a=0
if val in chaine :
    print("La valeur entrée est dans la chaine")
    for c in chaine :
        if c==val:
            i=i+1
            a=i
        else :
            i=i+1
    print("La valeur entrée est la ",a,"e de la chaine en comptant les espaces")
else :
    print("Cette valeur n'est pas dans la chaine")

