package up.mi.as.td03;

public abstract class Produit {
	protected double prixHT;
	protected TVA tva;
	
	public Produit(double prixHT, TVA tva) {
		this.prixHT = prixHT;
		this.tva = tva;
	}
	
	public double prixTTC() {
		return(prixHT * tva.getTaux());
	}
	
	public TVA getTVA() {
		return(tva);
	}
	
	public void setTVA(TVA tva) {
		this.tva = tva;
	}
}
