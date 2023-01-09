# -*- coding: utf-8 -*-
"""
 
 
Role : test d'animation
   interface 
       un Canvas pour le dessin
       un bouton pour lancer l'animation
"""
import random

import threading
import time
import sys
import os
import signal

start=False
 

class Moustique( ):
    "store file metadata"
    
    def __init__(self ,x,y,width,height,image ):
        self.width=width
        self.height=height
        
        self.x=x
        self.y=y
        self.mutex=threading.Lock() # un verrou
        
        self.dx=random.randint(-50,+50)
        self.dy=random.randint(-50,+50)
        
        self.image=image
        
    def setTh(self):
        self.thread = threading
        
    def getpos(self):
        self.mutex.acquire()    
        x=self.x
        y=self.y     
        self.mutex.release()
        return x,y
    
    def getImage(self):
        return self.image

    def move(self) :
        global start

        self.mutex.acquire()
        if start :
            self.x+=self.dx
            self.y+=self.dy
              
        
            if  self.x < 0 or self.x > self.width   :
               self.dx=-self.dx
            if  self.y < 0 or self.y > self.width   :
                  self.dy=-self.dy
        self.mutex.release()
                  
 

from tkinter import *  
       
  
# Move effectue l'animation
# fonction appelée par le bouton démarrer
#     puis sera appelée toutes le 100 millisecondes
#
def anime():
    
     
    for mous in l_moustique :
        #print( "Moutique ",mous)
        
        #mous.move()
        x,y=mous.getpos()
        can.coords(mous.getImage(),x ,y)
        
 
    fen.after(100,anime)         # appel de la fonction apres 100 millisecondes
 

# fonction appelée lors de l'apui sur le bouton "demarrer"
def start_it():
    global start
    start=True
    anime() # premier appel à la focntion move
 
def stop_it():
    print("QUIT")    
    os.kill(os.getpid(), signal.SIGKILL)
  
#========== Programme principal =============
 
xDim,yDim =400,400 # hauteur et largeur du dessin
 
 
# Creation de la fenêtre :
fen = Tk()
fen.title("Animation avec Tkinter")

# creation du Canvas  avec un fond gris:
can = Canvas(fen,bg='dark grey',height=yDim, width=xDim)
can.pack()



# PhotoImage chargement d'une image
# l'image doit être dans le même répertoire que le programme 
# file : le nom du fichier image
mou1 = PhotoImage(file="moustique.gif");  
 
#base = PhotoImage(file="base.png");


# bouton pour démarrer l'animation
# command :  fonction associé au bouton
#            qui sera appelée lors de l'appui sur le bouton
bout = Button(fen, text='Demarrer', width =8, command=start_it)
bout.pack()
bout2 = Button(fen, text='Quit', width =8, command=stop_it)
bout2.pack()

 
print("Main ", threading.current_thread()) 


max=4

l_moustique=[]
for i in range(max) :  # creation de 4 threads
    x=random.randint(0,xDim)
    y=random.randint(0,yDim)
    monimage = can.create_image(x ,y , anchor = CENTER, image=mou1)
    m=Moustique(x,y,xDim,yDim,monimage)
    t=threading.Thread()
    m.setTh(t)
    t.start()
    l_moustique.append(m)

# demarrage du receptionnaire d'evenements (boucle principale) :
fen.mainloop()

#il faut faire une liste d'objets Moustique, on fait un thread/moustique, et après le thread va passer son temps à faire des move sur son moustique
#il faut juste récupérer les x, y des moustiques et les afficher
#il faut protéger le fait de les afficher en les déplaçant (principe de lecteur redacteur : tk est une sorte de lecteur, chaque moustique est un rédacteur)