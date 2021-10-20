vect_Pw = [] ; // vecteur des Pw en dB 

vect_RSBmda = [] ; // Eb/N0 en dB pour MDA
vect_Pe_MDA = []+%eps; // Pe pour MDA

vect_RSBmaq = [] ; // Eb/N0 en dB pour MAQ
vect_Pe_MAQ = []+%eps; // Pe pour MAQ

scf(); plot(vect_RSBmda,vect_Pe_MDA,'k'); plot(vect_RSBmaq,vect_Pe_MAQ,'b');
a= gca(); a.log_flags = "nln";
xlabel("Eb/N0 (dB)")
ylabel("Pe")
legend(["MDA-4"; "MAQ-4"])
