package up.mi.jgm.td01;

/**
 * Classe creee pour tester les fonctionnalites implementees dans UtilMath.
 * 
 * @author Jean-Guy Mailly
 *
 */
public class TestUtilMath {

	public static void main(String[] args) {
		System.out.println("somme3(2, 4, 5) = " + UtilMath.somme3(2, 4, 5));
		System.out.println("fact(5) = " + UtilMath.fact(5));
		System.out.println("comb(10, 7) = " + UtilMath.comb(10, 7));
		System.out.println("puissance(4, 3) = " + UtilMath.puissance(4, 3));

		System.out.println("max2(2, 3) = " + UtilMath.max2(2, 3));
		System.out.println("max2(3, 2) = " + UtilMath.max2(3, 2));
		System.out.println("max3v1(3, 5, 6) = " + UtilMath.max3v1(3, 5, 6));
		System.out.println("max3v1(8, 5, 6) = " + UtilMath.max3v1(8, 5, 6));
		System.out.println("max3v2(3, 5, 6) = " + UtilMath.max3v2(3, 5, 6));
		System.out.println("max3v2(8, 5, 6) = " + UtilMath.max3v2(8, 5, 6));
		System.out.println("estPremierNaif(1) = " + UtilMath.estPremierNaif(1));
		System.out.println("estPremierNaif(2) = " + UtilMath.estPremierNaif(2));
		System.out.println("estPremierNaif(3) = " + UtilMath.estPremierNaif(3));
		System.out.println("estPremierNaif(16) = " + UtilMath.estPremierNaif(16));
		System.out.println("estPremierNaif(23) = " + UtilMath.estPremierNaif(23));
		System.out.println("estPremierAmeliore(1) = " + UtilMath.estPremierAmeliore(1));
		System.out.println("estPremierAmeliore(2) = " + UtilMath.estPremierAmeliore(2));
		System.out.println("estPremierAmeliore(3) = " + UtilMath.estPremierAmeliore(3));
		System.out.println("estPremierAmeliore(16) = " + UtilMath.estPremierAmeliore(16));
		System.out.println("estPremierAmeliore(23) = " + UtilMath.estPremierAmeliore(23));
		System.out.println("estPremier(1) = " + UtilMath.estPremier(1));
		System.out.println("estPremier(2) = " + UtilMath.estPremier(2));
		System.out.println("estPremier(3) = " + UtilMath.estPremier(3));
		System.out.println("estPremier(16) = " + UtilMath.estPremier(16));
		System.out.println("estPremier(23) = " + UtilMath.estPremier(23));
		System.out.println("nPremiersNombresPremiers(5) = " + UtilMath.nPremiersNombresPremiers(5));
		System.out.println("nPremiersNombresPremiers(12) = " + UtilMath.nPremiersNombresPremiers(12));
		System.out.println("puissanceDecomposition(15, 2) = " + UtilMath.puissanceDecomposition(15, 2));
		System.out.println("puissanceDecomposition(30, 2) = " + UtilMath.puissanceDecomposition(30, 2));
		System.out.println("puissanceDecomposition(360, 2) = " + UtilMath.puissanceDecomposition(360, 2));
		System.out.println("puissanceDecomposition(360, 2) = " + UtilMath.puissanceDecomposition(360, 3));
		System.out.println("decompositionFacteursPremiers(360) = " + UtilMath.decompositionFacteursPremiers(360));
		System.out.println("decompositionFacteursPremiers(1024) = " + UtilMath.decompositionFacteursPremiers(1024));
		System.out.println("decompositionFacteursPremiers(25398) = " + UtilMath.decompositionFacteursPremiers(25398));
		System.out.println("decompositionFacteursPremiers(431766) = " + UtilMath.decompositionFacteursPremiers(431766));

	}
}
