function c = codeur_bloc_cycl(m,n,k,g)

// pol m = a0 + a1.x + ... + x^k
// g = g0 + g1.x + ... + x^(n-k)

// Reste de la division de m.x^(n-k) par g
// On entre m.x^(n-k) dans R en commençant par les fortes puissances

R = zeros(1,n-k);

for i=1:k
   add = R(n-k) * g(1:n-k);
   R(2:n-k) = modulo(R(1:n-k-1)+add(2:n-k),2);
   R(1) = modulo(m(i)+add(1),2);
end

//for i=1:k
//  add = modulo(R(n-k)+m(i),2) * g(1:n-k);
//  R(2:n-k) = modulo(R(1:n-k-1)+add(2:n-k),2);
//  R(1) = add(1);
//end

c = [m,R];

endfunction

  
