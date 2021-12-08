package up.mi.as.td03;

public class Valeur extends Operateur {
	private double val;
	
	public Valeur(double val) {
		super(0);
		this.val = val;
	}
	
	@Override
	public double evaluer() {
		return(val);
	}
}
