package TD07;

public class Roman extends Livre{
	private String prix;
	
	public Roman(String prix, String auteur, String isbn, int nombrePages, int numero, String titre) {
		super (auteur, nombrePages, isbn, numero, titre);
		this.prix = prix;
	}
	
	public String getPrix() {
		return prix;
	}
	
	public String toString() {
		return (super.toString() + "Prix : " + prix);
	}
}
