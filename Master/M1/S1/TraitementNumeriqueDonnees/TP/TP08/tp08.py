#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from skimage import io
from skimage import img_as_float
import matplotlib.pyplot as plt
import numpy as np
import scipy.fftpack as fft #autre librairie fft utilisable
import tp07

img = io.imread('./chimpanze.jpg')

plt.figure()
io.imshow(img)

Fft2Img = fft.fft2(img)
MagnImg = np.abs(Fft2Img)
# PhaImg = np.arctan(np.imag(FftImg)/np.real(FftImg))
PhasImg = np.angle(Fft2Img)
plt.figure()
plt.plot(MagnImg)
io.imshow(MagnImg)
plt.figure()
plt.plot(PhasImg)
io.imshow(MagnImg)

# plt.figure()
Ifft2Img = fft.ifft2(MagnImg * np.exp(1j + PhasImg))
# io.imshow(Ifft2Img, cmap = 'gray')

plt.figure()
filtre = np.array([[1, 3, 1], [3, 5, 3], [1, 3, 1]])
imgConvo = tp07.convolution(img, filtre)
plt.imshow(imgConvo)

plt.figure()
filtre = np.array([[0, -1, 0], [-1, 5, -1], [0, -1, 0]])
imgConvo = tp07.convolution(img, filtre)
plt.imshow(imgConvo)



io.show()