nb=int(input("Donnez un entier : "))
if ((nb % 100)==10) :
    print("L'ordinal abrégé anglais du nombre donné est",nb,"th")
elif (((nb-1)%10)==0) :
    print("L'ordinal abrégé anglais du nombre donnée est",nb,"st")
elif (((nb-2)%10)==0) :
    print("L'ordinal abrégé anglais du nombre donnée est",nb,"nd")
elif (((nb-3)%10)==0) :
    print("L'ordinal abrégé anglais du nombre donnée est",nb,"rd")
else :
    print("L'ordinal abrégé anglais du nombre donnée est",nb,"th")
