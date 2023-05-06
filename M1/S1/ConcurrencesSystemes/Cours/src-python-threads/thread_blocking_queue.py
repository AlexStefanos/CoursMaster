#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading

import time
import queue

 
 
def thread_prod( aqueue):
     
    print("Thread Producteur   starting   "  ,threading.current_thread() )   
    cpt =0
    while True :         
        cpt+=1
        print("Produteur ",cpt)
        aqueue.put(cpt)
             
        time.sleep(0.25)
        
        
def thread_cons(  aqueue):
         
    print("Thread Consommateur   starting   ",threading.current_thread()   )          
    while True :
        x=aqueue.get()
        print("Consommateur ",x)     
        time.sleep(0.25)
        
           


if __name__ == "__main__":
        
    aQueue=queue.Queue()
     
    p=threading.Thread(target=thread_prod, args=(aQueue,))
    p.start()
    c=threading.Thread(target=thread_cons, args=(aQueue,))
    c.start()
 

    

    