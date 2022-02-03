function y = codeur_convolutif(x)

// entrée = vecteur à coder
// sortie = vecteur codé

exec("codeur_conv.sci");

N = length(x);

C = [[1 1 1];[1 0 1]];

y_tmp = codeur_conv(x,C); // dim 2*N
y = zeros(1,2*N);
y(1:2:2*N) = y_tmp(1,:);
y(2:2:2*N) = y_tmp(2,:); // dim 1*2N

endfunction