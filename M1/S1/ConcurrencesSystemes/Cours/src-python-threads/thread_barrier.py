#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading,time
from threading import Barrier

def thread_function(bar) :
    print(f"Thread {threading.current_thread()}: start " )
    cpt=0
    while True :
        print(threading.current_thread(),cpt)
        cpt+=1
        bar.wait()
        time.sleep(1);
        
     


if __name__ == "__main__":
    bar = Barrier(4)
    print("Main    : crée et lance 4 threads")
    for _ in range(4):
       x=threading.Thread(target=thread_function,args=(bar,) )
       x.start()

    
    