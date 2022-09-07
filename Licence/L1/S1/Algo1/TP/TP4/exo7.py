chaine=input("Saisissez une chaîne de caractère :")
ch=""
i=len(chaine)
while i > 0:
    ch=ch+chaine[i-1]
    i=i-1
print(ch)
