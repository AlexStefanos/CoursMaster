chaine=input("Saisissez une chaine de caractère : ")
mot=input("Saisissez une lettre : ")
i=0
a=0
b=-1
if mot in chaine:
    print ("La lettre entrée est dans la chaîne")
    while b != a :
        for c in chaine :
            if c==mot :
                a=i
                i=i+1
                b=a
                break
            else :
                i=i+1
                b=b+1
    print("Le lettre entrée est la ",a,"e de la chaine.")
else :
    print("Cette lettre n'est pas dans la chaine")

    
    
