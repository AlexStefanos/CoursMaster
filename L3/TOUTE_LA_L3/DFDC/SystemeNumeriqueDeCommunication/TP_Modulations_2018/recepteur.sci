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

function r_comp = compenseNiveau(r,Ps)
    // Compensation niveau
    Ps = V0^2*(M^2-1)/3;
    r = r * sqrt(Ps)/stdev(r);
endfunction

function m = demodule(r,f0,phi)
    t = (0:length(r)-1)/Fe;
    p = 2*cos(2*%pi*f0*t+phi);
    Hz = iir(4,'lp','cheb2',f0/Fe,[0.05 0.05]);
    m = filter(Hz.num,Hz.den,r.*p);
endfunction

function [ma,mb] = demoduleMAQ(r,f0,phi)
    ma = demodule(r,f0,phi);
    mb = demodule(r,f0,phi+%pi/2);
endfunction

function rFA = filtrage_adapte(r,T,Fe)
    rFA = convol(ones(1,T*Fe)/(T*Fe),r);
    d = ceil((T*Fe-1)/2);
    rFA = rFA(d+(1:length(r)));
endfunction

function z = echantillonne(rFA,T,Fe)
    z = rFA(T*Fe/2:T*Fe:$);
endfunction

function msg = detecteur(z,M,V0)
    x = round((z/V0+M-1)/2);
    x = min(M-1,max(0,x));   // entiers entre 0 et M-1
    NbSymb = length(x);
    tmp = zeros(log2(M),NbSymb);
    for i=1:NbSymb
        tmp(:,i) = int2bin(x(i),log2(M)); 
    end
    msg = matrix(tmp,1,NbSymb*log2(M));
endfunction

function msg = detecteurMAQ(zA,zB,M,V0)
    msgA = detecteur(zA,sqrt(M),V0);
    msgB = detecteur(zB,sqrt(M),V0);
    msg = zeros(1,2*length(msgA));
    msg(1:2:$) = msgA;
    msg(2:2:$) = msgB;
endfunction


function y = Q(x)
    y = erfc(x/sqrt(2))/2;
endfunction

function [r_est,EbDivN0] = recoitALT(fichier,sigSync,f0,Fe,V0,M,duree,T)
    r = reception(fichier);
    // elimination 50Hz parasite
    r = filtre_PasseHaut(r,500,Fe);  // elimination 50Hz parasite
    // Synchronisation
    r_sync = synchronise(r,Fe,sigSync);
    // troncature -> r puis b :
    r_est = r_sync(1:duree*Fe);
    b_est = r_sync(duree*Fe+1:$); // pour calcul Pb
    // Estimation Eb / N0
    Pb = stdev(b_est)^2;
    N0 = 2*Pb/Fe;
    Ps_est = stdev(r_est)^2 -Pb; // estimation puissance de s recu (r-b)
    Eb = Ps_est*T/log2(M);
    EbDivN0 = Eb / N0;
endfunction


function [r_est,b_est,N0] = recoit(fichier,sigSync,f0,Fe,V0,M,duree,modulation)
    r = reception(fichier);
    // elimination 50Hz parasite
    r = filtre_PasseHaut(r,500,Fe);  // elimination 50Hz parasite
    // Synchronisation
    r_sync = synchronise(r,Fe,sigSync);
    // troncature -> r puis b :
    r_est = r_sync(1:duree*Fe);
    b_est = r_sync(duree*Fe+1:$); // pour calcul Pb
    // Normalisation puissance
    select modulation
    case "MDA" then
        Ps = V0^2*(M^2-1)/6;
    case "MAQ" then
        Ps = V0^2*(M-1)/3;
    end
    Ps_est = stdev(r_est)^2 - stdev(b_est)^2; // estimation puissance de s recu (r-b)
    r_est = r_est * sqrt(Ps/Ps_est); // Compensation niveau
    b_est = b_est * sqrt(Ps/Ps_est); // Compensation niveau
    // Estimation N0
    // Q(x) = erfc(x/sqrt(2))/2
    Pb = stdev(b_est)^2;
    N0 = 2*Pb/Fe;
endfunction
