import numpy as np
import matplotlib.pyplot as plt
import matplotlib.image as matimg

img = (matimg.imread("chambre.jpg").copy()-255).astype(np.uint8)
plt.figure()
plt.show()

imgGris = np.zeros((img.shape[0], img.shape[1]), dtype = np.uint8)
for i in range(img.shape[0]):
    for j in range(img.shape[1]):
        imgGris[i, j] = (img[i, j, 0] * 1.0 + img[i, j, 1] * 1.0 + img[i, j, 2] * 1.0) / 3
plt.figure()
plt.imshow(imgGris, cmap = plt.cm.gray, vmin = 0, vmax = 255)
plt.show

imgInv = np.zeros(img.shape, dtype = np.uint8)
for i in range(img.shape[0]):
    for j in range(img.shape[1]):
        imgInv[i, j, 0] = 255 - img[i, j, 0]
        imgInv[i, j, 1] = 255 - img[i, j , 1]
        imgInv[i, j, 2] = 255 - img[i, j, 2]

plt.figure()
plt.imshow(imgInv, cmap = plt.cm.gray, vmin = 0, vmax = 255)
plt.show()