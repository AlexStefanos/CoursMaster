clear
exec("binaires.sci");
exec("emetteur.sci");
exec("recepteur.sci");
exec("mls.sci");
exec("affichage.sci");
stacksize(1e8);

//-----------------------------
// PARAMETRES

modulation = "MDA";
Fe = 44100; // frequence d'echantillonnage des fichiers audio (Hz)
duree = 16;  // duree d'emission (s) -> attention : adapter script canal.sh
T = 5e-3;   // duree symbole (s)
M = 4; // nombre symboles

T = round(T*Fe)/Fe; // re-ajustement pour avoir nombre entier d'echantillons par symbole
duree = round(duree/T)*T; // re-ajustement de duree pour avoir nombre entier de symboles

V0 = 0.1; // Attention, doit etre < 0.3
f0 = 5000;  // frequence porteuse utilisee pour canal reel

Pw_dB = -100;    // puissance du bruit ajoute' dans le canal, en dB

//-----------------------------
// EMISSION + CANAL

// msg binaire pour duree d'emission
msg = genere_msg_aleatoire(log2(M)*duree/T); 

// Modulation
select modulation
case "MDA" then
    m = msg2modulant(msg,M,T,Fe,V0);
    s = MDA(m,f0,Fe);
case "MAQ" then
    [ma,mb] = msg2modulants(msg,M,T,Fe,V0); // modulants NRZ sqrt(M)-aire 
    s = MAQ(ma,mb,f0,Fe);
end

// Signal de synchronisation
sigSync = 0.5*mls(15)';

// ajout signal de synchro et emission sur canal bruite'
emet(s,sigSync,Pw_dB,f0,Fe,"s");

texte = ["installer le microphone et le haut-parleur" ...
    "regler les parametres audio de la machine"  ...
    "appeler ./canal.sh dans un terminal"  ...
    "recommencer si necessaire"  ...
    "puis cliquez OK"];
//input(text);
messagebox(texte,"modal");


//---------------------------------
// RECEPTION
[r,b,N0] = recoit("r",sigSync,f0,Fe,V0,M,duree,modulation); // r = signal recu, b = extrait du bruit reel du canal, N0 = sa DSP
//r = -r;

// Demodulation, Filtrage adapte', echantillonnage, decision
phase = 0; // phase du cosinus en reception
select modulation
case "MDA" then
    m_est = demodule(r,f0,phase);
    m_FA = filtrage_adapte(m_est,T,Fe);
    z = echantillonne(m_FA,T,Fe); 
    correction_z = 1;
    affiche_signaux(m,m_est,m_FA,z,duree,Fe,T);
    affiche_z_MDA(z*correction_z,V0,M);
    msgRX = detecteur(z*correction_z,M,V0);
case "MAQ" then
    [ma_est,mb_est] = demoduleMAQ(r,f0,phase);
    ma_FA = filtrage_adapte(ma_est,T,Fe);
    mb_FA = filtrage_adapte(mb_est,T,Fe);
    zA = echantillonne(ma_FA,T,Fe);
    zB = echantillonne(mb_FA,T,Fe);
    affiche_z_MAQ(zA,zB,V0,M);
    msgRX = detecteurMAQ(zA,zB,M,V0);
end

//----------------------------------
// RESULTATS

disp("Nombre de symboles emis = "+string(length(msg)/log2(M)))

select modulation
case "MDA" then
    Ps = V0^2*(M^2-1)/6;
case "MAQ" then
    Ps = V0^2*(M-1)/3;
end
Eb = Ps*T/log2(M);
RSB_dB = 10*log10(Eb/N0);

// Taux d'erreur empirique
Pe = d_Hamming(msg,msgRX)/length(msg);
disp("Eb/N0 = " + string(round(RSB_dB)) + " dB -> Pe empirique = " + string(Pe));


