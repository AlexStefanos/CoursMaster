nombre_notes = 5
note_min=20
note_max=0
indice_min=0
indice_max=0
note_moy=0
for i in range(1,nombre_notes + 1):
    print("Veuillez entrer la note numéro",i)
    note_saisie=float(input())
    while note_saisie > 20 or note_saisie < 0 :
        print ("Attention! une note est comprise entre 0 et 20")
        print ("Veuillez entrer la note numéro", i)
        note_saisie=float(input())
    if note_min > note_saisie :
        note_min = note_saisie
        indice_min=i
    if note_max < note_saisie :
        note_max=note_saisie
        indice_max=i
print("Pour les ",nombre_notes,"saisies, la plus mauvaise note est : ",note_min,"c'est la ",indice_min,"e note saisie. La meilleur note est : ",note_max,"c'est la ",indice_max,"e note saisie.")
