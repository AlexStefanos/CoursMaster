#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import pandas as pd
import numpy as np

unames = ['user_id', 'gender', 'age', 'occupation', 'zip']
users = pd.read_csv('ml-1m/users.dat', sep='::', header=None, names=unames, engine='python')
# print(users.head())


rnames = ['user_id', 'movie_id', 'rating', 'timestamp']
ratings = pd.read_csv('ml-1m/ratings.dat', sep='::', header=None, names=rnames, engine='python')
# print(ratings.head(10))


mnames = ['movie_id', 'title', 'genres']
movies = pd.read_csv('ml-1m/movies.dat', sep='::', header=None, names=mnames, engine='python', encoding="ISO-8859-1")
# print(movies.head(10))


data = pd.merge(pd.merge(ratings, users), movies)
# print(data.head())


# print(np.sum(data.rating > 4.5))
# print(np.sum(data.rating[data.gender == 'F'] > 4.5))
# print(np.sum(data.rating[data.gender == 'M'] > 4.5))


# print(np.sum((data.rating[data.gender == 'F'] > 4.5) / (data.rating[data.gender == 'F'])))


best_film = data.groupby('movie_id')['rating'].mean().nlargest(10)
# print(best_film)


# print(np.sum(data[(data.gender == 'M') & (data.age >= 30)].groupby('movie_id', axis=0)['rating'].median() >= 4.5))
# print(np.sum(data[(data.gender == 'F') & (data.age >= 30)].groupby('movie_id', axis=0)['rating'].median() >= 4.5))


d1 = data.groupby('movie_id')['rating'].count().head(5)
# print(d1)
d2 = np.sum(d1) / d1.count()
# print(d2)
d3 = pd.concat(d1, d2)
print(d3)