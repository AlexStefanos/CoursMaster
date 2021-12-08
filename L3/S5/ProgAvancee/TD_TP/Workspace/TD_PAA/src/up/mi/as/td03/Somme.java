package up.mi.as.td03;

public class Somme extends Operateur {
	private Operateur a, b;
	
	public Somme(Operateur a, Operateur b) {
		super(2);
		this.a = a;
		this.b = b;
	}
	
	public double evaluer() {
		return(a.evaluer() + b.evaluer());
	}
}
