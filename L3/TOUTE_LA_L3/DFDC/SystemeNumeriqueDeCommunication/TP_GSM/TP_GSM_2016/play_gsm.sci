function play_gsm(fichier)

fichier_wav = strsubst(fichier,".gsm","") + ".wav";
unix("sox " + fichier + " -t wav -r 16000 -s -2 " + fichier_wav)
unix("play " + fichier_wav)

endfunction