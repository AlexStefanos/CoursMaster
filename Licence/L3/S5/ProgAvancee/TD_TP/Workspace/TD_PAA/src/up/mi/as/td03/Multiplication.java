package up.mi.as.td03;

public class Multiplication extends Operateur {
	private Operateur a, b;
	
	public Multiplication(Operateur a, Operateur b) {
		super(2);
		this.a = a;
		this.b = b;
	}
	
	@Override
	public double evaluer() {
		return(a.evaluer() * b.evaluer());
	}
}
