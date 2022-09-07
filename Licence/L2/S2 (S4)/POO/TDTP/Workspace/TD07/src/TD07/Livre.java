package TD07;

public class Livre extends Document{
	private String auteur, isbn;
	private int nombrePages;
	
	public Livre(String auteur, int nombrePages, String isbn, int numero, String titre) {
		super (numero, titre);
		this.auteur = auteur;
		this.nombrePages = nombrePages;
		this.isbn = isbn;
	}
	
	public String getAuteur() {
		return auteur;
	}
	
	public int getNombrePages() {
		return nombrePages;
	}
	
	public String toString() {
		return (super.toString() + auteur + "Nombre de Pages : " + nombrePages); 
	}
}
