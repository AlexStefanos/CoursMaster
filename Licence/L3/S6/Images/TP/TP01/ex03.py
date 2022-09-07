import numpy as np
import matplotlib.pyplot as plt
from PIL import Image
from skimage.draw import line

img = np.zeros([128, 128], dtype = np.uint8)
rr,cc = line(1, 1, 100, 100)
img[rr, cc] = 1

plt.imshow(img)
plt.show()