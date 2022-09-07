package up.mi.as.td03;

public enum TVA {
	NORMAL(0.2), INTERMEDIAIRE(0.1), REDUIT(0.055), PARTICULIER(0.021);
	
	private final double taux;
	
	private TVA(double taux) {
		this.taux = taux;
	}
	
	public double getTaux() {
		return(taux);
	}
}
