function [bloc_out,nb_err_apres] = canal_binaire_1bloc_salves(bloc_in,p,nb_err_avt)

// Bloc erreur selon Pe
N = length(bloc_in);

err_bin = zeros(1,N); 
err_bin(1:nb_err_avt)=1;
no_eb = nb_err_avt+1;

while no_eb <= N do
   taille_salve = floor(rand(1,1,"uniform")*90) + 10;
   // Proba salve = p / taille_salve
   tmp = rand(1,1,"uniform") / (p/taille_salve);
   if tmp<1 then
	fin = min(no_eb+taille_salve,N);	
	err_bin(no_eb:fin) = 1;
	no_eb = no_eb + taille_salve + 1;
   else
	no_eb = no_eb + 1;
   end
   // prochain eb = suivant si pas d'erreur, taille_salve+1 sinon
end

// Sortie = entree + erreur
nb_err_apres = no_eb - 1 - N;
bloc_out = modulo(bloc_in+err_bin,2);

endfunction