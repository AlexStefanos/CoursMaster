# -*- coding: utf-8 -*-
"""
Created on Mon Oct  9 15:05:21 2017

@author: sylviegibet
"""
#%% Data
import pandas as pd
import numpy as np
# LECTURE DES DONNEES
#1) Lire les données "users" dans un DataFrame Pandas et afficher les 5 premières valeurs

unames = ['user_id', 'gender', 'age', 'occupation', 'zip']
users = pd.read_csv('ml-1m/users.dat', sep='::', header=None, names=unames, engine='python')

users.head()

#2) Lire les données "ratings" dans un DataFrame Pandas et afficher les 10 premières valeurs

rnames = ['user_id', 'movie_id', 'rating', 'timestamp']
ratings = pd.read_csv('ml-1m/ratings.dat', sep='::', header=None, names=rnames, engine='python')

ratings.head(10)

# 3) Lire les données "movies" dans un DataFrame Pandas et afficher les 10 premières valeurs

mnames = ['movie_id', 'title', 'genres']
movies = pd.read_csv('ml-1m/movies.dat', sep='::', header=None, names=mnames, engine='python', encoding="ISO-8859-1")
movies.head(10)

#4) Fusionner les données des 3 fichiers dans un seul DataFrame

data = pd.merge(pd.merge(ratings, users), movies)
data.head()

