dividende=int(input("Donnez un entier : "))
diviseur=int(input("Donnez un entier : "))
quotient=dividende//diviseur
reste=dividende%diviseur
if ((dividende>=0) and (diviseur>0)):
    print("On fait la division euclidienne de ",dividende,"par ",diviseur)
    print("Le quotient de la division est ",quotient)
    print("Le reste de la division est ",reste)
    print(dividende,"=",diviseur,"*",quotient,"+",reste)
else :
    print("Cette division euclidienne est impossible.")
