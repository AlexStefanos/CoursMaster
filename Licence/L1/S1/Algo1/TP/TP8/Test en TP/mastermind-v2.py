import random

def g_p(ne,nc):
    """Int x Int --> Int List 
    Retourne une liste de ne entiers fournis par l'utilisateur. Les entiers sont 
    compris entre 0 et nc-1"""

    # Initialisation de la liste
    p=[]
    
    print("Fournir",ne,"couleurs comprises entre 0 et",nc-1)
    for i in range(1,ne+1):
        print("Fournir la couleur de rang",i,":")
        c=int(input())
        while c<0 or c>=nc:
            print("Fournir la couleur de rang",i,":")
            c=int(input())
        p.append(c)
    return(p)

def g_s(ne,nc):
    """Int x Int --> Int List 
    Retourne une liste de ne entiers définis aléatoirement. Les entiers sont 
    compris entre 0 et nc-1"""

    # Initialisation de la liste
    s=[]
    
    for i in range(ne):
        s.append(random.randrange(nc))
    return(s)

def dnbp(p,s):
    """List x List --> Int
    dnbp Définir le Nombre d'éléments Bien Placés 
    Retourne le nombre de fois où les éléments de même rang sont identiques dans
    les listes p et c. Les listes p et s sont de même longueur.
    Dans ce programme p représente la proposition du joueur et s représente la
    la solution."""

    n=0
    for i in range(len(p)):
        if p[i]==s[i]:
            n+=1
    return(n)

def occurence(l,nc):
    """List x Int --> List
    Retourne une liste o de longueur nc, dont la i-ème case contient
    le nombre d'occurences de la valeur i dans la liste l"""

    # Initialisation à 0 des nc valeurs de la liste o
    o=[]
    for i in range(nc):
        o.append(0)
    # Dénombrement des entiers présents dans la liste l
    # Exemple : pour l[i] qui vaut 3 on incrémente o[3]
    for i in range(len(l)):
        o[l[i]]+=1
    return(o)
        
def dnbpc(p,s,c):
    """Int List x Int List x Int --> Int
    dnbpc Définir le Nombre d'éléments Bien Placés pour une Couleur donnée
    Retourne le nombre de fois où la couleur c est dans la meme position dans
    la proposition p et dans la solution s."""

    n=0
    for i in range(len(p)):
        if c==p[i] and c==s[i]:
            n+=1
    return(n)

def dnmp(p,s,nc):
    """Int List x Int List x Int --> Int
    Retourne le nombre de couleurs mal placées dans la proposition p
    par rapport à la solution s"""

    # op contient le dénombrements des couleurs présentes dans p
    op=occurence(p,nc)
    # os contient le dénombrements des couleurs présentes dans s
    os=occurence(s,nc)

    n=0
    for c in range(nc):
        # m représente le nombre de fois où la couleur c apparait
        # à la fois dans la proposition et dans la solution
        m=min(op[c],os[c])
        # On retranche à m le nombre de fois où la couleur c est bien placée
        # On obtient le nombre de fois où la couleur est mal placée
        # On incrémente n 
        n+=m-dnbpc(p,s,c)
    return(n)

nc=int(input("Nombre de couleurs : "))
ne=int(input("Nombre d'éléments : "))
nt=int(input("Nombre de tentatives : "))

# Définition aléatoire de la solution s constituée de ne éléments parmi nc couleurs
s=g_s(ne,nc)
# Affichage éventuel de la solution pour tester le programme
print("s:",s)

t=1
trouve=False

# Tant que la solution n'a pas été trouvée et qu'il reste des tentatives
while t<=nt and not trouve:
    print("\nTentative ",t,"sur",nt)
    # On demande à l'utilisateur de fournir un proposition p
    p=g_p(ne,nc)
    print("\np:",p)

    # Si les listes p et s sont identiques on a trouvé la solution
    # On réalise ce premier test afin d'éviter les calculs des éléments
    # bien placés et mal placés
    if p==s:
            trouve=True
    # On calcule et on affiche le nombre d'éléments bien placés
    # On calcule et on affiche le nombre d'éléments mal placés
    # On incrémente le nombre de tentatives réalisées
    else:
            nbp=dnbp(p,s)
            print("Bien placé(s) : ",nbp)
            nmp=dnmp(p,s,nc)
            print("Mal  placé(s) : ",nmp)
            t+=1

# A la sortie de la boucle on indique si la solution a été trouvée et on 
# affiche la solution
if trouve:
    print("Réussite :",s)
else:
    print("Echec :",s)

