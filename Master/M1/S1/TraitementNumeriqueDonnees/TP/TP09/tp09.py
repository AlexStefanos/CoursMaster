#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import pandas as pd
import numpy as np

#Lecture des données
#Question 1
unames = ['user_id', 'gender', 'age', 'occupation', 'zip']
users = pd.read_csv('ml-1m/users.dat', sep='::', header=None, names=unames, engine='python')
# print(users.head())


#Question 2
rnames = ['user_id', 'movie_id', 'rating', 'timestamp']
ratings = pd.read_csv('ml-1m/ratings.dat', sep='::', header=None, names=rnames, engine='python')
# print(ratings.head(10))


#Question 3
mnames = ['movie_id', 'title', 'genres']
movies = pd.read_csv('ml-1m/movies.dat', sep='::', header=None, names=mnames, engine='python', encoding="ISO-8859-1")
# print(movies.head(10))


#Question 4
data = pd.merge(pd.merge(ratings, users), movies)
# print(data.head())


#Exploration des données
#Question 1
# print(np.sum(data.rating > 4.5))
# print(np.sum(data.rating[data.gender == 'F'] > 4.5))
# print(np.sum(data.rating[data.gender == 'M'] > 4.5))


#Question 2
# print(np.sum((data.rating[data.gender == 'F'] > 4.5) / (data.rating[data.gender == 'F'])))


#Question 3
best_film = data.groupby('movie_id')['rating'].mean().nlargest(10)
# print(best_film)


#Question 4
# print(np.sum(data[(data.gender == 'M') & (data.age >= 30)].groupby('movie_id', axis=0)['rating'].median() >= 4.5))
# print(np.sum(data[(data.gender == 'F') & (data.age >= 30)].groupby('movie_id', axis=0)['rating'].median() >= 4.5))


#Question 5
d1 = data.groupby('movie_id')['rating'].count().head(5)
# print(d1)
d2 = data.groupby('movie_id')['rating'].mean().head(5)
# print(d2)
d3 = pd.concat([d1, d2], axis = 1)
d3.columns = ['mean_rating', 'count_rating']
# print(d3)


#Question 6
d4 = d3.sort_values('mean_rating', axis = 0, ascending = False)
# print(d4.head(2).get('title'))


#Visualisation des données
#Question 1
data.rating.hist(bins = 5, align = 'left', range = [1, 6])
# print(data)


#Question 2
data.groupby('movie_id', axis = 0)['rating'].count().hist(bins = 10)
# print(data)


#Question 3
data.groupby('movie_id', axis = 0)['rating'].mean().hist(bins = 10)
# print(data)


#Question 4
data[(data.gender == 'M')].groupby('movie_id')['rating'].mean().hist(bins = 10)
print(data)
data[(data.gender == 'F')].groupby('movie_id')['rating'].mean().hist(bins = 10)
print(data)
#Non, la distribution ne dépend pas du sexe des utilisateurs