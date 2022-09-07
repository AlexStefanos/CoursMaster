package up.mi.as.td02;
import up.mi.jgm.maths.*;

public class Util {
	public static void main(String args[]) {
		Rationnel r = new Rationnel(2, 1);
		System.out.println("Puissance d'un Rationnel : " + puissance(r, 5));
		Complexe a = new Complexe(2.0, 3.0);
		Complexe b = new Complexe(3.0, 2.0);
		System.out.println("Somme de deux Complexes : " + sommeComplexes(a, b));
		System.out.println("Produit de deux Complexes : " + produitComplexes(a, b));
	}
	
	public static Rationnel puissance(Rationnel r, int n) {
		Rationnel result = new Rationnel(1, 1);
		while(n > 0) {
			result = result.multiplication(r);
			n--;
		}
		return(result);
	}
	
	public static Complexe sommeComplexes(Complexe a, Complexe b) {
		double PartReelle = a.getPartieReelle() + b.getPartieReelle();
		double PartImagin = a.getPartieImaginaire() + b.getPartieImaginaire();
		return new Complexe(PartReelle, PartImagin);
	}
	
	public static Complexe produitComplexes(Complexe a, Complexe b) {
		double PartReelle = a.getPartieReelle() * b.getPartieReelle() - a.getPartieImaginaire() * b.getPartieImaginaire();
		double PartImagin = a.getPartieReelle() * b.getPartieImaginaire() + a.getPartieImaginaire() * b.getPartieReelle();
		return new Complexe(PartReelle, PartImagin);
	}
}
