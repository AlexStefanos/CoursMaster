# -*- coding: utf-8 -*-

def residu1 (n):
    if n < 10 :
        return n
    return residu1(n//10+n%10)
# ou alors : return residu1(sum(int(x) for x in str(n))

#on a pas fait la question 2

""" Soit S(n) la somme des chiffres de n (écrit en base 10). Si n=a0 + 10a1 + 10²a2 + 10puissancek * ak . Alors comme 10puissancei = 1 [9] pour tout i on a n = a0 + a1 + a2 +...+ak [9]
