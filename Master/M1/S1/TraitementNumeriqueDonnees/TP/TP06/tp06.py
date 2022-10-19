# -*- coding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt
from scipy.io.wavfile import read
from scipy.fftpack import fft
from scipy import signal

(fs,x) = read('./Sons/Oiseaux/fauvette.wav')
print("Taille (en nombre d'échantillons): ", len(x))
print("Fréquence d'échantillonnage : ", fs)
print("Durée du signal (en secondes), voir valeur maximale : ", x)
f, t, Sxx = signal.spectrogram(x, fs)
plt.pcolormesh(t, f, np.log(Sxx))

def transformmeeFourierSpectreDB(N, f, fe):
    t = np.linspace(0,(N-1)/fs, N)
    pi = np.pi
    x = np.zeros(N)
    x = np.sin(2*pi*f*t)
    X = np.fft.fft(x)
    A = np.abs(X/N)
    An = A/A.max()
    freq = np.linspace(0, fe, N)
    spectre_db = 20*np.log10(An)
    plt.plot(freq, spectre_db, 'r')
    plt.xlabel('Frequence')
    plt.ylabel('AdB')
    plt.axis([0, fe/2, spectre_db[0:128].min(), spectre_db[0:128].max()])
    plt.grid()
    plt.show()

transformmeeFourierSpectreDB(512, len(f), fs) #Le pic du spectre correspond au moment où le son est le plus élevé