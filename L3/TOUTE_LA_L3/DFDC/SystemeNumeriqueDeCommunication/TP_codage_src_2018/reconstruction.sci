function y = reconstruction(YY,fenetre)
    
  exec("imdct4.sci");

  [Nfreq,nblocs] = size(YY);
  L = 2*Nfreq;
  overlap = L/2;

  y = zeros(1,nblocs*L/2+L/2);

  index = 1:L;
  for bloc=1:nblocs
      y(index) = y(index) + fenetre.*imdct4(YY(:,bloc));
      index = index + L - overlap; 
  end
  
  y = y(overlap+1:$-overlap);
  
endfunction