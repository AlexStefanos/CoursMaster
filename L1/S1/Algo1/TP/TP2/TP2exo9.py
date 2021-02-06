a=int(input("Donnez un premier entier : "))
b=int(input("Donnez un second entier : "))
#on veut donner le plus grand nombre en valeur absolue
if (a>0) and (b>0) :
    if (a>b) :
        print("Le plus grand nombre en valeur absolue est",a)
    else :
        print("Le plus grand nombre en valeur absolue est",b)
if (a<0) and (b>0) :
    if (-a<b) :
        print("Le plus grand nombre en valeur absolue est",b)
    else :
        print("Le plus grand nombre en valeur abolue est",a)
if (a>0) and (b<0) :
    if(-b<a) :
        print("Le plus grand nombre en valeur absolue est",a)
    else :
        print("Le plus grand nombre en valeur absolue est",b)
if (a<0) and (b<0) :
    if(-a<-b) :
        print("Le plus grand nombre en valeur absolue est",b)
    else :
        print("Le plus grand nombre en valeur absolue est",a)
