package up.mi.jgm.td03.produits;

/**
 * Represente un medicament
 * @author Jean-Guy Mailly
 *
 */
public class Medicament extends Produit {
	/**
	 * Le nom du medicament
	 */
	private String nom;
	
	/**
	 * Le nom du laboratoire qui produit le medicament
	 */
	private String laboratoire;
	
	/**
	 * Le nombre de comprimes dans une boite
	 */
	private int nbComprimes;

	/**
	 * Construit un medicament
	 * @param nom le nom du medicament
	 * @param laboratoire le nom du laboratoire
	 * @param nbComprimes le nombre de comprimes dans une boite
	 * @param prixHT le prix hors taxes
	 */
	public Medicament(String nom, String laboratoire, int nbComprimes, double prixHT) {
		super(prixHT, TVA.PARTICULIER);
		this.nbComprimes = nbComprimes;
		this.nom = nom;
		this.laboratoire = laboratoire;
	}

	@Override
	public String toString() {
		return nom + ", laboratoire " + laboratoire + " (" + nbComprimes + " comprimes) : " + prixTTC();
	}
}
