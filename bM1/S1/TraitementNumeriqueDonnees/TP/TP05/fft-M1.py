# -*- coding: utf-8 -*-
"""
Created on Wed Apr 19 14:31:07 2017

@author: sylviegibet

"""
#%%
# imports 
import numpy as np
#from scipy.fftpack import fft
import matplotlib.pyplot as plt 

#%% Transformée de Fourier du signal impulsionnel

n = 20

# definition de a
a = np.zeros(n)
a[1] = 1

# visualisation de a
# on ajoute a droite la valeur de gauche pour la periodicite
plt.subplot(311)
#plt.stem( np.append(a, a[0]) )
plt.stem( a)

# calcul de A
A = np.fft.fft(a)

# visualisation de A
plt.subplot(312)
plt.plot(np.real(A))
plt.ylabel("partie reelle")

plt.subplot(313)
plt.plot(np.imag(A))
plt.ylabel("partie imaginaire")


#%% Transformée de Fourier de la fonction créneau

n = 20

# definition de a
a = np.zeros(n)
for i in range(5,n):
    a[i] = 1

# visualisation de a
# on ajoute a droite la valeur de gauche pour la periodicite
plt.subplot(311)
plt.stem( np.append(a, a[0]) )

# calcul de A
A = np.fft.fft(a)

# visualisation de A
# on ajoute a droite la valeur de gauche pour la periodicite
B = np.append(A, A[0])
plt.subplot(312)
plt.plot(np.real(B))
plt.ylabel("partie reelle")

plt.subplot(313)
plt.plot(np.imag(B))
plt.ylabel("partie imaginaire")


#%% 
# Fonction de génération d'un signal sinusoidal échantillonné

def genSin (N,f,fs) :
    #n = np.arange(N)
    #t = n / float(fs)   
    t = np.linspace(0,(N-1)/fs, N) #start = 0, stop = (N-1)*Ts, N points
    pi = np.pi
    x=np.sin(2*pi*f*t);
    return(x)
    
#%% Exemple d'application de GenSin 

fe = 256                   # fréquence d'échantillonnage
N = 256                    # nombre de d'échantillons synthétisés (multiple de 2)
f = 16                    # fréquence de la sinusoide


t = np.linspace(0,(N-1)/fe,N)   # échelle temporelle
x = np.zeros(N)      # 
x = genSin(N,f,fe)
plt.figure(1)
plt.figure(1)
plt.plot(t,x)
#%% 
# Transformée de Fourier du signal x --> X

X = np.fft.fft(x)               # spectre du signal
Re = np.real(X)                 # partie reelle de S
Im = np.imag(X)                 # partie imaginaire de S
A = np.abs(X/N)                 # amplitude du spectre
An = A/A.max()                  # amplitude normalisée
Ph = np.angle(X/N)              # phase normalisée

#%%
### Visualisations de la transformée de Fourier: parties réelle et imaginaire

plt.figure(2)
freq =np.linspace(0,fe,N)	
plt.plot(freq,Re,'g')
plt.xlabel('fréquence')
plt.ylabel('Réelle')
plt.axis([0,fe,Re.min(),Re.max()])
plt.grid()

plt.figure(3)   
plt.plot(freq,Im,'g')
plt.xlabel('fréquence')
plt.ylabel('Imaginaire')
plt.axis([0,fe,Im.min(),Im.max()])
plt.grid()

#%%
# Visualisations du spectre : amplitude normalisée et phase

plt.plot(freq,An,'r')
plt.xlabel('frequence')
plt.ylabel('AdB')
#plt.axis([0,fe/2,spectre_db.min(),spectre_db.max()])
# echelle des axes de 0 à fe/2
plt.axis([0,fe/2,An[0:128].min(),An[0:128].max()])
plt.grid()

# Pour ce signal sinusoidal,  visualiser la phase n'a pas de sens

# plt.figure(figsize=(10,4))
# plt.plot(freq,Ph,'r')
# plt.xlabel('fréquence')
# plt.ylabel('Phase')
# plt.axis([0,fe,0,Ph.max()])
# plt.grid()
#%%
# Visualisations du spectre en dB

spectre_db = 20*np.log10(An)
plt.plot(freq,spectre_db,'r')
plt.xlabel('frequence')
plt.ylabel('AdB')
#plt.axis([0,fe/2,spectre_db.min(),spectre_db.max()])
# echelle des axes de 0 à fe/2
plt.axis([0,fe/2,spectre_db[0:128].min(),spectre_db[0:128].max()])
plt.grid()


#%%
fe = 10
N = 1024

echantillons = genSin(N,1,fe)+0.5*genSin(N,2,fe)+0.25*genSin(N,3,fe)+0.01*genSin(N,4,fe)
plt.figure(1)
t=np.arange(1024)
plt.figure(1)
plt.plot(t[0:128],echantillons[0:128])

#### Transformée de Fourier

TFD = np.fft.fft(echantillons)  # spectre du signal
A = np.abs(TFD/N)               # amplitude du spectre
An = A/A.max()                  # amplitude normalisée
P = np.angle(TFD/N)             # phase normalisée
Re = np.real(TFD)               # partie reelle de TFD
Im = np.imag(TFD)               # partie imaginaire TFD
AdB = 20*np.log10(An)        # amplitude en dB

#%%
### Visualisation de la transformée de Fourier  

plt.figure(2)
freq =np.linspace(0,fe,N)	
plt.plot(freq,Re,'g')
plt.xlabel('fréquence')
plt.ylabel('Réelle')
plt.axis([0,fe/2,Re.min(),Re.max()])
plt.grid()

plt.figure(3)   
plt.plot(freq,Im,'g')
plt.xlabel('fréquence')
plt.ylabel('Imaginaire')
plt.axis([0,fe/2,Im.min(),Im.max()])
plt.grid()

plt.figure(4)   
plt.plot(freq,AdB,'r')
plt.xlabel('f')
plt.ylabel('AdB')
plt.axis([0,fe/2,AdB[0:512].min(),AdB[0:512].max()])
plt.grid()