#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Nov  4 17:04:55 2018

@author: sylviegibet
"""

from skimage import io
from skimage import img_as_float
import matplotlib.pyplot as plt
#import numpy as np
#from scipy.fftpack import fft2


plt.figure(1)
lena_image = io.imread('./lena.png')    #lecture d'une image
lenagrey = lena_image[:,:, 0] 
# ou bien :
#lenagrey = io.imread('./DATA/Images/lena.png', as_grey=True)
print("lena : ", lena_image.shape)      # taille de l'image
print("lena : ", lenagrey.shape)        # taille de l'image en noir et blanc
print(lena_image.dtype)
#print(lenagrey.dtype)                   # type de l'image
io.imshow(lena_image)                   # affichage de l'image couleur
plt.figure(2)
#io.imshow(lena_image[:,:, 0])           # affichage de l'image en noir et blanc
io.imshow(lenagrey)

# conversion du type de l'image : on passe de utf8 à float64
# en flottant, chaque pixel est normalisé entre 0 et 1
lena = img_as_float(lenagrey)
print(lena.dtype)

# zoom de l'image



# sauvegarde de l'image zoomée



