# -*- coding: utf-8 -*-
"""
Created on Wed Apr 19 14:31:07 2017

@author: sylviegibet

"""
#%%
# Saisies 
import numpy as np
import matplotlib.pyplot as plt
from scipy.io.wavfile import read
from scipy.fftpack import fft
from scipy import signal
#%%
#(fs,x) = read('./DATA/Sons/Oiseaux/etourneau.wav')
#(fs,x) = read('./DATA/Sons/Oiseaux/fauvette.wav')
#(fs,x) = read('./../../DATA/Sons/Oiseaux/piebavarde.wav')
#(fs,x) = read('./../../DATA/Sons/Oiseaux/rossignol.wav')
(fs,x) = read('./../../DATA/Sons/Classique/nocturne_chopin.wav')
x=x[:,1]

