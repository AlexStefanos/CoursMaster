function m = msg2modulant(msg,M,T,Fe,V0)
    
    // msg binaire -> suite de symboles M-aires
    NbSymb = floor(length(msg)/log2(M));
    tmp = matrix(msg,log2(M),NbSymb);
    suiteSymb = zeros(1,NbSymb);
    for k=1:NbSymb
        suiteSymb(k) = V0 * bin2int(tmp(:,k));
    end
    
    // suite de symboles -> signal NRZ unipolaire   
    m = ones(round(T*Fe),1)*suiteSymb; 
    m = matrix(m,1,NbSymb*T*Fe);
    
endfunction

function s = module_bruit(m)
    // modulation d'un bruit blanc uniforme
    b = 2*(rand(1,length(m),"uniform") - 0.5);
    s = m .* b;
endfunction

function emet(s,sigSync,Pw_dB,Fe,fichier)
    // Ajout bruit de puissance Pw_dB et ecriture dans s.wav
    sigma_w = 10^(Pw_dB/20);
    w = [zeros(1,length(sigSync)) sigma_w*rand(1,length(s)+3*Fe,"normal")];
    wavwrite([[sigSync s zeros(1,3*Fe)];w],Fe,fichier);
endfunction

// voie G : 0.74s sigSync + ~10s s
// voie D : 0.74 silence + ~10s w

// rec : 13s, demarrage 0 a 1s avant debut du fichier emis 
// -> finit 1.26s à 2.26s apres fin signal s
//     -> on ajoute 3s de blanc voie G, 3s de w voie D
