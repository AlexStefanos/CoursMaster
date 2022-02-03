import numpy as np
from matplotlib import pyplot as plt

img = np.zeros((630, 1345), dtype = np.uint8)
l = 630%112
c = 1345%112
for x in range (0, img.shape[0], l):
	for i in range (0, l):
		for j in range (0, c):
			img[i,j] = 192



plt.figure()
plt.imshow(img, cmap=plt.cm.gray, vmin=0, vmax=255)
plt.show()
