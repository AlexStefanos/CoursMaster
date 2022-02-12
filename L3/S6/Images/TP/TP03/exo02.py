import numpy as np
import matplotlib.pyplot as plt
import matplotlib.image as matimg
import cv2

img = matimg.imread('landscape.png')
plt.figure()
plt.imshow(img, cmap = plt.cm.gray, vmin = 0, vmax = 255)
plt.show()