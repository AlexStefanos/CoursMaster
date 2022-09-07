package up.mi.jgm.td03.produits;

/**
 * Represente les differents taux de TVA en France
 * 
 * @author Jean-Guy Mailly
 *
 */
public enum TVA {
	NORMAL(0.2), INTERMEDIAIRE(0.1), REDUIT(0.055), PARTICULIER(0.021);

	/**
	 * Le taux de TVA associe a cette instance
	 */
	private double taux;

	/**
	 * Construit une instance de TVA en fonction de son taux
	 * @param taux le taux de TVA
	 */
	private TVA(double taux) {
		this.taux = taux;
	}

	/**
	 * Permet d'obtenir le taux de TVA associe a l'instance
	 * @return le taux
	 */
	public double getTaux() {
		return taux ;
	}
}
