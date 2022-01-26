import numpy as np
import matplotlib.pyplot as plt
import matplotlib.patches as patches
from PIL import Image
import math

def rand():
    rdm = np.random.randint(127)
    return(rdm)

def rand2(nb):
    rdm2 = np.random.randint(nb, 127)
    return(rdm2)

hauteur = 128
largeur = 128
canal = 3
img = np.zeros([hauteur, largeur, canal], dtype = np.uint8)
for i in range(3):
    rect1 = rand()
    rect2 = rand()
    img[rect1: rand2(rect1), rect2: rand2(rect2)] = 255
for i in range(128):
    for j in range(128):
        for c in range(3):
            if img[i, j, c] == 255:
                img[i, j, 0] = 0
                img[i, j, 1] = 0
#np.where permet de toutes les coordonnées qui remplissent la condition 
plt.imshow(img)
plt.show()