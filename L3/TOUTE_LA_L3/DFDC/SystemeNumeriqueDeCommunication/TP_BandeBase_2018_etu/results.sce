vect_Pw = [] ; // vecteur des Pw en dB 

vect_RSB_M2 = [] ; // vecteur des Eb/N0 en dB pour M=2
vect_Pe_emp_M2 = []+%eps; // vecteur des Pe empiriques pour M=2

//vect_RSB_M4 = [] ; // vecteur des Eb/N0 en dB pour M=4
//vect_Pe_emp_M4 = []+%eps; // vecteur des Pe empiriques pour M=4

//vect_RSB_M4g = [] ; // vecteur des Eb/N0 en dB pour M=4 + codage de Gray
//vect_Pe_emp_M4g = []+%eps; // vecteur des Pe empiriques pour M=4 + codage de Gray

scf(); 
plot(vect_RSB_M2,vect_Pe_emp_M2,'b');
//plot(vect_RSB_M4,vect_Pe_emp_M4,'r');
//plot(vect_RSB_M4g,vect_Pe_emp_M4g,'r--');
a= gca(); a.log_flags = "nln";
xlabel("Eb/N0 (dB)")
ylabel("Pe")
//legend(["M=2"; "M=4"])

