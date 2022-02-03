exec("canal_binaire_1bloc.sci");
exec("canal_binaire_1bloc_salves.sci");
exec("lit_trame.sci");
exec("ecrit_trame.sci");
exec("codeur_convolutif.sci");
exec("decod_Viterbi.sci");
exec("d_Hamming.sci");
exec("play_gsm.sci");

fic_in = "al10.gsm";
fic_out = "al10_out3.gsm";

[IN,err] = mopen(fic_in,"r"); 
[OUT,err] = mopen(fic_out,"w");

nb_trames = 0;

while ~meof(IN)
  
  trame = lit_trame(IN); // trame = vecteur 1 * 264
  if length(trame)==264 then // 264bits=33octets, au lieu de 260 -> 4 bits en +
        
    nb_trames = nb_trames + 1;
    
    // codage
    // ... à compléter ...
    
    // Le canal : trame -> trame_out *****************
    trame_out = canal_binaire_1bloc(trame,0.01);
    //********************************************************
    
    // décodage
    // ... à compléter ...

    ecrit_trame(trame_out,OUT);
    
  end
end;

mclose(IN);
mclose(OUT);

