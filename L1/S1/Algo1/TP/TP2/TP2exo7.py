a=int(input("Donnez un entier strictement positif : "))
b=0
c=0
d=0
e=0
if (a<0):
    print("Cela ne foncionne pas")
elif (a>60) or (b>60):
        b=a//60
        a=a%60
        c=b//60
        b=b%60
        d=c//24
        c=c%24
        e=d//31
        d=d%31
        f=e//365
        e=e%365
print("On a ",e,"années",d,"jours",c,"heures",b,"minutes",a,"secondes")

