package up.mi.jgm.td03.produits;

/**
 * Represente un livre
 * @author Jean-Guy Mailly
 *
 */
public class Livre extends Produit {
	
	/**
	 * Le titre de livre
	 */
	private String titre;
	
	/**
	 * L'auteur du livre
	 */
	private String auteur;
	
	/**
	 * L'ISBN du livre
	 */
	private String isbn;

	/**
	 * Construit un livre
	 * @param titre titre du livre
	 * @param auteur auteur du livre
	 * @param isbn ISBN du livre
	 * @param prixHT prix hors taxes du livre
	 */
	public Livre(String titre, String auteur, String isbn, double prixHT) {
		super(prixHT, TVA.REDUIT);
		this.titre = titre;
		this.auteur = auteur;
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return titre + " de " + auteur + " (ISBN : " + isbn + ") : " + prixTTC();
	}
}
