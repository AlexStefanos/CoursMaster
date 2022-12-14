#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading

 


def thread_function( arg1,arg2,arg3):
    
    
    print("Thread %s: starting   arg",arg1,arg2,arg3  )
  

if __name__ == "__main__":
  
    x = threading.Thread(target=thread_function, args=(1,2,3))   
    x.start()
    x.join()

    