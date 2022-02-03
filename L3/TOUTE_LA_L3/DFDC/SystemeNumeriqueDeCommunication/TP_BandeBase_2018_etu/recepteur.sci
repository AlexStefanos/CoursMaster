function r_sync = synchronise(r,Fe,sigSync)
    // sigSync = signal de synchro
    debut = 0.5*Fe;
    fin = 2*Fe;
    N = fin-debut+1;
    L_sigSync = length(sigSync);
    sigSync = [sigSync zeros(1,N-L_sigSync)];
    corr_sr = corr(sigSync,r(debut:fin),N);
    [maxi,k] = max(abs(corr_sr));
    r_sync = r(debut+k-1:$);
    r_sync = r_sync(L_sigSync+1:$);
endfunction

function r = reception(fichierRX)
    [r,Fe] = wavread(fichierRX); 
    r = r(1,:) - mean(r(1,:));
endfunction

function y = filtre_PasseHaut(x,fc,Fe)
    // Filtrage passe-haut, 
    // pour eliminer composante 50Hz parasite 
    Hz = iir(4,'hp','cheb2',fc/Fe,[0.01 0.01]);
    y = filter(Hz.num,Hz.den,x);
endfunction


function rFA = filtrage_adapte(r,T,Fe,Pb)
    rFA2 = convol(ones(1,T*Fe)/(T*Fe),r.^2); // moyenne glissante du carré
    d = ceil((T*Fe-1)/2);
    rFA2 = rFA2(d+(1:length(r))); // correction du decalage temporel
    rFA = sqrt(max(0,3*(rFA2-Pb))); // a expliquer (Q 1.b)
endfunction


function z = echantillonne(rFA,T,Fe)
    z = rFA(round(T*Fe/2):round(T*Fe):$);
endfunction

function msg = detecteur(z,M,V0)
    x = round(z/V0);
    x = min(M-1,max(0,x));   // entiers entre 0 et M-1
    NbSymb = length(x);
    tmp = zeros(log2(M),NbSymb);
    for i=1:NbSymb
        tmp(:,i) = int2bin(x(i),log2(M)); 
    end
    msg = matrix(tmp,1,NbSymb*log2(M));
endfunction

function y = Q(x)
    y = erfc(x/sqrt(2))/2;
endfunction

function [r,Pb] = recoit(fichier,sigSync,Fe,V0,M,duree)
    // Sorties :
    // * le signal recu r aligne' sur s en temps et en amplitude
    // * la puissance Pb du bruit du canal, estimeee sur la fin du signal
    
    r = reception(fichier); 
    r = filtre_PasseHaut(r,500,Fe);  // a expliquer (Q 1.a)
    // Synchronisation :
    r_sync = synchronise(r,Fe,sigSync);
    // troncature -> r puis b :
    r = r_sync(1:duree*Fe);
    b = r_sync(duree*Fe+1:$); // pour calcul Pb
    // Normalisation puissance
    Ps = V0^2*(M-1)*(2*M-1)/18;  // puissance s emis 
    Ps_est = stdev(r)^2 - stdev(b)^2; // estimation puissance de s recu (r-b)
    r = r * sqrt(Ps/Ps_est); // Compensation niveau
    b = b * sqrt(Ps/Ps_est); // Compensation niveau
    // Estimation puissance bruit du canal
    Pb = stdev(b)^2; // puissance bruit corrigee
endfunction

