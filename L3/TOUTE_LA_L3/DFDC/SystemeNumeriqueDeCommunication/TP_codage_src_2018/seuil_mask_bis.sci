function masquage = seuil_mask_bis(dsp_x,NFFT,Fe);

exec("etalement.sci");

// DEBUT DE L'ANALYSE PAR BANDES CRITIQUES---------------------------------------

// Construction du vecteur de raies des frequences 

	F=(Fe/(2*(1+NFFT/2)):Fe/(2*(1+NFFT/2)):Fe/2)';
	
	if Fe==16000
		dsp_bark=zeros(22,1);
	end

	if Fe==8000
		dsp_bark=zeros(18,1);
	end

	Nbre_bc=size(dsp_bark,1);

Borne=[0;100;200;300;400;510;630;770;920;1080;1270;1480;1720;2000;2320;2700;3150;3700;4400;5300;6400;7700;9500;12000;15500];

i=1;

k=1;

borne_inf=Borne(1);
borne_sup=Borne(2);


// Calcul de l'energie du signal dans chaque bande critique

	while (i < (Nbre_bc+1)) & (k < (NFFT/2+2))
	

	
		if (F(k,1)>=borne_inf) & (F(k,1)<borne_sup)

			dsp_bark(i)=dsp_bark(i)+dsp_x(k);
			k=k+1;
		else

			i=i+1;
			borne_inf=Borne(i);
			borne_sup=Borne(i+1);

		end
	end



// Calcul du spectre etale------> Convolution avec la fonction d'etalement.

	Spec_etale=zeros(Nbre_bc,1);

	for i=1:Nbre_bc
		for k=1:Nbre_bc
			Spec_etale(i)=Spec_etale(i)+etalement(k-i)*dsp_bark(k);
		end
	end


//Calcul de l'indice de correction----> Spectral Flatness Measure

	moy_geom=sum(log10(dsp_x));
	moy_arith=sum(dsp_x);

	moy_geom=10.^(moy_geom/(1+NFFT/2))+%eps;
	moy_arith=moy_arith/(1+NFFT/2)+%eps;
	
	SFM=10*log10(moy_geom/moy_arith);
	SFM_max=-60;

	alfa=min(SFM/SFM_max,1);

	correction=zeros(size(Spec_etale));
	correction=alfa*(14.5+(1:Nbre_bc)')+(1-alfa)*5.5;

// Soustraction de l'indice de correction au spectre etale

seuil=10.^(log10(Spec_etale)-(correction/10)); 

// Normalisation / energie fonction d'etalement
seuil = seuil / sum(etalement(-10:10));

// Retour dans le domaine des Hertz

	k=1;
	indice=1;
	borne_inf=Borne(1);
	borne_sup=Borne(2);

while k<(NFFT/2+2)

if ((k*Fe/(2*(1+NFFT/2)))<borne_sup) & ((k*Fe/(2*(1+NFFT/2)))>=borne_inf)
	
			masquage(k)=seuil(indice);
			k=k+1;

		else

			indice=indice+1;
			borne_inf=borne_sup;
			borne_sup=Borne(indice+1);
		end
end

endfunction

