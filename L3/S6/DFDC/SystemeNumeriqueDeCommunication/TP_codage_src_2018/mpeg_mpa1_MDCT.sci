function y = tonal_process(X, tonal, max_local, ranges, look)
  for k = ranges,
    if max_local(k),
        tonal(k) = 1;
        for j = look,
            if X(k) - X(k+j) < 7,
                tonal(k) = 0;
                break;
            end;
        end;
    end	;
  end;
  y = tonal;
endfunction

function i0 = ppv(k0)
  if k0 <= 48
	  i0 = k0;
  elseif k0 <= 96,
	  i0 = floor((k0-48)/2) + 48;
  else,
	  i0 = round((k0-96)/4) + 72;
  end;
  if i0 > 108,
	  i0 = 108;
  end;
endfunction

function le_vf = vf(dz, j, X)
  if dz < -1
	  le_vf = 17 * (dz + 1) - (0.4 * X(j) + 6);
  elseif dz < 0
	  le_vf = (0.4 * X(j) + 6) * dz;
  elseif dz < 1
	  le_vf = -17 * dz;
  else
	  le_vf = -(dz - 1) * (17 - 0.15 * X(j)) - 17;
	end;
endfunction


function [SMR, seuil1 , seuil2 , Ix] = mpeg_mpa1(X2, LTq_i, LTq_k, Table_z, Fi, Fk, Larg_f)
    
  // X2 doit être vecteur col de taille NFFT/2
  N = 2*length(X2);
  if (N ~= 512),
    disp('Erreur: mauvaise taille de fenêtres...');
    return;
  end;
  
  if (sum(X2))>0,
    Ix = 10*log10([X2;0]+%eps);
  else,
    Ix = zeros(N/2+1,1);
  end;
  offset = max(Ix) - 96;
  X = Ix - offset;
 
  // On saute :
  // - La détermination du niveau de pression acoustique
  // - La prise en compte du seuil d'audition absolu
 
 // Recherche des raies tonales
  max_local = zeros(250,1);
  peaks = find(and([X(3:250) > X(2:249) X(3:250) > X(4:251)], 'c'));
  max_local(peaks+2) = ones(length(peaks), 1);
 
  tonal = zeros(250,1);
  tonal = tonal_process(X, tonal, max_local, 3:62, [-2 2]);
  tonal = tonal_process(X, tonal, max_local, 63:126 , [-3 -2 2 3]);
  tonal = tonal_process(X, tonal, max_local, 127:249, [-6:-2 2:6]);

  X_tm = zeros(250,1);
  
  for k = 2:250,
    if tonal(k),
        temp = 10^(X(k-1)/10) + 10^(X(k)/10) + 10^(X(k+1)/10);
        X_tm(k) = 10*log10(temp);
        X(k-1) = -100; 
        X(k)   = -100;
        X(k+1) = -100;
    else,
        X_tm(k) = -100;
    end;
  end;
  
  X_nm = -100*ones(250, 1);
  k = 1;
  for k1 = Fk
    geom_mean = 1;
    pow = 0;
    raies_en_sb = 0;
    while k <= k1,
        geom_mean = geom_mean*k;
        pow = pow + 10^(X(k)/10);
        k = k + 1;
        raies_en_sb = raies_en_sb + 1;
    end;
    geom_mean = floor(geom_mean^(1/raies_en_sb));
    X_nm(geom_mean) = 10*log10(pow);
  end

  // Suppression des composantes masquantes
  mask = find(X_tm < LTq_k(1:250));
  X_tm(mask) = -100*ones(length(mask),1);
  mask = find(X_nm < LTq_k(1:250));
  X_nm(mask) = -100*ones(length(mask),1);
  
  // Elimination des raies tonales dans la même bande critique
  upper_bound = 1;
  lower_bound = 1;
  while upper_bound < 250
    [v, max_X] = max(X_tm(lower_bound:upper_bound));
    X_tm(lower_bound:upper_bound) = -100 * ones(upper_bound-lower_bound+1,1);
    X_tm(max_X+lower_bound-1) = v;
    lower_bound = lower_bound + 1;
    upper_bound = lower_bound + Larg_f(lower_bound);
  end;

  Ni = length(Table_z);
  X_tm_i = -100 * ones(Ni, 1);
  X_nm_i = -100 * ones(Ni, 1);

  for k = 1:250,
    if X_tm(k) >= -10,
      X_tm_i(ppv(k)) = X_tm(k);
    end;
  end;

  for k = 1:250,
    if X_nm(k) >= -10,
      X_nm_i(ppv(k)) = X_nm(k);
    end;
  end;
  
  // Calcul des seuils
  seuil_m = zeros(Ni, 1);
  
  tab_tm = find(X_tm_i > -100);
  tab_nm = find(X_nm_i > -100);

  for i = 1:Ni,
    sum_tm = 0;
    z_i = Table_z(i);
    for j = tab_tm,
        z_j = Table_z(j);
        dz = z_i - z_j;
        if dz >= -3 & dz < 8,
            LT_tm = X_tm_i(j) + (-1.525 - 0.275*z_j - 4.5) + vf(dz, j, X_tm_i);
            sum_tm = sum_tm + 10 ^ (LT_tm/10);
        end;
    end
    sum_nm = 0;
    for j = tab_nm,
        z_j = Table_z(j);
        dz = z_i - z_j;
        if dz >= -3 & dz < 8
            LT_nm = X_nm_i(j) + (-1.525 - 0.175*z_j - 0.5) + vf(dz, j, X_nm_i);
            sum_nm = sum_nm + 10 ^ (LT_nm/10);
        end;
    end;
    seuil_m(i) = 10 * log10(10^(LTq_i(i)/10) + sum_tm + sum_nm);
  end;
  
  // Calcul du seuil de masquage
  seuil1  = zeros(1,256);
  seuil2  = zeros(1,256);
  for i = 1:6,
    t1 = seuil_m(8*(i-1)+1:8*(i))';
    seuil1 (8*(i-1)+1:8*(i)) = t1;
    seuil2 (8*(i-1)+1:8*(i)) = ones(1,8)*min(t1);
  end;
  for i = 7:12,
    i1 = i - 6;
    t1 = seuil_m(49+4*(i1-1):48+4*(i1))';
    t2(1:2:7) = t1;
    t2(2:2:8) = t1;
    seuil1 (8*(i-1)+1:8*(i)) = t2;
    seuil2 (8*(i-1)+1:8*(i)) = ones(1,8)*min(t1);
  end;
  for i = 13:30,
    i1 = i - 12;
    t1 = seuil_m(73+2*(i1-1):72+2*(i1))';
    t2(1:4:5) = t1;
    t2(2:4:6) = t1;
    t2(3:4:7) = t1;
    t2(4:4:8) = t1;
    seuil1 (8*(i-1)+1:8*(i)) = t2;
    seuil2(8*(i-1)+1:8*(i)) = ones(1,8)*min(t1);
  end;
  for i = 31:32,
    seuil1(8*(i-1)+1:8*(i)) = ones(1,8)*min(t1);
    seuil2 (8*(i-1)+1:8*(i)) = ones(1,8)*min(t1);
  end;

  seuil1  = seuil1  + offset;
  seuil2  = seuil2  + offset;
  SMR = zeros(1,32);
  for i = 1:32
    SMR(i) = max(Ix((i-1)*8+1:i*8)) - seuil2 (i*8);
  end
endfunction
