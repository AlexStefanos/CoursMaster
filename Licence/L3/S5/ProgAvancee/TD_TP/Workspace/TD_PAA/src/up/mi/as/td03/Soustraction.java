package up.mi.as.td03;

public class Soustraction extends Operateur {
	private Operateur a, b;
	
	public Soustraction(Operateur a, Operateur b) {
		super(2);
		this.a = a;
		this.b = b;
	}
	
	@Override
	public double evaluer() {
		return(a.evaluer() - b.evaluer());
	}
}
