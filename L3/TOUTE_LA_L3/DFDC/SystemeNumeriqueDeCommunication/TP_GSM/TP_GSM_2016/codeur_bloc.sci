function c = codeur_bloc(m,n,k,g)

G = zeros(k,n);
G(1,1:n-k+1) = g;
for i=2:k
  G(i,:) = [0,G(i-1,1:n-1)];
end

c = modulo(m*G,2);

endfunction