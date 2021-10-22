package up.mi.jgm.td03.produits;

/**
 * Represente un DVD d'un film
 * 
 * @author Jean-Guy Mailly
 *
 */
public class DVD extends Produit {

	/**
	 * Le titre du film
	 */
	private String titre;

	/**
	 * Le realisateur du film
	 */
	private String realisateur;

	/**
	 * Construit une instance de DVD en fonction de son titre, son realisateur et
	 * son prix hors taxes
	 * 
	 * @param titre       le titre
	 * @param realisateur le nom du realisateur
	 * @param prixHT      le prix hors taxes
	 */
	public DVD(String titre, String realisateur, double prixHT) {
		super(prixHT, TVA.NORMAL);
		this.titre = titre;
		this.realisateur = realisateur;
	}

	@Override
	public String toString() {
		return titre + ", de " + realisateur + " : " + prixTTC();
	}

}
