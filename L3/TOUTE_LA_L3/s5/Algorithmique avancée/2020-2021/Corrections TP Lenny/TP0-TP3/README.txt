Instructions
**************************
Extrayez svp le dossier code_NAMOLARU_51704115 du fichier zip. 
Placez ce dossier dans le répertoire courant et suivez l'exemple:
**************************
[ij04115@saphyr:~]:dim. oct. 11$ cd code_NAMOLARU_51704115
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ ls
arbre.c  arbre.h  liste.c  liste.h  maListe.c  monArbre.c
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ gcc -c maListe.c
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ gcc -c liste.c
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ ls
arbre.c  arbre.h  liste.c  liste.h  liste.o  maListe.c  maListe.o  monArbre.c
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ gcc -lm -o TP11 liste.o maListe.o
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ ls
arbre.c  arbre.h  liste.c  liste.h  liste.o  maListe.c  maListe.o  monArbre.c  TP11
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ ./TP11
**** Position 0 de la liste ****
x = ? 1
y = ? 1

****** Menu ******
I - Insérer
S - Supprimer
A - Afficher
Q - Quitter
Votre choix ? Q
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ gcc -c  monArbre.c
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ gcc -c  arbre.c
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ gcc -lm -o TP12 arbre.o monArbre.o
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ ls
arbre.c  arbre.o  liste.h  maListe.c  monArbre.c  TP11
arbre.h  liste.c  liste.o  maListe.o  monArbre.o  TP12
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$ ./TP12
**** La racine de l'arbre ****
Caractère = ? A

****** Menu ******
G - Insérer fils gauche
D - Insérer fils droit
P - Parcours prefixe
Q - Quitter
Votre choix ? Q
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 11$

********************************************************************************
[ij04115@saphyr:~]:dim. oct. 18$ cd code_NAMOLARU_51704115
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 18$ gcc graphe.c -o graphe
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 18$ ./graphe
Ordre du graphe (n > 0) = ?
6
Ligne 1 (6 chiffres séparés que par des virgule) :
0,1,0,0,1,0
Ligne 2 (6 chiffres séparés que par des virgule) :
1,0,1,1,0,0
Ligne 3 (6 chiffres séparés que par des virgule) :
0,1,0,1,0,1
Ligne 4 (6 chiffres séparés que par des virgule) :
0,1,1,0,1,1
Ligne 5 (6 chiffres séparés que par des virgule) :
1,0,0,1,0,0
Ligne 6 (6 chiffres séparés que par des virgule) :
0,0,1,1,0,0
 0  1  0  0  1  0
 1  0  1  1  0  0
 0  1  0  1  0  1
 0  1  1  0  1  1
 1  0  0  1  0  0
 0  0  1  1  0  0
Sommet de référence (s >= 0) = ?
0
**** Afficher tous les sommets marqués ****
Numéro du sommet de référence : 0
0,1,4,2,3,5
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 18$

*********************************************************************************
[ij04115@saphyr:~]:dim. oct. 18$ cd code_NAMOLARU_51704115
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 18$ gcc -c plusCourtChemin.c
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 18$ gcc -c file.c
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 18$ gcc -lm -o plusCourtChemin file.o plusCourtChemin.o
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 18$ ./plusCourtChemin
Ordre du graphe (n > 0) = ?
4
Ligne 1 (4 chiffres séparés que par des virgule) :
1,1,0,0
Ligne 2 (4 chiffres séparés que par des virgule) :
1,0,1,1
Ligne 3 (4 chiffres séparés que par des virgule) :
0,1,0,1
Ligne 4 (4 chiffres séparés que par des virgule) :
0,1,1,0
 1  1  0  0
 1  0  1  1
 0  1  0  1
 0  1  1  0
Sommet de référence (s >= 0) = ?
0
**** Afficher tous les sommets marqués ****
Numéro du sommet de référence : 0
0,1,2,3
[ij04115@saphyr:~/code_NAMOLARU_51704115]:dim. oct. 18$


