function tmp = ecrit_trame(trame_bin,fichier)

exec("bin2hex.sci");

trame_hex = string(zeros(1,33));
index = 1:8;
for i=1:33
  trame_hex(i) = bin2hex(trame_bin(index));
  index = index + 8;
end
trame_dec = hex2dec(trame_hex);
mput(trame_dec,"uc",fichier);
tmp = 0;

endfunction