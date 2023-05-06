#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading

import time

 
 
buffer=[]
MAX=10

rlock=threading.RLock()
article=threading.Condition(rlock)
place=threading.Condition(rlock)  # le meme verrou

def extraire( buffer):
         
    rlock.acquire()
    while len(buffer) ==0:
        article.wait()
    print("->extraire",buffer)
    result=buffer[0]
    del buffer[0]
    print("<-extraire",buffer)
    place.notify() #libere prod
    rlock.release()
    return result

def deposer(buffer,elt):
     
   
    rlock.acquire()
    
    while len(buffer) > MAX: # 10 place max
        place.wait()
    print("-> deposer",buffer)
    buffer.append(elt)
    
    print("<- deposer",buffer)
    article.notify() #libere cons
    rlock.release()
     
    
def thread_prod( buffer):
     
    
     
    print("Thread Producteur   starting   arg"  ,threading.current_thread() )
     
    cpt =0
    while True :
          
        cpt+=1
        print("Prod ",cpt)
        deposer(buffer,cpt)
             
        time.sleep(0.25)
        
        
def thread_cons(  buffer):
     
    
     
    print("Thread Consomateur   starting   arg",threading.current_thread()   )
      
     
    while True :
          
      
        x=extraire (buffer)
        print("Consomateur ",x)     
        time.sleep(0.25)
        
           
  


if __name__ == "__main__":
    
     
    buffer=[]
    print("Main    : before creating thread")

    print("Main ", threading.current_thread() )
    #target=thread_function, args=(1,)
    p=threading.Thread(target=thread_prod, args=(buffer,))
    p.start()
    p2=threading.Thread(target=thread_prod, args=(buffer,))
    p2.start()
    c=threading.Thread(target=thread_cons, args=(buffer,))
    c.start()
    
    
    
    p.join()
    c.join()
    

    

    