import numpy as np
import matplotlib.pyplot as plt
import matplotlib.image as matimg
import cv2

img = cv2.imread('landscape.png', cv2.IMREAD_GRAYSCALE)
plt.figure()
plt.imshow(img, cmap = plt.cm.gray, vmin = 0, vmax = 255)
plt.show()

