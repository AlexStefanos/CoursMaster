clear
exec("binaires.sci");
exec("emetteur.sci");
exec("recepteur.sci");
exec("mls.sci");
exec("affichage.sci");

stacksize(1e8);

//-----------------------------
// PARAMETRES

Fe = 44100; // frequence d'echantillonnage des fichiers audio (Hz)
duree = 10;  // duree d'emission (s) (veiller a ce que script canal.sh soit adapte')
T = 5e-3;   // duree symbole (s)
M = 2; // nombre symboles

T = round(T*Fe)/Fe; // re-ajustement pour avoir nombre entier d'echantillons par symbole
duree = round(duree/T)*T; // re-ajustement de duree pour avoir nombre entier de symboles

V0 = 0.1 ; // Attention, doit etre < 1

Pw_dB = -60;    // puissance du bruit ajoute' dans le canal, en dB

//-----------------------------
// EMISSION + CANAL

msg = genere_msg_aleatoire(log2(M)*duree/T); // msg binaire pour duree d'emission
m = msg2modulant(msg,M,T,Fe,V0); // Signal NRZ M-aire unipolaire
s = module_bruit(m);    // modulation d'un bruit blanc uniforme
sigSync = 0.5*mls(15)';    // Signal de synchronisation
emet(s,sigSync,Pw_dB,Fe,"s"); // ajout signal de synchro et emission sur canal bruite'
texte = ["installer le microphone et les haut-parleurs" ...
    "regler les parametres audio de la machine"  ...
    "appeler ./canal.sh dans un terminal"  ...
    "recommencer si necessaire"  ...
    "puis cliquez OK"];
//input(text);
messagebox(texte,"modal");

//---------------------------------
// RECEPTION
[r,Pb] = recoit("r",sigSync,Fe,V0,M,duree); // r = signal recu, Pb = puissance estimee du bruit reel du canal
r_FA = filtrage_adapte(r,T,Fe,Pb); // Filtrage adapté
z = echantillonne(r_FA,T,Fe);   // echantillonnage
msgRX = detecteur(z,M,V0);  // detection

//----------------------------------
// RESULTATS

// affichage signaux successifs
affiche_signaux(s,r,r_FA,z,duree,Fe,T);

// affichage nuage des valeurs de z
affiche_z(z,V0,M);

// Taux d'erreur empirique
disp("Nombre de symboles emis = "+string(length(msg)/log2(M)))
Eb = V0^2*T*(M-1)*(2*M-1)/(6*log2(M));
N0 = 2*Pb/Fe; //  DSP du bruit du canal
RSB_dB = 10*log10(Eb/N0);
Pe = d_Hamming(msg,msgRX)/length(msg);
disp("Eb/N0 = " + string(round(RSB_dB)) + " dB");
disp(" => Pe empirique par symbole = " + string(Pe));

