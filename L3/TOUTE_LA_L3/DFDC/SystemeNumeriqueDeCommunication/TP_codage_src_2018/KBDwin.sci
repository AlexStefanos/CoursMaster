function k = KBDwin(L,b)

// L pair

w = window('kr',L/2+1,b);

k=zeros(1,L);

for i=1:L/2
 k(i)=sqrt(sum(w(1:i))/sum(w));
end
k(L:-1:L/2+1) = k(1:L/2);


endfunction
