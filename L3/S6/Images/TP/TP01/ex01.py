import numpy as np
import matplotlib.pyplot as plt
from PIL import Image
import math

hauteur = 128
largeur = 128
canal = 3
new_array_RGB = np.zeros([hauteur, largeur, canal], dtype = np.uint8)
pointLigne1 = [14, 64]
pointLigne2 = [114, 64]
xLigne_valeurs = [pointLigne1[0], pointLigne2[0]]
yLigne_valeurs = [pointLigne1[1], pointLigne2[1]]

pointOblique1 = [14, 114]
pointOblique2 = [114, 14]
xOblique1_valeurs = [pointOblique1[0], pointOblique2[0]]
yOblique1_valeurs = [pointOblique1[1], pointOblique2[1]]

pointOblique3 = [14, 14]
pointOblique4 = [100 * math.cos(math.radians(30)), 14 + 100 * math.cos(math.radians(30))]
xOblique2_valeurs = [pointOblique3[0], pointOblique4[0]]
yOblique2_valeurs = [pointOblique3[1], pointOblique4[1]]

pointRectangleH1 = [10, 10]
pointRectangleH2 = [100, 10]
pointRectangleB1 = [10, 50]
pointRectangleB2 = [100, 50]
xRectangle1_valeurs = [pointRectangleH1[0], pointRectangleH2[0]]
yRectangle1_valeurs = [pointRectangleH1[1], pointRectangleH2[1]]
xRectangle2_valeurs = [pointRectangleB1[0], pointRectangleB2[0]]
yRectangle2_valeurs = [pointRectangleB1[1], pointRectangleB2[1]]
xRectangle3_valeurs = [pointRectangleH1[0], pointRectangleB1[0]]
yRectangle3_valeurs = [pointRectangleH1[1], pointRectangleB1[1]]
xRectangle4_valeurs = [pointRectangleH2[0], pointRectangleB2[0]]
yRectangle4_valeurs = [pointRectangleH2[1], pointRectangleB2[1]]

plt.plot(xLigne_valeurs, yLigne_valeurs)
plt.plot(xOblique1_valeurs, yOblique1_valeurs)
plt.plot(xOblique2_valeurs, yOblique2_valeurs)
plt.plot(xRectangle1_valeurs, yRectangle1_valeurs)
plt.plot(xRectangle2_valeurs, yRectangle2_valeurs)
plt.plot(xRectangle3_valeurs, yRectangle3_valeurs)
plt.plot(xRectangle4_valeurs, yRectangle4_valeurs)
plt.imshow(new_array_RGB)
plt.show()