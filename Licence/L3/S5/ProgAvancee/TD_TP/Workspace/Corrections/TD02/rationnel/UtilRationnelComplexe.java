package up.mi.jgm.td02.rationnel;

import up.mi.jgm.maths.Complexe;
import up.mi.jgm.maths.Rationnel;

/**
 * Classe qui fournit des methodes utiles a la manipulation de nombres
 * rationnels et complexes
 * 
 * @author Jean-Guy Mailly
 *
 */
public class UtilRationnelComplexe {

	/**
	 * Calcule la puissance n-ieme d'un nombre rationnel
	 * 
	 * @param r le rationnel dont on veut la puissance
	 * @param n la puissance a laquelle doit etre eleve le rationnel
	 * @return r eleve a la puissance n
	 */
	public static Rationnel puissanceNieme(Rationnel r, int n) {
		Rationnel res = new Rationnel(1);
		for (int i = 1; i <= n; i++) {
			res = res.multiplication(r);
		}
		return res;
	}

	/**
	 * Calcule la somme de deux nombres complexes
	 * 
	 * @param c1 le premier nombre complexe
	 * @param c2 le second nombre complexe
	 * @return si c1 = a + ib, et c2 = c + id, la methode retourne (a+c) + i*(b+d)
	 */
	public static Complexe sommeComplexes(Complexe c1, Complexe c2) {
		double reel = c1.getPartieReelle() + c2.getPartieReelle();
		double imaginaire = c1.getPartieImaginaire() + c2.getPartieImaginaire();
		return new Complexe(reel, imaginaire);
	}

	/**
	 * Calcule le produit de deux nombres complexes
	 * 
	 * @param c1 le premier nombre complexe
	 * @param c2 le second nombre complexe
	 * @return si c1 = a + ib, et c2 = c + id, la methode retourne (a*c - b*d) +
	 *         i*(a*d + b*c)
	 */
	public static Complexe produitComplexes(Complexe c1, Complexe c2) {
		double reel = c1.getPartieReelle() * c2.getPartieReelle() - c1.getPartieImaginaire() * c2.getPartieImaginaire();
		double imaginaire = c1.getPartieReelle() * c2.getPartieImaginaire()
				+ c1.getPartieImaginaire() * c2.getPartieReelle();
		return new Complexe(reel, imaginaire);
	}
}
