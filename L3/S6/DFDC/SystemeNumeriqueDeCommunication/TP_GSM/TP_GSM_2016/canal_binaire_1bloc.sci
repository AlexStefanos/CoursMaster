function bloc_out = canal_binaire_1bloc(bloc_in,p)

// erreur concentrée sur 78 bits :
p_bis = p*260/50;

// Bloc erreur selon Pe
N = length(bloc_in);
tmp = rand(1,N,"uniform") / p_bis;
I = find(tmp<1);
err_bin = zeros(1,N); err_bin(I) = 1;
// erreur concentrée sur classe II :
err_bin(51:$)=0;

// Sortie = entree + erreur
bloc_out = modulo(bloc_in+err_bin,2);

endfunction
