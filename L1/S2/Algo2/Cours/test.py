import math

def fonction(n):
    if n >= 2:
        while n > 0 and n > 1/1000:
            n = math.sin((math.pi)/n*n)
        return n
print(fonction(200))
