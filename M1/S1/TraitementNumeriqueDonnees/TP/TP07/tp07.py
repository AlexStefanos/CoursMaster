#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from skimage import io
from skimage import img_as_float
import matplotlib.pyplot as plt
import matplotlib.image as pltimg
import numpy as np

img = io.imread('./chimpanze.jpg')

plt.figure()
imgSliced = img[50:100, 50:100]
io.imshow(imgSliced)
io.imsave('./slicing.png', imgSliced)


plt.figure()
imgCarre = np.zeros(np.shape(img))
for i in range(50, 200):
    for j in range(50, 200):
        imgCarre[i, j] = img[i, j]
io.imshow(imgCarre)


def echantillonnage(img, resolution):
    shape = np.shape(img)
    divi = int(shape[0]/resolution)
    echant = img[0:shape[0]:divi, 0:shape[1]:divi]
    return(echant)

plt.figure()
plt.subplot(231)
img128 = echantillonnage(img, 128)
io.imshow(img128)

plt.subplot(232)
img64 = echantillonnage(img, 64)
io.imshow(img64)

plt.subplot(233)
img32 = echantillonnage(img, 32)
io.imshow(img32)

plt.subplot(234)
img16 = echantillonnage(img, 16)
io.imshow(img16)


def histogramme(img, norme):
    hist = np.zeros(256)
    shape = np.shape(img)
    for i in range(0, shape[0]):
        for j in range(0, shape[1]):
            hist[img[i, j]] += 1
    for i in range(256):
        hist[i] = hist[i]/norme
    return(hist)

plt.figure()
imgHisto0 = histogramme(img, 1)
plt.plot(imgHisto0)

plt.figure()
imgHisto64 = histogramme(img, 64)
plt.plot(imgHisto64)

plt.figure()
imgHistoSizeImg = histogramme(img, img.size)
plt.plot(imgHistoSizeImg)


def convolution(img, filtre):
    shape = np.shape(img)
    convo = np.copy(img)
    shapeFiltre = np.shape(filtre)
    yShape = int((shapeFiltre[1] - 1)/2)
    xShape = int((shapeFiltre[0] - 1)/2)
    for x in range(0, shape[0]):
        for y in range(0, shape[1]):
            somme = 0
            xTmp = 0
            yTmp = 0
            for i in range((x - xShape), (x + xShape)):
                if((i >= 0) and (i < shape[0]) and (xTmp <= shapeFiltre[0])):
                    yTmp = 0
                    for j in range((y - yShape), (y + yShape)):
                        if((j >= 0) and (j < shape[1]) and (yTmp <= shapeFiltre[1])):
                            somme += img[i, j] * filtre[xTmp, yTmp]
                        yTmp += 1
                xTmp += 1
        convo[i,j] = somme
    return(convo)

plt.figure()
filtre = np.array([[-1, -1, -1], [-1, 8, -1], [-1, -1, -1]])
imgConvo = convolution(img, filtre)
io.imshow(imgConvo)

plt.figure()
filtre = np.array([[1/16, 1/8, 1/16], [1/8, 1/4, 1/8], [1/16, 1/8, 1/16]])
imgConvo = convolution(img, filtre)
io.imshow(imgConvo)

plt.figure()
filtreMoyenneur = np.array([[1/9, 1/9, 1/9], [1/9, 1/9, 1/9], [1/9, 1/9, 1/9]])
imgFiltreMoyenneur = convolution(img, filtreMoyenneur)
io.imshow(imgFiltreMoyenneur)

io.show()