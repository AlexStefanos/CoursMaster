// Branchez et ouvrez le micro
// verifiez vos paramètres audio
// ne faites pas de bruit

// enregistrement
//unix_g("./test_micro.sh");

// temporisation
realtimeinit(1);
realtime(0);
realtime(1);

// signal enregistre'
[x,Fe] = wavread("silence.wav");
N = length(x);
t = (0:N-1)/Fe;
scf; plot(t,x);
xlabel("temps (s)");
ylabel("x");
title("Enregistrement de 2s de silence");

// son spectre d'amplitude
NFFT = 2^14;
X = fft(x(1:NFFT));
scf; plot((-NFFT/2:NFFT/2-1)*Fe/NFFT,abs(fftshift(X)));
xlabel("frequence (Hz)");
ylabel("|X(nu)|");
title("Spectre d\'amplitude du signal");

