
def genSin(n, f, fs) :
    m = np.arange(n)
    t = n / float(fs)   
    t = np.linspace(0,(m-1)/fs, m) #start = 0, stop = (m-1)*Ts, n points
    pi = np.pi
    x = np.sin(2*pi*f*t);
    return(x)
    
#%% Exemple d'application de GenSin 

def appliGenSin(n, f, fe):
    t = np.linspace(0, (n-1)/fe, n)
    x = np.zeros(n)
    x = genSin(n, f, fe)
    plt.figure(1)
    plt.figure(1)
    plt.plot(t, x)
    plt.show()

appliGenSin(256, 16, 256)
#%% 
# Transformée de Fourier du signal x --> X

def transformeeFourierPartiesReellesImaginaires(n, x, fe):
    y = np.fft.fft(n)
    re = np.real(y)
    im = np.imag(y)
    plt.figure(2)
    freq = np.linspace(0, fe, x)	
    plt.plot(freq, re, 'g')
    plt.xlabel('Fréquence')
    plt.ylabel('Réelle')
    plt.axis([0, fe, re.min(), re.max()])
    plt.grid()
    plt.figure(3)   
    plt.plot(freq, im, 'g')
    plt.xlabel('Fréquence')
    plt.ylabel('Imaginaire')
    plt.axis([0, fe, im.min(), im.max()])
    plt.grid()


def transformeeFourierAmplitudeNormaliseePhase(n, x, fe):
    y = np.fft.fft(n)
    a = np.abs(y/x)
    an = a/a.max()
    plt.figure(2)
    freq = np.linspace(0, fe, x)
    plt.plot(freq, an, 'r')
    plt.xlabel('frequence')
    plt.ylabel('AdB')
    plt.axis([0, fe/2, an[0:128].min(), an[0:128].max()])
    plt.grid()



def transformeeFourierSpectreDB(n, x, fe):
    y = np.fft.fft(n)
    a = np.abs(y/x)
    an = a/a.max()
    spectredB = 20*np.log10(an)
    freq = np.linspace(0, fe, x)
    plt.plot(freq, spectredB, 'r')
    plt.xlabel('Frequence')
    plt.ylabel('AdB')
    plt.axis([0, fe/2, spectredB[0:128].min(), spectredB[0:128].max()])
    plt.grid()



def transformeeFourierDiscrete(n, x, fe):
    y = np.fft.fft(n)
    re = np.real(y)
    im = np.imag(y)
    echantillons = genSin(x, 1, fe)+0.5*genSin(x, 2, fe)+0.25*genSin(x, 3, fe)+0.01*genSin(x, 4, fe)
    plt.figure(1)
    t = np.arange(1024)
    plt.figure(1)
    plt.plot(t[0:128], echantillons[0:128])
    tfd = np.fft.fft(echantillons)
    a = np.abs(tfd/x)
    an = a/a.max()
    p = np.angle(tfd/x)
    re = np.real(tfd)
    im = np.imag(tfd)
    adB = 20*np.log10(an)
    plt.figure(2)
    freq =np.linspace(0, fe, x)	
    plt.plot(freq, re,'g')
    plt.xlabel('Fréquence')
    plt.ylabel('Réelle')
    plt.axis([0, fe/2, re.min(), re.max()])
    plt.grid()
    plt.figure(3)
    plt.plot(freq, im, 'g')
    plt.xlabel('Fréquence')
    plt.ylabel('Imaginaire')
    plt.axis([0, fe/2, im.min(), im.max()])
    plt.grid()
    plt.figure(4)   
    plt.plot(freq, adB, 'r')
    plt.xlabel('f')
    plt.ylabel('AdB')
    plt.axis([0, fe/2, adB[0:512].min(), adB[0:512].max()])
    plt.grid()

#%%
fe = 10
N = 1024

#%%
### Visualisation de la transformée de Fourier  






