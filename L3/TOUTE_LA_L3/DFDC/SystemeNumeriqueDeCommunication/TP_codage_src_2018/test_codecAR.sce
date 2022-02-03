clear

// chargement des fonctions
exec("cod_AR.sci");
exec("decod_AR.sci");
//exec("jouer.sci");
exec("ecrit_wav.sci");
exec("quantif.sci");

// chargement d'un fichier audio -> vecteur d'échantillons s et frequence d'echantillonnage Fe
// fichiers voix disponibles : al10.wav  lf10.wav  fe15.wav  rd10.wav
fichier = "al10"
[s,Fe] = wavread(fichier+".wav");

// Affichage s
scf(); // cree fenetre 0 si necessaire et l'efface
plot(s,'k') // affiche s
xtitle("signal original s")

// quantification de s sur 12 bits -> sq
sq = quantif(s,1,12);
// ecriture du fichier audio
wavwrite(sq,Fe,fichier+"_q.wav")

// codage
[coded,err] = cod_AR(s,10,160,2,10);

// decodage
[s_codec,e,sig2] = decod_AR(coded);
wavwrite(s_codec,Fe,fichier+"_codec.wav")

// Affichage e
scf() ;
plot(e) // affiche e
xtitle("residu de prediction e")



