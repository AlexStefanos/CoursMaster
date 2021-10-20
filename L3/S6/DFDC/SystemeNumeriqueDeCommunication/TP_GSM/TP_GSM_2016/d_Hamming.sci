function d = d_Hamming(x,y)

d = sum(modulo(x+y,2));

endfunction