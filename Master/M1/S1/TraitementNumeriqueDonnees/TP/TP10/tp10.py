#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import numpy as np
import csv

data = csv.reader(open('dataset.csv', 'rt'))
x0 = []
y0 = []
for i in data:
    x0.append(i[0])
    y0.append(i[1])
x = np.array(x0)
y = np.array(y0)
sumX = 0
sumY = 0

#exo 2 : a = 1.193  b = -3.896
#exo 3 : la fonction coût est donnée par l'équation(8)
#quand le coût avoisine 0, on arrête l'algo
#on essaie plusieurs coûts de manière eurystique et on compare le nb d'opérations/le temps de calcul

def compute_partial_derivates(a, b):
    return(a, b)