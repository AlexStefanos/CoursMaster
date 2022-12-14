#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading

import time

 


article=threading.Condition()

def extraire( liste):      
    article.acquire()
    while len(liste) ==0:
        article.wait()
    print("->extraire",liste)
    result=liste[0]
    del liste[0]
    print("<-extraire",liste,result)
    article.release()
    return result

def deposer(liste,elt): 
    article.acquire()
    print("-> deposer",liste,elt)
    liste.append(elt)  
    print("<- deposer",liste,elt)
    article.notify()
    article.release()
     
    
def thread_prod( liste):
     
    print("Thread Producteur   starting   "  ,threading.current_thread() )   
    cpt =0
    while True :         
        cpt+=1
        print("Prod ",cpt)
        deposer(liste,cpt)
             
        time.sleep(0.25)
        
        
def thread_cons(  liste):
         
    print("Thread Consomateur   starting   ",threading.current_thread()   )          
    while True :
        x=extraire (liste)
        print("Consomateur ",x)     
        time.sleep(0.25)
        
           
  


if __name__ == "__main__":
    
     
    liste=[]
    print("Main    : before creating thread")
 
    #target=thread_function, args=(1,)
    p=threading.Thread(target=thread_prod, args=(liste,))
    p.start()
    c=threading.Thread(target=thread_cons, args=(liste,))
    c.start()
 

    

    