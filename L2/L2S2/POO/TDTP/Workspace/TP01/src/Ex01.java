
public class Ex01 {
	public void incrementer(Compteur nb) {
		nb.valeur++;
	}
	public void decrementer(Compteur nb) {
		nb.valeur--;
	}
	public void raz(Compteur nb) {
		nb.valeur = 0;
	}
	public int getValeur(Compteur nb) {
		return (nb.valeur);
	}
	public static void main(String[] args) {
		Compteur c1 = new Compteur();
		c1.valeur = 0;
		//incrementer(c1);
		System.out.println("c1.valeur = " + c1.valeur);
		
		//decrementer(c1);
		System.out.println("c1.valeur = " + c1.valeur);
		
		c1.valeur = 26;
		System.out.println("c1.valeur = " + c1.valeur);
		//raz(c1);
		System.out.println("c1.valeur = " + c1.valeur);
		
		c1.valeur = 22;
		//System.out.println("c1.valeur = " + getValeur(c1));
	}
}

class	Compteur {
	int valeur;
}