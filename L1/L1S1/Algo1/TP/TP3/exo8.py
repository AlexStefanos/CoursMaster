b=0
d=0
for a in range (5) :
    b=b+1
    print("Veuillez entrer le numéro",b,":")
    a=int(input())
    c=a
    if c==42 :
        d=1
if d==0 :
    print("Perdu")
else:
    print("Gagné")
