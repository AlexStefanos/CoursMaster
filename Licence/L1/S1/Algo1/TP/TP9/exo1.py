def tri_insertion(liste):
    """List --> None
    Tri la liste donnée en paramètre"""
    for i in range(1, len(liste)):
        elem = liste[i]
        pos = i
        while pos > 0 and liste[pos - 1] > elem :
            liste[pos] = liste[pos-1]
            pos = pos - 1
        liste[pos] = elem
list=[8, 10, 5, 7]
a=tri_insertion(list)
print(a)
