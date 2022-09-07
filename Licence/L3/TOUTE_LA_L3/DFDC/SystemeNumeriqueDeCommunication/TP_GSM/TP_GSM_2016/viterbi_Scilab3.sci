function out_dec = viterbi(in,motif1,motif2,motifn,motifNm1,motifN)

exec("d_Hamming.sci")

// in = matrice 2 x N 
// 
[M,N] = size(in);
out_dec = zeros(1,N);

C = zeros(4,2,4,N+1);
// C(i,j,:,n) = cellule transition 
//            = {précedent ; suivant ; metrique de branche ; metrique cum}

for n=1:N
  select n
  case 1 then motif = motif1
  case 2 then motif = motif2
  case N-1 then motif = motifNm1
  case N then motif = motifN
  else motif = motifn
  end
  // etats suivants
  C(:,:,2,n) = motif(:,:,1);
  // Metriques de branches
  for i=1:4
    for j=1:2
      s = [motif(i,j,2);motif(i,j,3)];
      C(i,j,3,n) = d_Hamming(in(:,n),s);
    end
  end
  // Metriques cumulees
  C(:,:,4,n) = C(:,:,4,n) + C(:,:,3,n);
  // Elaguage : definition de l'etat precedent chaque etat n+1
  //            initialisation de sa metrique cumulée
  if n==N-1 then nb_etats_new=2;
  elseif n==N then nb_etats_new=1;
  else nb_etats_new=4;  
  end
  for etat_new = 1:nb_etats_new
    [I,J] = find(C(:,:,2,n)==etat_new);
    if length(I)>1 & C(I(1),J(1),4,n) > C(I(2),J(2),4,n) then
      I_min = I(2); J_min = J(2);
      else I_min = I(1); J_min = J(1);
      end
    if I_min<>[] then
      C(etat_new,:,1,n+1) = I_min; // etat precedent le n+1-eme
      C(etat_new,:,4,n+1) = C(I_min,J_min,4,n); // metrique cumulee de etat n+1
    end
  end
end;

// Decodage
etat = 1; transi = 1;
for n=N+1:-1:2
  etat_prec = C(etat,transi,1,n);
  if C(etat_prec,1,2,n-1)==etat then
    transi=1;
  else transi=2;
  end
  out_dec(n-1) = transi-1;
  etat = etat_prec;
end  

endfunction