function y = codeur_conv(x,C)

// C = matrice binaire (1/R) * (m+1) des operations
// x = vect ligne binaire
// y = matrice binaire (1/R) * longueur de x

N = length(x);
[p,q] = size(C);
Reg = [zeros(1,q-1),x];
y = zeros(p,N);

index = 1:q;
for n=1:N 
  y(:,n) = modulo(C*Reg(index)',2);
  index = index + 1;
end

  
endfunction