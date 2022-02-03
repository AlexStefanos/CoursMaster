clear

// chargement des fonctions
exec("spectrogramMDCT.sci");
exec("mask_vs_MDCT.sci");
exec("miseAzero.sci");
exec("KBDwin.sci");
exec("reconstruction.sci");
exec("lit_wav.sci");
exec("ecrit_wav.sci");

// Chargement du son -> x
[x,N,octets,Fe,nb_canaux] = lit_wav("src6");

// Analyse temps-frequence
Nmdct = 512; 
w = KBDwin(2*Nmdct,8);
XX = spectrogram(x,w); 

// Calcule taux de zeros
[Nmdct,Nblocs] = size(XX);
tauxInit = length(find(abs(XX)<1)) / (Nmdct*Nblocs);
disp("taux initial de zeros dans XX = "+string(tauxInit));

//// Visualisation seuil de masquage vs spectre d'amplitude
mask_vs_MDCT(XX,Fe,"MPEG1",1);

// Mise a zero des coefs MDCT inferieurs au seuil de masquage
[YY,WM] = miseAzero(XX,Fe,"MPEG1",8);

// Reconstruction du signal temporel, y 
y = reconstruction(YY,w);
y = round(y);

// sauvegarde dans un fichier wav
ecrit_wav(y,Fe,"src6z");

// Analyse taux de zeros
tauxYY = length(find(abs(YY)<1)) / (Nmdct*Nblocs);
disp("taux de zeros dans YY = "+string(tauxYY));

