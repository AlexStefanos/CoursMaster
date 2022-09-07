import cv2

img = cv2.imread('/users/licence/il06110/test.png', cv2.IMREAD_GRAYSCALE)
#(retVal, newImg) = cv2.threshold(img, 145, 255, cv2.THRESH_TOZERO)
for i in range (256):
	img = cv2.imread('/users/licence/il06110/test.png', cv2.IMREAD_GRAYSCALE)
	(retVal, newImg2) = cv2.threshold(img, i, 255, cv2.THRESH_BINARY)
	#cv2.imshow('test', newImg)
	#cv2.imshow('test2', newImg2)
	#cv2.waitKey(0) 
	#cv2.destroyAllWindows()
	count = cv2.countNonZero(newImg2)
	print('Pour', i, ':', + 65536 - count)
	


