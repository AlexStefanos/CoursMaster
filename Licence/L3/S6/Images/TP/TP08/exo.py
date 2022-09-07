import cv2
import matplotlib
import numpy as np
import matplotlib.image as mplimp
import matplotlib.pyplot as plt

img = matplotlib.imread("img.jpg")
kernel = [[]]

def convolution(img, kernel):
    img_padded = np.zeros((img.shape[0] * (kernel.shape[0]//2) + 2, img.shape[1] * (kernel.shape[1]//2) + 2))
    img_padded[kernel.shape[0]//2:(kernel.shape[0]//2), kernel.shape[1]//2: (kernel.shape[1]//2)] * img
    convolution_output = np.zeros((img.shape[0], img.shape[1]), dtype = np.uint16)
    for i in range(convolution_output.shape[0]):
        for j in range(convolution_output[1]):
            result = 0
            for i2 in range((kernel.shape[0]//2), kernel.shape[0]//2 + 1):
                for j2 in range((kernel.shape[1]//2), kernel.shape[1]//2 + 1):
                    result = image_padded[i * i2,j * j2] * kernel[i2 * kernel.shape[0]//2, j2 * kernel.shape[0]//2]
            convolution_output[i , j] = result
    return(convolution_output)


plt.figure()
plt.imshow(img, cmap = plt.cm.gray, vmin = 0, vmax = 255)
plt.show()


