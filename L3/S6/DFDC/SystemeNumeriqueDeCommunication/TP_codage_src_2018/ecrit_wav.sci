function [x,N,octets,Fe] = ecrit_wav(x,Fe,fichier)
  
  // ecrit un fichier wav à partir d'échantillons entiers appartenant à +/- 2^15
  wavwrite(x/max(abs(x)),Fe,"tmp"); // fichier temporaire pour copier l'entete
  
  N = length(x);
  
  fid = mopen(fichier+".wav",'w');
  tmp = mopen("tmp.wav",'r');
  
  entete = mget(44,'uc',tmp);
  mput(entete,'uc',fid);
  
  mput(x,'s',fid);
  
  mclose(fid);
  mclose(tmp);
  
  deletefile("tmp.wav");
  
endfunction


  