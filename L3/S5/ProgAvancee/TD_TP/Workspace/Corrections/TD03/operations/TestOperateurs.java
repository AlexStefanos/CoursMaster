package up.mi.jgm.td03.operations;

public class TestOperateurs {

	public static void main(String[] args) {
		Multiplication m = new Multiplication(new Valeur(5.2), new Valeur(4.7));
		Somme s = new Somme(new Valeur(2.5), new Soustraction(new Valeur(10.6), new Valeur(7.2)));
		Division d = new Division(m,s);
		
		System.out.println(d + " = " + d.evaluer());
		
		AdditionNAire a = new AdditionNAire();
		a.ajouteOperande(new Valeur(2));
		a.ajouteOperande(m);
		a.ajouteOperande(new Valeur(4));
		a.ajouteOperande(d);
		System.out.println(a + " = " + a.evaluer());
		
		MultiplicationNAire mN = new MultiplicationNAire();
		mN.ajouteOperande(new Valeur(3));
		mN.ajouteOperande(s);
		mN.ajouteOperande(new Valeur(6.8));
		mN.ajouteOperande(m);
		System.out.println(mN + " = " + mN.evaluer());
	}

}
