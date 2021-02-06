
import tkinter as tk
from time import sleep

class Hanoi():
    def __init__(self,ndiscs,temps=0.5):
        assert type(ndiscs) is int
        assert 1 <= ndiscs <= 10
        assert temps>=0
        self.ndiscs = ndiscs
        self.temps = temps
        self.P = [list(range(ndiscs,0,-1))]+[[] for _ in range(2)]
        self.rad = [30+20*i for i in range(ndiscs)]
        self.col = ['pink','yellow','orange','cyan','red','green','blue','red4','purple','black']
        self.hdisc = 25
        self.sep = max(self.rad)*2+21
        self.height = 20 + 20 + self.hdisc*ndiscs + 40 + 20
        self.width = 30 + 20 + 3*self.sep + 20 + 30
        self.win = tk.Tk()
        self.win.title("Tours de Hanoi")
        self.can = tk.Canvas(self.win,height=self.height,width=self.width,bg='white')
        self.display()

    def display(self):
        self.can.delete("all")
        self.can.create_rectangle(30,self.height-60,self.width-30,self.height-20,outline='black',fill='brown')
        for i in range(3):
            x0 = 30+20+self.sep//2+self.sep*i
            self.can.create_rectangle(x0-5,20,x0+5,self.height-61, outline='black', fill='black')
            for k in range(len(self.P[i])):
                j = self.P[i][k]
                x0 = 30+20+self.sep//2+self.sep*i
                y0 = self.height-60-(k+1)*self.hdisc
                r = self.rad[j-1]
                self.can.create_rectangle(x0-r,y0,x0+r,y0+self.hdisc, outline='black', fill=self.col[j-1])    
        self.can.pack()
        self.win.update()
        sleep(self.temps)

    def move(self,src,dst):
        assert len(self.P[src])>0, "Mouvement impossible: pas de disque sur cette tige"
        assert len(self.P[dst])==0 or self.P[dst][-1]>self.P[src][-1], "Mouvement interdit: un disque ne peut être posé sur un disque plus petit"
        x = self.P[src].pop()
        self.P[dst].append(x)
        self.display()

    def quit(self):
        input('Pressez entrée pour quitter...')
        self.win.quit()

