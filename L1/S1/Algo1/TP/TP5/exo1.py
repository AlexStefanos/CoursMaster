# -*- coding: utf-8 -*-

def appartient_listeliste(n, ll) :
    app = False
    i = 0
    while i < len(ll) and not(app) :
        if n in ll[i] :
            app = True
            i = i + 1
    print(app)

appartient_listeliste(5, [[1], [4]])
