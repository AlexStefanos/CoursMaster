#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import csv
from sklearn.metrics import accuracy_score
from sklearn.metrics import classification_report
from sklearn.metrics import confusion_matrix

x = csv.reader(open('parkinsons.data.txt', 'rt'))
y = csv.reader(open('parkinsons.names.txt', 'rt'))
