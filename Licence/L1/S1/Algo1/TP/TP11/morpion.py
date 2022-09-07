#-*- coding: utf-8 -*-
import os


def grille_vide(g00c,g01c,g02c,g10c,g11c,g12c,g20c,g21c,g22c):
    grille=[[" "]]
    g00=grille[0][0]=" "
    g01=grille[0][0]=" "
    g02=grille[0][0]=" "
    g10=grille[0][0]=" "
    g11=grille[0][0]=" "
    g12=grille[0][0]=" "
    g20=grille[0][0]=" "
    g21=grille[0][0]=" "
    g22=grille[0][0]=" "
    print("   ","a  ","b  ","c") 
    print("  ","-----------"," ")
    print("1","|",g00c,"|",g01c,"|",g02c,"|")
    print("  ","-----------"," ")
    print("2","|",g10c,"|",g11c,"|",g12c,"|")
    print("  ","-----------"," ")
    print("3","|",g20c,"|",g21c,"|",g22c,"|")
    print("  ","-----------"," ")
    
def verif_vide(g00,g01,g02,g10,g11,g12,g20,g21,g22):
    liste=[[" "]]
    grille=[[" "]]
    g00=grille[0][0]=" "
    g01=grille[0][0]=" "
    g02=grille[0][0]=" "
    g10=grille[0][0]=" "
    g11=grille[0][0]=" "
    g12=grille[0][0]=" "
    g20=grille[0][0]=" "
    g21=grille[0][0]=" "
    g22=grille[0][0]=" "
    grille_vide(g00,g01,g02,g10,g11,g12,g20,g21,g22)
    if liste == g00 :
        ag00=True
    else :
        ag00=False
    if liste == g01 :
        ag01=True
    else :
        ag01=False
    if liste == g02 :
        ag02=True
    else :
        ag02=False
    if liste == g10 :
        ag10=True
    else :
        ag10=False
    if liste == g11 :
        ag11=True
    else :
        ag11=False
    if liste == g12 :
        ag12=True
    else :
        ag12=False
    if liste == g20 :
        ag20=True
    else :
        ag20=False
    if liste == g21 :
        ag21=True
    else :
        ag21=False
    if liste == g22 :
        ag22=True
    else :
        ag22=False
    if ag00 == False or ag01==False or ag02==False or ag10==False or ag11==False or ag12==False or ag20==False or ag21==False or ag22==False :
        return False
    else :
        return True

def verif_vide2():
    grille=[[" "]]
    g00=grille[0][0]=" "
    g01=grille[0][0]=" "
    g02=grille[0][0]=" "
    g10=grille[0][0]=" "
    g11=grille[0][0]=" "
    g12=grille[0][0]=" "
    g20=grille[0][0]=" "
    g21=grille[0][0]=" "
    g22=grille[0][0]=" "
    if verif_vide(g00,g01,g02,g10,g11,g12,g20,g21,g22) == False :
        print("Certaines cases ne sont pas vides , nous recommançons donc la partie")
    else :
        print("Les cases sont toutes vides, nous pouvons commencer la partie")

verif_vide2()
