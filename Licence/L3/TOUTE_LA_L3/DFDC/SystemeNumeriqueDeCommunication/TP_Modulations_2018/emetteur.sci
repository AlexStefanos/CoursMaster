function m = msg2modulant(msg,M,T,Fe,V0)
    
    // msg binaire -> suite de symboles M-aires
    NbSymb = floor(length(msg)/log2(M));
    tmp = matrix(msg,log2(M),NbSymb);
    suiteSymb = zeros(1,NbSymb);
    for k=1:NbSymb
        suiteSymb(k) = V0 * ( 2*bin2int(tmp(:,k))-M + 1 );
    end
    
    // suite de symboles -> signal NRZ
    m = ones(T*Fe,1)*suiteSymb;
    m = matrix(m,1,NbSymb*T*Fe);
    
endfunction

function [ma,mb] = msg2modulants(msg,M,T,Fe,V0)
    msg_a = msg(1:2:$); 
    msg_b = msg(2:2:$);
    ma = msg2modulant(msg_a,sqrt(M),T,Fe,V0);
    mb = msg2modulant(msg_b,sqrt(M),T,Fe,V0);
endfunction

function s = MDA(m,f0,Fe)
    // modulation MDA
    t = (0:length(m)-1)/Fe;
    p = cos(2*%pi*f0*t);
    s = m.*p;
endfunction

function s = MAQ(ma,mb,f0,Fe)
    // modulation MAQ
    t = (0:length(ma)-1)/Fe;
    pa = cos(2*%pi*f0*t);
    pb = sin(2*%pi*f0*t);
    s = ma.*pa - mb.*pb;
endfunction

function emet(s,sigSync,Pw_dB,f0,Fe,fichier)
    // Ajout bruit de puissance Pw_dB et ecriture dans s.wav
    sigma_w = 10^(Pw_dB/20);
    w = [zeros(1,length(sigSync)) sigma_w*rand(1,length(s)+3*Fe,"normal")];
    wavwrite([[sigSync s zeros(1,3*Fe)];w],Fe,fichier);
endfunction




