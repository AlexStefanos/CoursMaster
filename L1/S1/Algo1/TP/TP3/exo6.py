hauteur=int(input("Entrez un entier :"))
p=hauteur-1
for i in range (1,hauteur+1):
    print(" "*(p),"*"*(i*2-1))
    p=p-1
