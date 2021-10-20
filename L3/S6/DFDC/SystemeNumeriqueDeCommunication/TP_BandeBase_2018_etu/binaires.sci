function msg = genere_msg_aleatoire(N)
    // genere un msg aleatoire de N eb
    msg = round(rand(1,N,"uniform"));
endfunction

function x = bin2int(v)
    // v = vecteur binaire, bits de poids fort en 1er
    // x = entier
    NbNits = length(v);
    u = matrix(v,1,NbNits)
    x = sum(u($:-1:1).*(2).^(0:NbNits-1));
endfunction

function v = int2bin(x,n)
    // int -> tableau vertical de n bits
    b = dec2bin(x); // -> chaine de caracteres 0/1
    v = evstr(strsplit(b));  // -> tableau vertical de reels 0/1
    v = [zeros(n-length(v),1); v];
endfunction

function d = d_Hamming(x,y);
    d = sum(modulo(x+y,2));
endfunction

function x = bin2int_Gray4(v)
    // v = vecteur binaire, 
    // x = entier
    mot = string(v(1)) + string(v(2));
    mots = ["11","10","00","01"];
    for k=1:4
        if ~strcmp(mot,mots(k)) then
            x = k-1;
        end
    end
endfunction

function v = int2bin_Gray4(x,n)
    // int -> tableau vertical de 2 bits
    mots = ["11","10","00","01"];
    mot = mots(x+1);
    v = evstr(strsplit(mot));
endfunction
