package up.mi.as.td03;

public class Livre extends Produit {
	private String titre, auteur, isbn;
	
	public Livre(double prixHT, String titre, String auteur, String isbn) {
		super(prixHT, TVA.REDUIT);
		this.titre = titre;
		this.auteur = auteur;
		this.isbn = isbn;
	}
	
	public String getTitre() {
		return(titre);
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getAuteur() {
		return(auteur);
	}
	
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	public String getISBN() {
		return(isbn);
	}
	
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
}
