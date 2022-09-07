function [s,exc, sig2] = decod_AR(coded)

    // ENTREE
    // coded = vecteur contenant le flux code'
    
    // SORTIES
    // s = signal decode'
    // exc = signal d'excitation (e dans l'equation)
    // sig2 = vecteur des sigma^2 successifs
    
  // Lit les paramètres de codage et la taille du signal à décoder
  N = coded(1);
  P = coded(2);
  ptr = 3;
  p = [];
  s = []; exc = []; sig2 = [];
  // Etat du filtre de synthèse (continuité)
  filter_state = zeros(P, 1);

  // Etat du générateur de peigne
  phase = 1;
  while ptr < length(coded),
    // lit les paramètres
    a = coded((ptr):(ptr+P-1));
    sigma2 = coded(ptr+P);
    e = coded(ptr+P+1:ptr+P+N);
    // filtrage
    s_filtered = zeros(N,1);
    for n=1:N,
      s_filtered(n) = sqrt(sigma2)*e(n) - a' * filter_state;
      filter_state(2:P) = filter_state(1:(P-1));
      filter_state(1) = s_filtered(n);
    end;
      
    s = [s; s_filtered];
    exc = [exc ; e];
    sig2 = [sig2 ; sigma2];
    ptr = ptr + (P + 1 + N);
  end;
endfunction


