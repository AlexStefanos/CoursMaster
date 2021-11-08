package up.mi.jgm.td01;

/**
 * Classe utilitaire pour quelques operations mathematiques
 * 
 * @author Jean-Guy Mailly
 *
 */
public class UtilMath {
	/**
	 * Calcule la somme de trois nombres entiers.
	 * 
	 * @param a le premier terme
	 * @param b le deuxieme terme
	 * @param c le troisieme terme
	 * @return la somme a + b + c
	 */
	public static int somme3(int a, int b, int c) {
		return a + b + c;
	}

	/**
	 * Calcule la factorielle d'un nombre entier. On ne gere pas pour l'instant le
	 * cas d'erreur ou n est inferieur a 1.
	 * 
	 * @param n le nombre dont on retourne la factorielle
	 * @return la factorielle de l'entier n
	 */
	public static long fact(int n) {
		if (n == 1) {
			return 1;
		}
		return n * fact(n - 1);
	}

	/**
	 * Calcule la combinaison p parmi n.
	 * 
	 * @param n cardinal de l'ensemble global
	 * @param p cardinal des parties
	 * @return le nombre de parties de taille p dans un ensemble de taille n
	 */
	public static long comb(int n, int p) {
		return fact(n) / (fact(p) * fact(n - p));
	}

	/**
	 * Calcule la puissance m-ieme d'in entier n. On considere uniquement le cas ou
	 * m est positif ou nul.
	 * 
	 * @param n le nombre qu'on eleve a la puissance m-ieme
	 * @param m la puissance
	 * @return n puissance m
	 */
	public static long puissance(int n, int m) {
		if (m == 0)
			return 1;
		return n * puissance(n, m - 1);
	}

	/**
	 * Determine le plus grand de deux entiers naturel
	 * 
	 * @param a le premier entier
	 * @param b le second entier
	 * @return le plus grand des entiers a et b
	 */
	public static int max2(int a, int b) {
		return (a >= b) ? a : b;
	}

	/**
	 * Determine le plus grand de trois entiers naturel. N'utilise pas max2.
	 * 
	 * @param a le premier entier
	 * @param b le deuxieme entier
	 * @param c le troisieme entier
	 * @return le plus grand des entiers a, b et c
	 */
	public static int max3v1(int a, int b, int c) {
		if (a >= b) {
			return (a >= c) ? a : c;
		}
		return (b >= c) ? b : c;
	}

	/**
	 * Determine le plus grand de trois entiers naturel. Utilise pas max2.
	 * 
	 * @param a le premier entier
	 * @param b le deuxieme entier
	 * @param c le troisieme entier
	 * @return le plus grand des entiers a, b et c
	 */
	public static int max3v2(int a, int b, int c) {
		return max2(a, max2(b, c));
	}

	/**
	 * Methode naive pour determiner si un entier est un nombre premier. Teste tous
	 * les nombres entre 2 et n.
	 * 
	 * @param n l'entier a tester
	 * @return true si et seulement si n est un nombre premier
	 */
	public static boolean estPremierNaif(int n) {
		if (n < 2)
			return false;
		if (n == 2)
			return true;
		for (int k = 2; k < n; k++) {
			if ((n % k) == 0)
				return false;
		}
		return true;
	}

	/**
	 * Methode pour determiner si un entier est un nombre premier. Ne teste pas les
	 * nombres pairs > 2.
	 * 
	 * @param n l'entier a tester
	 * @return true si et seulement si n est un nombre premier
	 */
	public static boolean estPremierAmeliore(int n) {
		if (n == 2)
			return true;
		if ((n < 2) || (n % 2) == 0)
			return false;
		for (int k = 3; k < n; k += 2) {
			if ((n % k) == 0)
				return false;
		}
		return true;
	}

	/**
	 * Methode pour determiner si un entier est un nombre premier. Ne teste pas les
	 * nombres pairs > 2, arrete les tests si k > sqrt(n).
	 * 
	 * @param n l'entier a tester
	 * @return true si et seulement si n est un nombre premier
	 */
	public static boolean estPremier(int n) {
		if (n == 2)
			return true;
		if ((n < 2) || (n % 2) == 0)
			return false;
		for (int k = 3; k <= Math.sqrt(n); k += 2) {
			if ((n % k) == 0)
				return false;
		}
		return true;
	}

	/**
	 * Retourne les n premiers nombres premiers sous forme de String
	 * 
	 * @param n le nombre de nombres premiers voulus
	 * @return un String correspondant aux n premiers nombres premiers
	 */
	public static String nPremiersNombresPremiers(int n) {
		StringBuilder build = new StringBuilder();
		int nb = 2;
		int nbPrem = 0;
		while (nbPrem < n) {
			if (estPremier(nb)) {
				build.append(nb);
				build.append(" ");
				nbPrem++;
			}
			nb++;
		}
		return build.toString();
	}

	/**
	 * Determine a quelle puissance le nombre premier p apparait dans la
	 * decomposition en facteurs premiers de n.
	 * 
	 * @param n un nombre entier
	 * @param p un nombre premier
	 * @return la puissance a laquelle p apparait dans la decomposition en facteurs
	 *         premiers de n.
	 */
	public static int puissanceDecomposition(int n, int p) {
		int puissance = 0;
		while (((n / p) > 0) && (n % p) == 0) {
			puissance++;
			n /= p;
		}
		return puissance;
	}

	/**
	 * Retourne la decomposition en facteurs premiers d'un nombre sous forme de
	 * String
	 * 
	 * @param n le nombre a decomposer
	 * @return la decomposition en facteurs premiers de n, sous forme de String
	 */
	public static String decompositionFacteursPremiers(int n) {
		StringBuilder build = new StringBuilder();
		if (n < 2) {
			System.err.println("Decomposition impossible");
		}

		int p = 2;
		while (n > 1) {
			if ((n % p) == 0) {
				int k = puissanceDecomposition(n, p);
				build.append("(" + p + ", " + k + ") ");
				n /= puissance(p, k);
			}
			p = prochainNombrePremier(p);
		}

		return build.toString();
	}

	/**
	 * Determine le prochain nombre premier apres un nombre premier donne
	 * 
	 * @param p le nombre premier de depart
	 * @return le plus petit nombre premier strictement plus grand que p
	 */
	private static int prochainNombrePremier(int p) {
		for (int i = p + 1;; i++) {
			if (estPremier(i))
				return i;
		}
	}
}
