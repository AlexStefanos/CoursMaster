#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import matplotlib.pyplot as plt
import numpy as np
import sklearn as sk
from sklearn import datasets
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score
from sklearn.metrics import classification_report, f1_score
from sklearn.metrics import confusion_matrix
from sklearn.neighbors import KNeighborsClassifier

#Question 1, 2, 3, 4
digits = datasets.load_digits()
x, y = digits.data, digits.target
print(x[1, :])
print(y[[1, 10, 20]])
plt.figure()
plt.imshow(np.reshape(x[1, :], (8, 8)))

#Question 5
plt.figure()
plt.imshow(np.reshape(x[1, :], (8, 8)), cmap = 'gray', aspect = 'equal', interpolation = 'nearest')
plt.title('Le chiffre numéro %s est un %s' % (1, y[1]))

#Question 6
x_train, x_test, y_train, y_test = train_test_split(x, y, train_size=0.8, random_state=42)
print("Nb d'échantillons d'apprentissage : {}".format(x_train.shape[0]))
print("Nb d'échantillons de validation : {}".format(x_test.shape[0]))

#Question 7
plt.figure()
plt.hist(y_train, bins = 10)
plt.hist(y_test, bins = 10)

#Question 8
clf_logit = LogisticRegression(solver = 'newton-cg', max_iter = 100, multi_class = 'auto')
clf_logit.fit(x_train, y_train)
y_pred_logit = clf_logit.predict(x_test)

#Question 9
print("Accuracy logit : ", accuracy_score(y_test, y_pred_logit))
print("Accuracy bis logit: : ", np.mean(y_test == y_pred_logit)) # mesure d’erreur 0/1
print("Le classifieur logit propose une bonne prédiction dans %s %% des cas." % (100 * accuracy_score(y_test, y_pred_logit)))

#Question 10
print(classification_report(y_test, y_pred_logit))

#Question 11
conf_mat_logit = confusion_matrix(y_test, y_pred_logit)
print("Matrice de confusion pour le classifieur logit : ")
print(y_pred_logit)

#Question 12, 13

clf_knn = KNeighborsClassifier(n_neighbors = 1)
# print(clf_knn.predict([[0.9]]))

plt.show()