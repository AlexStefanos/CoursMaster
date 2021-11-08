Instructions
**************************
Extrayez svp le dossier code_NAMOLARU_51704115 du fichier zip. 
Placez ce dossier dans le répertoire courant et utilisez la commande suivante dans votre terminal :
[ij04115@saphyr:~]:lun. déc. 21$ cd code_NAMOLARU_51704115

Suivez les exemples:

**************************
TP4_prim.c
La matrice dans l'exemple représente le graphe que nous avons vu en cours :
AlgoAvanceeParE_Birmele1.pdf, page 85 (Algorithme de Prim : exemple).
**************************
[ij04115@saphyr:~/code_NAMOLARU_51704115]:lun. déc. 21$ gcc TP4_prim.c -o TP4_prim
[ij04115@saphyr:~/code_NAMOLARU_51704115]:lun. déc. 21$ ./TP4_prim
Ordre du graphe (n > 0) = ?
8
Ligne 1 (8 chiffres séparés que par des virgule) :
0,3,1,0,0,0,0,0
Ligne 2 (8 chiffres séparés que par des virgule) :
3,0,4,3,5,0,0,0
Ligne 3 (8 chiffres séparés que par des virgule) :
1,4,0,0,0,2,0,0
Ligne 4 (8 chiffres séparés que par des virgule) :
0,3,0,0,0,0,2,3
Ligne 5 (8 chiffres séparés que par des virgule) :
0,5,0,0,0,0,4,0
Ligne 6 (8 chiffres séparés que par des virgule) :
0,0,2,0,0,0,0,6
Ligne 7 (8 chiffres séparés que par des virgule) :
0,0,0,2,4,0,0,1
Ligne 8 (8 chiffres séparés que par des virgule) :
0,0,0,3,0,6,1,0

Le graphe :
 0.0  3.0  1.0  0.0  0.0  0.0  0.0  0.0
 3.0  0.0  4.0  3.0  5.0  0.0  0.0  0.0
 1.0  4.0  0.0  0.0  0.0  2.0  0.0  0.0
 0.0  3.0  0.0  0.0  0.0  0.0  2.0  3.0
 0.0  5.0  0.0  0.0  0.0  0.0  4.0  0.0
 0.0  0.0  2.0  0.0  0.0  0.0  0.0  6.0
 0.0  0.0  0.0  2.0  4.0  0.0  0.0  1.0
 0.0  0.0  0.0  3.0  0.0  6.0  1.0  0.0

Les arêtes de l'arbre :
Arête 1 : sommet1 : 2 , sommet2 : 1, poids : 3.0
Arête 2 : sommet1 : 1 , sommet2 : 3, poids : 1.0
Arête 3 : sommet1 : 3 , sommet2 : 6, poids : 2.0
Arête 4 : sommet1 : 2 , sommet2 : 4, poids : 3.0
Arête 5 : sommet1 : 4 , sommet2 : 7, poids : 2.0
Arête 6 : sommet1 : 7 , sommet2 : 8, poids : 1.0
Arête 7 : sommet1 : 7 , sommet2 : 5, poids : 4.0

[ij04115@saphyr:~/code_NAMOLARU_51704115]:lun. déc. 21$

**************************
TP4_Kruskal.c
La arêtes dans l'exemple représente le graphe que nous avons vu en cours :
AlgoAvanceeParE_Birmele1.pdf, page 67 (Algorithme de Kruskal : exemple).
**************************
[ij04115@saphyr:~/code_NAMOLARU_51704115]:mar. déc. 22$ gcc TP4_Kruskal.c -o TP4_Kruskal
[ij04115@saphyr:~/code_NAMOLARU_51704115]:mar. déc. 22$ ./TP4_Kruskal
Taille du graphe (n > 0) = ?
11
Ordre du graphe (n > 0) = ?
8

Les arêtes du graphe :
Nous commençons le comptage des sommets et des arêtes à partir de zéro
Arête 0 :
         sommet 1 : 0
         sommet 2 : 1
         poids    : 3
Arête 1 :
         sommet 1 : 0
         sommet 2 : 2
         poids    : 1
Arête 2 :
         sommet 1 : 1
         sommet 2 : 4
         poids    : 5
Arête 3 :
         sommet 1 : 4
         sommet 2 : 6
         poids    : 4
Arête 4 :
         sommet 1 : 6
         sommet 2 : 7
         poids    : 1
Arête 5 :
         sommet 1 : 6
         sommet 2 : 3
         poids    : 2
Arête 6 :
         sommet 1 : 7
         sommet 2 : 3
         poids    : 3
Arête 7 :
         sommet 1 : 3
         sommet 2 : 1
         poids    : 3
Arête 8 :
         sommet 1 : 1
         sommet 2 : 2
         poids    : 4
Arête 9 :
         sommet 1 : 2
         sommet 2 : 5
         poids    : 2
Arête 10 :
         sommet 1 : 5
         sommet 2 : 7
         poids    : 6

Les arêtes du graphe :
Arête 0 : sommet1 : 0 , sommet2 : 1 , poids : 3.0
Arête 1 : sommet1 : 0 , sommet2 : 2 , poids : 1.0
Arête 2 : sommet1 : 1 , sommet2 : 4 , poids : 5.0
Arête 3 : sommet1 : 4 , sommet2 : 6 , poids : 4.0
Arête 4 : sommet1 : 6 , sommet2 : 7 , poids : 1.0
Arête 5 : sommet1 : 6 , sommet2 : 3 , poids : 2.0
Arête 6 : sommet1 : 7 , sommet2 : 3 , poids : 3.0
Arête 7 : sommet1 : 3 , sommet2 : 1 , poids : 3.0
Arête 8 : sommet1 : 1 , sommet2 : 2 , poids : 4.0
Arête 9 : sommet1 : 2 , sommet2 : 5 , poids : 2.0
Arête 10 : sommet1 : 5 , sommet2 : 7 , poids : 6.0

Sommet de référence (s >= 0) = ?
0
Les arêtes de l'arbre :
Arête 0 : sommet1 : 0 , sommet2 : 2 , poids : 1.0
Arête 1 : sommet1 : 6 , sommet2 : 7 , poids : 1.0
Arête 2 : sommet1 : 6 , sommet2 : 3 , poids : 2.0
Arête 3 : sommet1 : 2 , sommet2 : 5 , poids : 2.0
Arête 4 : sommet1 : 0 , sommet2 : 1 , poids : 3.0
Arête 5 : sommet1 : 3 , sommet2 : 1 , poids : 3.0
Arête 6 : sommet1 : 4 , sommet2 : 6 , poids : 4.0

[ij04115@saphyr:~/code_NAMOLARU_51704115]:mar. déc. 22$


**************************
TP7_8
L'utilisation de la commande javac sur saphyr n'est pas possible,
"Le programme « javac » peut être trouvé dans les paquets suivants...
Demandez à votre administrateur d'installer l'un d'entre eux".
Par conséquent, le test est effectué via la cmd du système d'exploitation Windows. 
Cependant, ces commandes devraient également fonctionner sur un système d'exploitation Linux.
**************************

C:\Users\user\Desktop\code_NAMOLARU_51704115>javac tp7_8/*.java

C:\Users\user\Desktop\code_NAMOLARU_51704115>java tp7_8.App graph.txt astar
Done! Using A*:
        Number of nodes explored: 4130
        Total time of the path: 302.6101730552669

C:\Users\user\Desktop\code_NAMOLARU_51704115>java tp7_8.App graph.txt dijkstra
Done! Using Dijkstra:
        Number of nodes explored: 4156
        Total time of the path: 302.6101730552669

C:\Users\user\Desktop\code_NAMOLARU_51704115>

**************************
projetLabyrinthe
L'utilisation de la commande javac sur saphyr n'est pas possible,
"Le programme « javac » peut être trouvé dans les paquets suivants...
Demandez à votre administrateur d'installer l'un d'entre eux".
Par conséquent, le test est effectué via la cmd du système d'exploitation Windows. 
Cependant, ces commandes devraient également fonctionner sur un système d'exploitation Linux.
**************************

C:\Users\user\Desktop\code_NAMOLARU_51704115>javac projetLabyrinthe/*.java

C:\Users\user\Desktop\code_NAMOLARU_51704115>java projetLabyrinthe.labyrinthe
3
4 5
....D
.....
.....
F...S
4 4
...D
....
....
F..S
3 4
###D
####
S..F
Y
N
N
