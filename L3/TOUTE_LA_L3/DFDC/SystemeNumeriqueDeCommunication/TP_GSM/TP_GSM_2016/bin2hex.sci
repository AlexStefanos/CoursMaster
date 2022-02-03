function y = bin2hex(x)

// y = chaine hexa
// x = tableau de dim 1,4*longueur(y)

y = "";

index = 1:4;
for i=1:length(x)/4
  c_bin = x(index);
  c_dec = 8*c_bin(1) + 4*c_bin(2) + 2*c_bin(3) + c_bin(4);
  c_hex = dec2hex(c_dec);
  y = y + c_hex;
  index = index + 4;
end

endfunction
