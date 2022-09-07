chaine=input("Saisissez une chaine de caractère :")
carac=input("Saisissez un caractère :")
chaine2=""
i=0
for c in chaine :
    if chaine[i]==carac:
        i=i+1
    else :
        chaine2=chaine2+chaine[i]
        i=i+1
print(chaine2)
