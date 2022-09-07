chaine=input("Saisissez une chaine de caractère :")
carac=input("Saisissez un caractère :")
liste=[]
i=0
a=len(chaine)
while i <= a-1:
    if(chaine[i]==carac):
        liste.append(i)
    i=i+1
print(liste)

