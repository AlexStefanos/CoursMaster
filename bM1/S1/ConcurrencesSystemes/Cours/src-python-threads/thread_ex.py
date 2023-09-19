#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 17 16:27:26 2020

@author: courtrai
"""

 

import threading


def thread_function( ) :
    print(f"Thread {threading.current_thread()}: start " )
     


if __name__ == "__main__":

    print("Main    : crée et lance le thread")
    x = threading.Thread(target=thread_function )
    x.start()

    print("Main    : join ")

    x.join()

    