package up.mi.jgm.td03.produits;

/**
 * Represente un fruit
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Fruit extends Produit {

	/**
	 * Le nom du fruit
	 */
	private String nom;

	/**
	 * Le pays d'origine du fruit
	 */
	private String pays;

	/**
	 * Construit un fruit
	 * 
	 * @param nom    le nom du fruit
	 * @param pays   le pays d'origine du fruit
	 * @param prixHT le prix hors taxes
	 */
	public Fruit(String nom, String pays, double prixHT) {
		super(prixHT, TVA.REDUIT);
		this.nom = nom;
		this.pays = pays;
	}

	@Override
	public String toString() {
		return nom + " origine " + pays + " : " + prixTTC();
	}
}
