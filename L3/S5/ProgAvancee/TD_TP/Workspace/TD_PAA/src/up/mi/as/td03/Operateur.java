package up.mi.as.td03;

public abstract class Operateur {
	private int arite;
	
	public Operateur(int arite) {
		this.arite = arite;
	}

	public int getArite() {
		return(arite);
	}

	public void setArite(int arite) {
		this.arite = arite;
	}
	
	public abstract double evaluer();
}
