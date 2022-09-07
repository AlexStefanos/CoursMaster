package TD07;

public class Document {
	//Attributs
	private int numero;
	private String titre;
	
	//Constructeurs
	public Document (int numero, String titre) {
		this.numero = numero;
		this.titre = titre;
	}
	
	//gets
	public int getNumero() {
		return (numero);
	}
	public String getTitre() {
		return (titre);
	}
	
	
	public boolean equals(Object obj) {
		return obj instanceof Document && ((Document) obj).numero == numero;
	}
	
	public String toString() {
		return (titre + "Numéro : " + numero);
	}
}
