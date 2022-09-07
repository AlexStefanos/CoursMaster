function [coded,err] = cod_AR(s, order, frame_size, amplitude, nbits)
    
    // ENTREES
    // s = signal `a coder
    // order = ordre de la modelisation AR
    // frame_size = taille des blocs en nombre d'echantillons
    // amplitude = valeur absolue max du signal
    // nbits = nombre de bits de quantification de e.
    // Si nbits = %inf, pas de quantification
    
    // SORTIES
    // coded = vecteur contenant le flux code'
    // err = indicateur d'erreurs dans le processus de codage

  exec("lpc_analysis.sci");
  exec("quantif.sci");

  err=0;
  [nargout, nargin] = argn(0)
  if nargin < 1 then error('No signal!'); end;
  
  // Valeurs par défaut des arguments
  if nargin < 3 then frame_size = 240; end;
  if nargin < 2 then order = 10; end;
  
  // Passage en colonne
  s = s(:);
  
  L = length(s);
  N = frame_size;
  P = order;
  
  // On complète avec des zéros
  frames = ceil(L/N);
  s(frames*N) = 0;
  
  x_prev = zeros(N, 1);
  coded = [N; P]
  
  // Traitement de chaque fenêtre
  for w = 1:N:L,
    // Extraction d'une fenêtre
    x = s(w:(w+N-1));
    
    // Analyse LPC
    [a, sigma2] = lpc_analysis(x, P);
    sigma2 = max(sigma2,1e-3);
    pol = poly([a(P:-1:1),1],'x','c');
    I = find(abs(roots(pol))>=1); 
    if length(I)>0 then err = err + 1; end,
    
    // blanchiment
    e = convol([1 a],[x_prev($-P+1:$);x]);
    e = e(P+1:P+N)/sqrt(sigma2);
    if nbits < %inf
      e = quantif(e,amplitude,nbits);
    end
    
   // constitution d'une nouvelle trame
   coded = [coded; a'; sigma2; e'];
   
   // memoire de x
   x_prev = x;
   
  end;
endfunction



