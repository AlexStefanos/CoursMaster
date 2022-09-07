a=int(input("Donnez un entier a : "))
b=int(input("Donnez un entier b : "))
c=int(input("Donnez un entier c : "))
if ((a>b) and (a>c)) :
    print("Le chiffre le plus grand est le ",a)
    if (b>c) :
        print("Le chiffre moyen est le ",b)
        print("Le chiffre le plus petit est le ",c)
    else :
        print("Le chiffre moyen est le ",c)
        print("Le chiffre le plus petit est le ",c)
elif ((c>b) and (c>a)) :
    print("Le chiffre le plus grand est le ",c)
    if (b>a) :
        print("Le chiffre moyen est le ",b)
        print("Le chiffre le plus petit est le ",a)
    else :
        print("Le chiffre moyen est le ",a)
        print("Le chiffre le plus petit est le ",b)
elif ((b>a) and (b>c)) :
    print("Le chiffre le plus grand est le ",b)
    if (a>c) :
        print("Le chiffre moyen est le ",a)
        print("Le chiffre le plus petit est le ",c)
    else :
        print("Le chiffre moyen est le ",c)
        print("Le chiffre le plus petit est le ",a)
else :
    print ("Tous les chiffres sont égaux")

        
            
            
    
