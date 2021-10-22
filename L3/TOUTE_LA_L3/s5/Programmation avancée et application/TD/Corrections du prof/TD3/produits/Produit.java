package up.mi.jgm.td03.produits;

/**
 * Represente un produit qui peut etre vendu
 * 
 * @author Jean-Guy Mailly
 *
 */
public abstract class Produit {

	/**
	 * Le prix hors taxes du produit
	 */
	private double prixHT;

	/**
	 * Le taux de TVA associe au produit
	 */
	private TVA tauxTVA;

	/**
	 * Construit un produit en fonction de son prix hors taxes et son taux de TVA
	 * 
	 * @param prixHT  le prix hors taxes
	 * @param tauxTVA le taux de TVA du produit
	 */
	public Produit(double prixHT, TVA tauxTVA) {
		this.prixHT = prixHT;
		this.tauxTVA = tauxTVA;
	}

	/**
	 * Permet d'obtenir le prix toutes taxes comprises à partir du prix hors taxes
	 * et du taux de TVA
	 * 
	 * @return le prix TTC
	 */
	public double prixTTC() {
		return prixHT + prixHT * tauxTVA.getTaux();
	}
}
