package up.mi.as.td03;

public class DVD extends Produit {
	private String titre, real;
	
	public DVD(double prixHT, String titre, String real) {
		super(prixHT, TVA.NORMAL);
		this.titre = titre;
		this.real = real;
	}
	
	public String getTItre() {
		return(titre);
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getReal() {
		return(real);
	}
	
	public void setReal() {
		this.real = real;
	}
}
