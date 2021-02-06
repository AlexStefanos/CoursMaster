# -*- coding: utf-8 -*-

M = [mot[:-1] for mot in open('mots.txt')]
M.sort()
D = dict()
for mot in M :
	T = tuple(sorted(mot))
	if T in D :
		D[T] = D[T] + 1
	else :
		D[T] = 1
maxi=max([D[T] for T in D])
print(maxi)
maxi2=max(D,Key=D.get)
print(maxi2)
for T in D :
	if D[T] == m
	print("pour T =" + str(T),", les mots sont :")
	for mot in M :
		U = tuple(sorted(mot))
		print(mot)
