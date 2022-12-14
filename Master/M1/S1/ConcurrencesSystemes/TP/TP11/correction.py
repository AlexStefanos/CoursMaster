class Moustique():

    def __init__(self, x, y, width, height, image)
        self.mutex = threading.lock()

    def setTH(self.th): #comme ça chaque moustique
        self.thread=th

    def getpose(self):
        self.mutex.acquire()
        x
        y 
        self.mutex.release()

    def move(self):
        global start 

        self.mutex.acquire()
        [...]
        self.mutex.release() #dernière ligne


def anime():
    for mous in l_moustique:
        x,y=mous.getPos()
        can.coords(mous.getImage(),x ,y) #pas sûr de cette ligne

print("Main ", threading.current_thread())

l_moustique=[]
for i in range(max):
    x=random.randint(0, xDim)
    y=random.randint(0, yDim)
    monimage = can.create_image(x, y, anchor = CENTER, image=mou1)
    t=threading.thread()
    m.setTh(t)
    t.start()
    l_moustique.append(m)

fen.mainloop()