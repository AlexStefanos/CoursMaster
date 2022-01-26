import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

img = np.zeros([128, 128], dtype = np.uint8)
for i in range(128):
    img[:, i:2*i] = i*2
plt.imshow(img, cmap = 'Greys_r')
plt.show()