function XX = spectrogram(x,fenetre)
  
  exec("mdct4.sci");

  L = length(fenetre);
  overlap = L/2;
  nblocs = ceil(length(x)/(L-overlap))+1;
  XX = zeros(L/2,nblocs);
  index = 1:L;
  xx = [zeros(1,overlap) x zeros(1,3*overlap)]; // on compte large sur la fin
  
  for bloc=1:nblocs
    XX(:,bloc) = mdct4(xx(index).*fenetre)';
    index = index + L - overlap;
  end
  
endfunction
