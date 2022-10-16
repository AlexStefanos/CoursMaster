import tp05

#%% Exercice 1.1
tp05.fourierImpulsionnel(20)
tp05.fourierCreneau(20)

#%% Exercice 1.2 : Question 1.2.1 -> 1.2.5
tp05.appliGenSin(256, 16, 256)
tp05.calculTransformeeFourier(256, 16, 256)
tp05.transformeeFourierReellesImaginares(256, 16, 256)
tp05.transformeeFourierSpectreAmpliNormal(256, 16, 256)
tp05.transformmeeFourierSpectreDB(256, 16, 256)

#%% Exercice 1.2 : Question 1.2.6
tp05.appliGenSin(256, 20, 256)
tp05.transformeeFourierReellesImaginares(256, 20, 256)
tp05.transformeeFourierSpectreAmpliNormal(256, 20, 256)
tp05.transformmeeFourierSpectreDB(256, 20, 256)

#%% Exercice 1.2 : Question 1.2.7
tp05.appliGenSin(512, 20, 256)
tp05.transformeeFourierReellesImaginares(512, 20, 256)
tp05.transformeeFourierSpectreAmpliNormal(512, 20, 256)
tp05.transformmeeFourierSpectreDB(512, 20, 256)

#%% Exercice 1.2 : Question 1.2.8
tp05.appliGenSin(512, 20, 400)
tp05.transformeeFourierReellesImaginares(512, 20, 400)
tp05.transformeeFourierSpectreAmpliNormal(512, 20, 400)
tp05.transformmeeFourierSpectreDB(512, 20, 400)

#%% Exercice 1.3
tp05.appliGenSin(256, 126, 200)
tp05.transformeeFourierReellesImaginares(256, 126, 200)
tp05.transformeeFourierSpectreAmpliNormal(256, 126, 200)
tp05.transformmeeFourierSpectreDB(256, 126, 200)