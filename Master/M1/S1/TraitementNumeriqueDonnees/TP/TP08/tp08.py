#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from skimage import io
from skimage import img_as_float
import matplotlib.pyplot as plt
import numpy as np
import scipy.fftpack as fft #autre librairie fft utilisable
import tp07
import scipy.misc as misc

img = io.imread('./chimpanze.jpg')

plt.figure()
io.imshow(img)

Fft2Img = fft.fft2(img)
MagnImg = np.abs(Fft2Img)
PhasImg = np.angle(Fft2Img)
plt.figure()
plt.plot(MagnImg)
plt.title("Magnitude")
io.imshow(MagnImg)
plt.figure()
plt.plot(PhasImg)
plt.title("Phase")
io.imshow(MagnImg)

plt.figure()
Ifft2Img = fft.ifft2(MagnImg * np.exp(1j + PhasImg)).real
io.imshow(Ifft2Img)

plt.figure()
filtre = np.array([[1, 3, 1], [3, 5, 3], [1, 3, 1]])
plt.title("Convolution n°1")
imgConvo = tp07.convolution(img, filtre)
io.imshow(imgConvo)

plt.figure()
filtre = np.array([[0, -1, 0], [-1, 5, -1], [0, -1, 0]])
plt.title("Convolution n°2")
imgConvo2 = tp07.convolution(img, filtre)
io.imshow(imgConvo2)

im_fft = fft.fft2(img)
plt.plot(im_fft)
im_fft_bas = np.copy(im_fft)
for i in range(126):
    im_fft_bas[i] = 0
im_fft_haut = np.copy(im_fft)
for i in range(125, 256):
    im_fft_haut[i] = 255

plt.figure()
plt.plot(im_fft_bas)
plt.title("Filtre Bas")
im_fft_bas_magn = np.abs(im_fft_bas)
im_fft_bas_phas = np.angle(im_fft_bas)
im_fft_final_bas = fft.ifft2(im_fft_bas_magn * np.exp(1j * im_fft_bas_phas)).real
io.imshow(im_fft_final_bas)

plt.figure()
plt.plot(im_fft_haut)
plt.title("Filtre Haut")
im_fft_haut_magn = np.abs(im_fft_haut)
im_fft_haut_phas = np.angle(im_fft_haut)
im_fft_final_haut = fft.ifft2(im_fft_haut_magn * np.exp(1j * im_fft_haut_phas)).real
io.imshow(im_fft_final_haut)

im_salt = img
saltValue = 95
noise = np.random.randint(saltValue + 1, size=(256, 256))
index = np.where(noise == 0)
A = index[0]
B = index[1]
im_salt[A, B] = 0
index = np.where(noise == saltValue)
A = index[0]
B = index[1]
im_salt[A, B] = 255
io.imshow(im_salt)

plt.figure()
filtre = np.array([[1/9, 1/9, 1/9], [1/9, 1/9, 1/9], [1/9, 1/9, 1/9]])
im_seuillage = tp07.convolution(im_salt, filtre)
io.imshow(im_seuillage)

io.show()