# -*- coding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt
from scipy.io.wavfile import read
from scipy.fftpack import fft
from scipy import signal

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

def transformmeeFourierSpectreDB4Signaux(N, f1, fe1, f2, fe2, f3, fe3, f4, fe4):
    t = np.linspace(0,(N-1)/fs, N)
    pi = np.pi
    x = np.zeros(N)
    x = np.sin(2*pi*f1*t)
    X = np.fft.fft(x)
    A = np.abs(X/N)
    An = A/A.max()
    freq = np.linspace(0, fe1, N)
    spectre_db = 20*np.log10(An)
    plt.subplot(221)
    plt.plot(freq, spectre_db, 'r')
    plt.xlabel('Frequence')
    plt.ylabel('AdB')
    plt.axis([0, fe1/2, spectre_db[0:128].min(), spectre_db[0:128].max()])

    t = np.linspace(0,(N-1)/fs, N)
    pi = np.pi
    x = np.zeros(N)
    x = np.sin(2*pi*f2*t)
    X = np.fft.fft(x)
    A = np.abs(X/N)
    An = A/A.max()
    freq = np.linspace(0, fe2, N)
    spectre_db = 20*np.log10(An)
    plt.subplot(222)
    plt.plot(freq, spectre_db, 'r')
    plt.xlabel('Frequence')
    plt.ylabel('AdB')
    plt.axis([0, fe2/2, spectre_db[0:128].min(), spectre_db[0:128].max()])

    t = np.linspace(0,(N-1)/fs, N)
    pi = np.pi
    x = np.zeros(N)
    x = np.sin(2*pi*f3*t)
    X = np.fft.fft(x)
    A = np.abs(X/N)
    An = A/A.max()
    freq = np.linspace(0, fe3, N)
    spectre_db = 20*np.log10(An)
    plt.subplot(223)
    plt.plot(freq, spectre_db, 'r')
    plt.xlabel('Frequence')
    plt.ylabel('AdB')
    plt.axis([0, fe3/2, spectre_db[0:128].min(), spectre_db[0:128].max()])

    t = np.linspace(0,(N-1)/fs, N)
    pi = np.pi
    x = np.zeros(N)
    x = np.sin(2*pi*f4*t)
    X = np.fft.fft(x)
    A = np.abs(X/N)
    An = A/A.max()
    freq = np.linspace(0, fe4, N)
    spectre_db = 20*np.log10(An)
    plt.subplot(224)
    plt.plot(freq, spectre_db, 'r')
    plt.xlabel('Frequence')
    plt.ylabel('AdB')
    plt.axis([0, fe4/2, spectre_db[0:128].min(), spectre_db[0:128].max()])
    plt.grid()
    plt.show()

(fs,x) = read('./Sons/Oiseaux/fauvette.wav')
print("Taille (en nombre d'échantillons): ", len(x))
print("Fréquence d'échantillonnage : ", fs)
print("Durée du signal (en secondes), voir valeur maximale : ", x)
f, t, Sxx = signal.spectrogram(x, fs)
# plt.pcolormesh(t, f, np.log(Sxx))

transformmeeFourierSpectreDB(512, len(f), fs) #Le pic du spectre correspond au moment où le son est le plus élevé

(fs,x) = read('./voixBruit.wav')
print("Taille (en nombre d'échantillons): ", len(x))
print("Fréquence d'échantillonnage : ", fs)
print("Durée du signal (en secondes), voir valeur maximale : ", x)
f, t, Sxx = signal.spectrogram(x, fs)

(fs2,x2) = read('./voixPasseBas.wav')
print("Taille (en nombre d'échantillons): ", len(x2))
print("Fréquence d'échantillonnage : ", fs2)
print("Durée du signal (en secondes), voir valeur maximale : ", x2)
f2, t2, Sxx2 = signal.spectrogram(x2, fs2)

(fs3,x3) = read('./voixPasseHaut.wav')
print("Taille (en nombre d'échantillons): ", len(x))
print("Fréquence d'échantillonnage : ", fs3)
print("Durée du signal (en secondes), voir valeur maximale : ", x3)
f3, t3, Sxx3 = signal.spectrogram(x3, fs3)

(fs4,x4) = read('./voixAmelioree.wav')
print("Taille (en nombre d'échantillons): ", len(x4))
print("Fréquence d'échantillonnage : ", fs4)
print("Durée du signal (en secondes), voir valeur maximale : ", x4)
f4, t4, Sxx4 = signal.spectrogram(x4, fs4)

transformmeeFourierSpectreDB4Signaux(512, len(f), fs, len(f2), fs2, len(f3), fs3, len(f4), fs4)