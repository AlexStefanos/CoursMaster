liste=["Je", "suis", "trop", "beau","woooow"]
lp=[]
lm=[]
i=0
while i < len(liste) :
    if len(liste[i]) < 5:
        lp.append(liste[i])
    else :
        lm.append(liste[i])
    i=i+1
print (lp)
print (lm)
