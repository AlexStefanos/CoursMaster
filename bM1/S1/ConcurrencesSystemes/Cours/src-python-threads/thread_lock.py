#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading
import time

cpt=0 # global
mutex=threading.Lock() # un verou

def thread_function( ):
    global cpt # compteur global
    print(f"Thread {threading.current_thread()}: start " )
      
    while True :      
        mutex.acquire()  
        cpt+=1
        print(threading.current_thread(),cpt)       
        mutex.release()     
        time.sleep(0.25) # leep sur le thread courant 
         
if __name__ == "__main__":
    print("Main ", threading.current_thread()) 
    for i in range(4) :  # creation de 4 threads
        t=threading.Thread(target=thread_function)
        t.start()

    

    