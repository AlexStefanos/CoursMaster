function s = syndrome_decode_bloc(r,n,k,G)

// cf Glavieux p.239

Reg = zeros(1,n-k+1);

for i=1:n-k
  add = G(1:n-k)*Reg(n-k+1);
  Reg(1) = r(i);
  Reg(2:n-k+1) = modulo( Reg(1:n-k)+add , 2 );
end

s = Reg(2:n-k+1);

endfunction