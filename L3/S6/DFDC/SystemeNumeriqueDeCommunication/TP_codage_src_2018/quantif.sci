function xq = quantif(x,max_x,k)

// quantification sur k bit d'un signal x de max max_x en val abs

    // ENTREES
    // x = signal `a coder
    // max_x = valeur absolue max du signal
    // k = nombre de bits de quantification 

xq = sign(x).*min(abs(x),max_x);
q = 2*max_x / 2^k;
xq = q*round(xq/q);

endfunction
