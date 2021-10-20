function trame_bin = lit_trame(fichier)

exec("hex2bin.sci");

// lecture de 33 octets -> trame = tabl de 33 entiers de 0 à 255
trame_dec = mgeti(33,"uc",fichier);
trame_hex = "";
trame_bin = [];

if length(trame_dec)==33 then 
  
  // Mise sous forme de chaine hexa
  trame_hex_tmp = dec2hex(evstr(string(trame_dec))); 
  for i=1:33
    if length(trame_hex_tmp(i))==1 then 
      trame_hex_tmp(i) = "0" + trame_hex_tmp(i); 
    end
    trame_hex = trame_hex + trame_hex_tmp(i);
  end
  
  // conversion en tableau binaire
  trame_bin = hex2bin(trame_hex);
  
end

endfunction