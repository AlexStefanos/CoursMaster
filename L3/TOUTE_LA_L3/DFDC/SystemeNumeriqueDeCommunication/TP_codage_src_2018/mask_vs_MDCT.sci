function rien = mask_vs_MDCT(XX,Fe,MPA,tps)
    
  if MPA=="Balazs" then
      exec("seuil_Balazs.sci");
  elseif MPA=="MPEG1" then  
      if Fe==32000 | Fe==44100 then
          load("MPEG_mpa1_"+string(round(Fe/1000))+"k.dat");
          exec("mpeg_mpa1_MDCT.sci");
      else
          disp("erreur : Fs = 32kHz ou 44.1kHz")
          return
      end
  else
      disp("erreur : MPA = Balazs ou MPEG1")
      return
  end
  
  
  [Nmdct,nblocs] = size(XX);
  seuil0 = zeros(1,Nmdct);

  realtimeinit(tps); realtime(0);
      
  for bloc=1:60     
         
         // Seuil de masquage 
         // appliquee a MDCT directement
         X2 = abs(XX(:,bloc)).^2;
         seuil_prec = seuil0;
         if MPA=="Balazs" then             
             seuil0 = seuil_Balazs([X2;0],Nmdct*2,Fe)'; 
             seuil0 = seuil0(1:$-1);
         elseif Nmdct==512 then    // MPEG-1
             X2_256 = max([X2(1:2:$),X2(2:2:$)],'c');
             [SMR, seuil_dB , seuil2 , Ix] = mpeg_mpa1(X2_256, LTq_i, LTq_k, Table_z, Fi, Fk, Larg_f);
             seuil_256 = (10).^(seuil_dB(1:$)/10); 
             seuil0(1:2:$)=seuil_256; seuil0(2:2:$)=seuil_256;        
         end

         // CTRL pre-echo
         if bloc ~= 1 then
             seuil = min(seuil0,2*seuil_prec);    
         else
             seuil = seuil0;
         end

         // Comparaison spectre / seuil 
          realtime(bloc);
          clf(0); plot(10*log10(X2+%eps),'b'); 
          plot(10*log10(seuil+%eps),'r'); 
          f=gcf(); set(f,'figure_size',[1200,800]);
  end
  
  rien = 0;
  
endfunction
