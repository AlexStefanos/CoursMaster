#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading
import time

 

def thread_function( synchr):
    global cpt # compteur global
    print(f"Thread {threading.current_thread()}: start " )
      
    while True :  
        synchr.wait() # attend synchr.set()  
        
        print(threading.current_thread(),cpt)       
        time.sleep(0.25) # leep sur le thread courant 
         
if __name__ == "__main__":
    synchr=threading.Event()
    print("Main ", threading.current_thread()) 
    for i in range(4) :  # creation de 4 threads
        t=threading.Thread(target=thread_function,args=(synchr,))
        t.start()
    time.sleep(3) 
    synchr.set()  
    

    