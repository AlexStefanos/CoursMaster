import numpy as np
import matplotlib.pyplot as plt
import matplotlib.image as matimg

img = np.array([[12, 10, 8, 13, 25, 50, 46],
                [8, 7, 8, 10, 20, 23, 31],
                [11, 9, 10, 14, 28, 30, 37],
                [14, 11, 10, 26, 31, 28, 32],
                [12, 9, 11, 31, 35, 33, 41],
                [7, 12, 33, 35, 41, 50, 70],
                [13, 10, 35, 38, 75, 73, 72],
                [9, 14, 41, 45, 71, 76, 75]])
maxVal = 100
plt.figure()
plt.imshow(img, cmap = plt.cm.gray, vmin = 0, vmax = maxVal)
plt.show()

histogram = [0] * 100
for i in range(img.shape[0]):
    for j in range(img.shape[1]):
        histogram[img[i, j]] += 1
# print(histogram)
histogramCumule = [0] * 100
histogramCumule[0] = histogram[0]
# histogramCumule[1] = histogramCumule[0] + histogram[1]
# histogramCumule[2] = histogramCumule[1] + histogram[2]
for i in range(maxVal):
    histogramCumule[i] = histogramCumule[i-1] + histogram[i]
# print(histogramCumule)

histogramEgalise = [0] * 100
N = img.shape[0] * img.shape[1]
n = 100
img2 = np.zeros(img.shape, dtype = np.uint8)
for ligne in range(img.shape[0]):
    for col in range(img.shape[1]):
        i = img[ligne, col]
        j = max(0, n/N * histogramCumule[i]-1)
        img2[ligne, col] = j
print(histogramEgalise)

plt.figure()
plt.imshow(img2, cmap = plt.cm.gray, vmin = 0, vmax = maxVal)
plt.show()