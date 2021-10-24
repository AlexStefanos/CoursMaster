package up.mi.jgm.td02.rationnel;

import up.mi.jgm.maths.Complexe;
import up.mi.jgm.maths.Rationnel;

public class TestRationnelComplexe {

	public static void main(String[] args) {
		System.out.println("-------- Rationnel");
		Rationnel r1 = new Rationnel(3, 5);
		System.out.println("r1 = " + r1);
		System.out.println("r1 ^ 2 = " + UtilRationnelComplexe.puissanceNieme(r1, 2));
		System.out.println("r1 ^ 3 = " + UtilRationnelComplexe.puissanceNieme(r1, 3));
		System.out.println("r1 ^ 5 = " + UtilRationnelComplexe.puissanceNieme(r1, 5));
		Rationnel r2 = new Rationnel(12, 4);
		System.out.println("r2 = " + r2);
		System.out.println("r2 ^ 2 = " + UtilRationnelComplexe.puissanceNieme(r2, 2));
		System.out.println("r2 ^ 3 = " + UtilRationnelComplexe.puissanceNieme(r2, 3));
		System.out.println("r2 ^ 5 = " + UtilRationnelComplexe.puissanceNieme(r2, 5));

		System.out.println("\n-------- Complexe");
		Complexe c1 = new Complexe(5.2, 7.9);
		System.out.println("c1 = " + c1);
		Complexe c2 = new Complexe(0, 2.5);
		System.out.println("c2 = " + c2);
		Complexe c3 = new Complexe(3.4, 0);
		System.out.println("c3 = " + c3);
		System.out.println("c1 + c2 = " + UtilRationnelComplexe.sommeComplexes(c1, c2));
		System.out.println("c1 + c3 = " + UtilRationnelComplexe.sommeComplexes(c1, c3));
		System.out.println("c2 + c3 = " + UtilRationnelComplexe.sommeComplexes(c2, c3));
		System.out.println("c1 * c2 = " + UtilRationnelComplexe.produitComplexes(c1, c2));
		System.out.println("c1 * c3 = " + UtilRationnelComplexe.produitComplexes(c1, c3));
		System.out.println("c2 * c3 = " + UtilRationnelComplexe.produitComplexes(c2, c3));
		
		
	}

}
