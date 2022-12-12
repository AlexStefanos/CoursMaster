#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import csv
from sklearn.metrics import accuracy_score
from sklearn.metrics import classification_report
from sklearn.metrics import confusion_matrix

dataParkinsons = csv.reader(open('parkinsons.data.txt', 'rt'))
namesParkinsons = csv.reader(open('parkinsons.names.txt', 'rt'))
x = []
y = []
cpt = 0
for i in dataParkinsons:
    if(cpt < 100):
        x.append(i[0])
    else:
        y.append(i[0])
    cpt += 1

def getIndice(p):
    cpt = 0
    result = 0
    for i in dataParkinsons:
        if(i == p):
            result = cpt
        cpt += 1
    return(result)

def getPatient(indice):
    patient = i[0]
    for i in dataParkinsons:
        if(i == indice):
            result = patient
    return(patient)


def distance(x, data1, data2):
    indice1 = 0
    indice2 = 0
    cpt = 0
    for i in dataParkinsons:
        if(i == data1):
            indice1 = cpt
        if(i == data2):
            indice2 = cpt
        cpt += 1
    if(indice1 >= indice2):
        result = indice1 - indice2
    else:
        result = indice2 - indice1
    return(result)

def getNeighbors(p_test, k):
    indice = getIndice(p_test)
    listeVoisins = []
    for i in range(k):
        listeVoisins.append(getPatient(indice-i))
        listeVoisins.append(getPatient(indice+i))
    return(listeVoisins)

#je n'ai pas bien compris comment repartir les patients dans deux différentes classes dans ce cas