#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading
from threading import Timer
import time

 

def thread_function(arg):
     
    print(f"Thread {threading.current_thread()}: start " )
      
   
if __name__ == "__main__":
    print("Main lance thread dans 10 second")
    t=Timer(10.,thread_function,args=(1,))
    t.start()
     
    

    