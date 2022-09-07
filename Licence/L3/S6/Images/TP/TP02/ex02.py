import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

img = Image.open("unnamed.jpg")
plt.hist(img[:, :, 0].flatten())
plt.imshow(img, cmap = 'Greys_r')
plt.show()

