function [x,N,octets,Fe,nb_canaux] = lit_wav(fichier)

// lit un fichier wav en laissant les échantillons dans leur gamme de valeurs originelle

fid = mopen(fichier+".wav",'r');

mseek(4,fid);
taille_fichier = mget(1,'i',fid)+8;

mseek(22,fid);
nb_canaux = mget(1,'us',fid);

mseek(24,fid);
Fe = mget(1,'ui',fid);

//nb octets par echantillon
mseek(34,fid);
octets = mget(1,'s',fid)/8;

// nb echantillons
N = (taille_fichier-44)/octets;
//N=2194416;

// signal
mseek(44,fid);
select octets
case 1 then x = mget(N,'uc',fid),
case 2 then x = mget(N,'s',fid),
end
//x = matrix(x,N/nb_canaux,nb_canaux)';

mclose(fid);

endfunction
