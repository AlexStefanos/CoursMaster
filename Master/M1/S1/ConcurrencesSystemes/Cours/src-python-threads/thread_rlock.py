#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading
import time

cpt=0 # global
r_mutex=threading.RLock() # un verou réentrant

def getCpt():
    r_mutex.acquire() 
    val=cpt
    r_mutex.release()
    return val
def incCpt(inc):
    global cpt
    r_mutex.acquire() 
    cpt=getCpt()+inc
    r_mutex.release()
    
def thread_function( ):
    print(f"Thread {threading.current_thread()}: start " )
      
    while True :      
        incCpt(1)
        print(threading.current_thread(),getCpt())       
            
        time.sleep(0.25) # leep sur le thread courant 
         
if __name__ == "__main__":
    print("Main ", threading.current_thread()) 
    for i in range(4) :  # creation de 4 threads
        t=threading.Thread(target=thread_function)
        t.start()

    

    