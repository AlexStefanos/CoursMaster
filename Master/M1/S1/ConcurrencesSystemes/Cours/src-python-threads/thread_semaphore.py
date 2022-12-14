#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading,time

 
cpt=0 # variable global

mutex=threading.Semaphore(1) #un sémaphore initialisé à 1

def thread_function(  ):
    global cpt  
    print(f"Thread {threading.current_thread()} starting"   )
    while True :     
        mutex.acquire()
        cpt+=1
        print(threading.current_thread(),cpt)   
        mutex.release()    
        time.sleep(0.25)
    

if __name__ == "__main__":

  
    print("Main ", threading.current_thread())
   
    for i in range(4) :
        t=threading.Thread(target=thread_function )
        t.start()

    

    