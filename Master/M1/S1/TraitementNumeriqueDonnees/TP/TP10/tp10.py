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

def compute_partial_derivates(a, b):
    y = np.empty_like(a)
    y[:1] = np.diff(x, axis = 0) / b
    y[-1] = -x[-1] / b
    return(a, b)

#j'ai fait une erreur avec mon github, j'ai perdu la dernière version de mon fichier. Il faudrait que je refasse le TP.